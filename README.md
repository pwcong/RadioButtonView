# RadioButtonView [![](https://jitpack.io/v/pwcong/RadioButtonView.svg)](https://jitpack.io/#pwcong/RadioButtonView)

一个简单单选按钮。

![SnapShot](https://github.com/pwcong/SnapShot/blob/master/RadioButtonView/GIF2.gif)

****************

# How To Install

see https://jitpack.io/#pwcong/RadioButtonView

# Usage

## Step 1. Add it in Layout

```
<me.pwcong.radiobuttonview.RadioButtonView
    android:id="@+id/rbv_1"
    android:layout_width="500dp"
    android:layout_height="100dp" 
    app:frame_color="#FF4081"
    app:text_color="#FFFFFF"
    app:stroke_width="6dp"
    app:margin="12dp"
/>
        
```

## Step 2. Initialize it

```
protected void onCreate(Bundle savedInstanceState) {

  ...

  rbv = (RadioButtonView) findViewById(R.id.rbv_1);
  
  rbv.setOptions(getArrayList());
  
  rbv.setOnRadioButtonChangedListener(new RadioButtonView.OnRadioButtonChangedListener() {
      @Override
      public void onRadioButtonChanged(String option, int index) {
        // Todo
      }
  });
  
}

private ArrayList<String> getArrayList(){
  ...
}

```

## Here are the attributes of RadioButtonView

```
<declare-styleable name="RadioButtonView">
    <attr name="margin" format="dimension"/>
    <attr name="stroke_width" format="dimension"/>
    <attr name="frame_color" format="color"/>
    <attr name="text_color" format="color"/>
</declare-styleable>
```

****************
