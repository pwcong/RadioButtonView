package me.pwcong.radiobuttonview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class RadioButtonView : View {
    private val k: Float = 0.5522848f
    private var updated: Boolean = false

    private var mPaint: Paint? = null

    private var width: Int = 0
    private var height: Int = 0
    private var length: Int = 0
    private var current: Int = 0
    private var oldCurrent: Int = 0
    private var clickCurrent: Int = -1
    private var radius: Float = -1f
    private var eachWidth: Float = 0f
    private var textSize: Float = 0f


    // 一下为可定制属性
    private var margin: Float = 4.0f
    private var frameColor: Int = BLUE
    private var textColor: Int = Color.WHITE
    private var strokeWidth: Float = 2.0f

    private var options: List<String>? = null

    private var listener: OnRadioButtonChangedListener? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        width = MeasureSpec.getSize(widthMeasureSpec)
        height = MeasureSpec.getSize(heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        width = w
        height = h
    }

    override fun onDraw(canvas: Canvas) {
        if (!updated) {
            initVariable()
            updated = true
        }

        drawFrame(canvas)
        drawClickedFrame(canvas)
        drawText(canvas)
    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val x1 = event.x
                val y1 = event.y

                if (y1 > height / 2.0f - radius && y1 < height / 2.0f + radius && x1 > margin && x1 < width - margin) {
                    clickCurrent = ((x1 - margin) / eachWidth).toInt()

                    postInvalidate()
                }
            }

            MotionEvent.ACTION_UP -> {
                val x2 = event.x
                val y2 = event.y

                if (y2 > height / 2.0f - radius && y2 < height / 2.0f + radius && x2 > margin && x2 < width - margin) {
                    val t = ((x2 - margin) / eachWidth).toInt()

                    if (t == clickCurrent) {
                        current = t

                        if (current != oldCurrent) {
                            oldCurrent = current

                            if (listener != null) {
                                listener!!.onRadioButtonChanged(options!![current], current)
                            }
                        }
                    }
                }

                clickCurrent = -1
                postInvalidate()
            }

            else -> {}
        }
        return true
    }

    private fun drawFrame(canvas: Canvas) {
        mPaint = Paint()
        mPaint!!.color = frameColor
        mPaint!!.isAntiAlias = true
        mPaint!!.strokeWidth = strokeWidth

        for (i in options!!.indices) {
            if (i == current) {
                mPaint!!.style = Paint.Style.FILL_AND_STROKE
            } else {
                mPaint!!.style = Paint.Style.STROKE
            }

            val path: Path = Path()

            if (i == 0) {
                path.moveTo(margin, height / 2.0f)

                path.cubicTo(
                    margin,
                    height / 2.0f - radius * k,
                    margin + radius * (1 - k),
                    height / 2.0f - radius,
                    margin + radius,
                    height / 2.0f - radius
                )
                path.lineTo(margin + eachWidth, height / 2.0f - radius)
                path.lineTo(margin + eachWidth, height / 2.0f + radius)
                path.lineTo(margin + radius, height / 2.0f + radius)
                path.cubicTo(
                    margin + radius * (1 - k),
                    height / 2.0f + radius,
                    margin,
                    height / 2.0f + radius * k,
                    margin,
                    height / 2.0f
                )
                path.close()
            } else if (i == (options!!.size - 1)) {
                path.moveTo(width - margin, height / 2.0f)

                path.cubicTo(
                    width - margin,
                    height / 2.0f - radius * k,
                    width - margin - radius * (1 - k),
                    height / 2.0f - radius,
                    width - margin - radius,
                    height / 2.0f - radius
                )
                path.lineTo(width - margin - eachWidth, height / 2.0f - radius)
                path.lineTo(width - margin - eachWidth, height / 2.0f + radius)
                path.lineTo(width - margin - radius, height / 2.0f + radius)
                path.cubicTo(
                    width - margin - radius * (1 - k),
                    height / 2.0f + radius,
                    width - margin,
                    height / 2.0f + radius * k,
                    width - margin,
                    height / 2.0f
                )
                path.close()
            } else {
                path.moveTo(margin + i * eachWidth, height / 2.0f - radius)
                path.lineTo(margin + (i + 1) * eachWidth, height / 2.0f - radius)
                path.lineTo(margin + (i + 1) * eachWidth, height / 2.0f + radius)
                path.lineTo(margin + i * eachWidth, height / 2.0f + radius)
                path.close()
            }

            canvas.drawPath(path, mPaint!!)
        }
    }

    private fun drawClickedFrame(canvas: Canvas) {
        if (clickCurrent != -1) {
            mPaint = Paint()
            mPaint!!.color = frameColor
            mPaint!!.isAntiAlias = true
            mPaint!!.strokeWidth = strokeWidth
            mPaint!!.style = Paint.Style.FILL_AND_STROKE

            val path: Path = Path()

            if (clickCurrent == 0) {
                path.moveTo(margin, height / 2.0f)

                path.cubicTo(
                    margin,
                    height / 2.0f - radius * k,
                    margin + radius * (1 - k),
                    height / 2.0f - radius,
                    margin + radius,
                    height / 2.0f - radius
                )
                path.lineTo(margin + eachWidth, height / 2.0f - radius)
                path.lineTo(margin + eachWidth, height / 2.0f + radius)
                path.lineTo(margin + radius, height / 2.0f + radius)
                path.cubicTo(
                    margin + radius * (1 - k),
                    height / 2.0f + radius,
                    margin,
                    height / 2.0f + radius * k,
                    margin,
                    height / 2.0f
                )
                path.close()
            } else if (clickCurrent == (options!!.size - 1)) {
                path.moveTo(width - margin, height / 2.0f)

                path.cubicTo(
                    width - margin,
                    height / 2.0f - radius * k,
                    width - margin - radius * (1 - k),
                    height / 2.0f - radius,
                    width - margin - radius,
                    height / 2.0f - radius
                )
                path.lineTo(width - margin - eachWidth, height / 2.0f - radius)
                path.lineTo(width - margin - eachWidth, height / 2.0f + radius)
                path.lineTo(width - margin - radius, height / 2.0f + radius)
                path.cubicTo(
                    width - margin - radius * (1 - k),
                    height / 2.0f + radius,
                    width - margin,
                    height / 2.0f + radius * k,
                    width - margin,
                    height / 2.0f
                )
                path.close()
            } else {
                path.moveTo(margin + clickCurrent * eachWidth, height / 2.0f - radius)
                path.lineTo(margin + (clickCurrent + 1) * eachWidth, height / 2.0f - radius)
                path.lineTo(margin + (clickCurrent + 1) * eachWidth, height / 2.0f + radius)
                path.lineTo(margin + clickCurrent * eachWidth, height / 2.0f + radius)
                path.close()
            }

            canvas.drawPath(path, mPaint!!)
        }
    }


    private fun drawText(canvas: Canvas) {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true

        mPaint!!.textSize = textSize


        for (i in options!!.indices) {
            if (i == current || i == clickCurrent) {
                mPaint!!.color = textColor
            } else {
                mPaint!!.color = frameColor
            }

            val text = options!![i]

            val textBound = Rect()
            mPaint!!.getTextBounds(text, 0, text.length, textBound)

            canvas.drawText(
                text,
                margin + (i + 0.5f) * eachWidth - textBound.width() / 2.0f,
                height / 2.0f + textBound.height() / 2.5f,
                mPaint!!
            )
        }
    }

    private fun initAttrs(attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RadioButtonView)

        margin = typedArray.getDimension(R.styleable.RadioButtonView_margin, 4.0f)
        strokeWidth = typedArray.getDimension(R.styleable.RadioButtonView_stroke_width, 2.0f)
        frameColor = typedArray.getColor(R.styleable.RadioButtonView_frame_color, Color.WHITE)
        textColor = typedArray.getColor(R.styleable.RadioButtonView_text_color, BLUE)

        typedArray.recycle()
    }

    private fun initVariable() {
        if (options == null || options!!.isEmpty()) {
            options = defaultOptions
        }

        length = options!!.size
        eachWidth = (width - 2 * margin) / length

        val ew = (width - 2 * margin) / length
        val eh = (height - 2 * margin) / 2

        radius = if (ew > eh) eh else ew

        textSize = radius * 0.7f
    }

    /**
     * 设置按钮与View之间的间距
     * @param margin float
     */
    fun setMargin(margin: Float) {
        this.margin = margin
    }

    /**
     * 设置按钮填充区与线条颜色
     * @param frameColor int
     */
    fun setFrameColor(frameColor: Int) {
        this.frameColor = frameColor
    }

    /**
     * 设置按钮选择区文字颜色
     * @param textColor int
     */
    fun setTextColor(textColor: Int) {
        this.textColor = textColor
    }

    /**
     * 设置按钮线条粗度
     * @param strokeWidth float
     */
    fun setStrokeWidth(strokeWidth: Float) {
        this.strokeWidth = strokeWidth
    }

    /**
     * 设置按钮选项
     * @param options List<String>
    </String> */
    fun setOptions(options: List<String>?) {
        this.options = options
    }

    /**
     * 设置按钮选项监听器
     * @param listener OnRadioButtonChangedListener
     */
    fun setOnRadioButtonChangedListener(listener: OnRadioButtonChangedListener?) {
        this.listener = listener
    }

    private val defaultOptions: List<String>
        get() {
            val defaultOptions = mutableListOf<String>()
            defaultOptions.add("ON")
            defaultOptions.add("OFF")

            return defaultOptions
        }

    interface OnRadioButtonChangedListener {
        fun onRadioButtonChanged(option: String?, index: Int)
    }

    companion object {
        const val BLUE: Int = -0xde690d
    }
}
