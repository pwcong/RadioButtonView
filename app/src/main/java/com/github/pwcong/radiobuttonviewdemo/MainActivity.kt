package com.github.pwcong.radiobuttonviewdemo

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.github.pwcong.radiobuttonview.RadioButtonView
import com.github.pwcong.radiobuttonview.RadioButtonView.OnRadioButtonChangedListener

class MainActivity : AppCompatActivity() {
    private var layout: RelativeLayout? = null

    private var rbv1: RadioButtonView? = null
    private var rbv2: RadioButtonView? = null
    private var rbv3: RadioButtonView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById<View>(R.id.activity_main) as RelativeLayout

        rbv1 = findViewById<View>(R.id.rbv_1) as RadioButtonView
        rbv2 = findViewById<View>(R.id.rbv_2) as RadioButtonView
        rbv3 = findViewById<View>(R.id.rbv_3) as RadioButtonView

        rbv1!!.setOnRadioButtonChangedListener(object : OnRadioButtonChangedListener {
            override fun onRadioButtonChanged(option: String?, index: Int) {
                Snackbar.make(layout!!, option!!, Snackbar.LENGTH_SHORT).show()
            }
        })

        rbv2!!.setOptions(genders)
        rbv2!!.setOnRadioButtonChangedListener(object : OnRadioButtonChangedListener {
            override fun onRadioButtonChanged(option: String?, index: Int) {
                Snackbar.make(layout!!, option!!, Snackbar.LENGTH_SHORT).show()
            }
        })

        rbv3!!.setOptions(jobs)
        rbv3!!.setOnRadioButtonChangedListener(object : OnRadioButtonChangedListener {
            override fun onRadioButtonChanged(option: String?, index: Int) {
                Snackbar.make(layout!!, jobs[index], Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private val genders: List<String>
        get() {
            val genders = mutableListOf<String>()
            genders.add("未知")
            genders.add("男")
            genders.add("女")
            return genders
        }

    private val jobs: List<String>
        get() {
            val jobs = mutableListOf<String>()
            jobs.add("老师")
            jobs.add("司机")
            jobs.add("农民")
            jobs.add("经理")
            return jobs
        }
}
