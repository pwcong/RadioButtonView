package me.pwcong.radiobuttonviewdemo;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import me.pwcong.radiobuttonview.view.RadioButtonView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;

    RadioButtonView rbv1;
    RadioButtonView rbv2;
    RadioButtonView rbv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout= (RelativeLayout) findViewById(R.id.activity_main);

        rbv1= (RadioButtonView) findViewById(R.id.rbv_1);
        rbv2= (RadioButtonView) findViewById(R.id.rbv_2);
        rbv3= (RadioButtonView) findViewById(R.id.rbv_3);

        rbv1.setOnRadioButtonChangedListener(new RadioButtonView.OnRadioButtonChangedListener() {
            @Override
            public void onRadioButtonChanged(String option, int index) {

                Snackbar.make(layout,option,Snackbar.LENGTH_SHORT).show();

            }
        });

        rbv2.setOptions(getGenders());
        rbv2.setOnRadioButtonChangedListener(new RadioButtonView.OnRadioButtonChangedListener() {
            @Override
            public void onRadioButtonChanged(String option, int index) {
                Snackbar.make(layout,option,Snackbar.LENGTH_SHORT).show();
            }
        });

        rbv3.setOptions(getJobs());
        rbv3.setOnRadioButtonChangedListener(new RadioButtonView.OnRadioButtonChangedListener() {
            @Override
            public void onRadioButtonChanged(String option, int index) {
                Snackbar.make(layout,getJobs().get(index),Snackbar.LENGTH_SHORT).show();
            }
        });



    }

    private ArrayList<String> getGenders(){

        ArrayList<String> genders=new ArrayList<>();
        genders.add("未知");
        genders.add("男");
        genders.add("女");
        return genders;

    }

    private ArrayList<String> getJobs(){

        ArrayList<String> jobs=new ArrayList<>();
        jobs.add("老师");
        jobs.add("司机");
        jobs.add("农民");
        jobs.add("经理");
        return jobs;

    }

}
