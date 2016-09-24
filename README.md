# RadioButtonView [![](https://jitpack.io/v/pwcong/RadioButtonView.svg)](https://jitpack.io/#pwcong/RadioButtonView)

一个简单单选按钮。

![SnapShot](https://github.com/pwcong/SnapShot/blob/master/RadioButtonView/GIF2.gif)

****************

# How To

To get a Git project into your build:

## Step 1. Add the JitPack repository to your build file
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

## Step 2. Add the dependency
```	
	dependencies {
	        compile 'com.github.pwcong:RadioButtonView:v1.0.0'
	}

```

****************

# Usage

# Step 1. Add it in Layout

```
    <me.pwcong.radiobuttonview.view.RadioButtonView
        android:id="@+id/rbv_1"
        android:layout_width="500dp"
        android:layout_height="100dp" 
        app:frameColor="#FF4081"
        app:textColor="#FFFFFF"
        app:strokeWidth="6dp"
        app:margin="12dp"/>
        
```

# Step 2. Initialize it

```
      protected void onCreate(Bundle savedInstanceState) {
      
        ...

        rbv = (RadioButtonView) findViewById(R.id.rbv_1);
        
        rbv.setOptions(getArrayList());
        
        rbv.setOnRadioButtonChangedListener(new RadioButtonView.OnRadioButtonChangedListener() {
            @Override
            public void onRadioButtonChanged(String option, int index) {

              //Todo

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
        <attr name="strokeWidth" format="dimension"/>
        <attr name="frameColor" format="color"/>
        <attr name="textColor" format="color"/>

    </declare-styleable>

```

****************

# Recently

## v1.0.2

添加默认属性。

## v1.0.1

* 修复边距过大问题调节字体大小
* 调节字体大小
* 增加预点击时填充色块

## v1.0.0

简单实现单选按钮。



