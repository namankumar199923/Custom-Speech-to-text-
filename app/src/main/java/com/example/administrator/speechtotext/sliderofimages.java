package com.example.administrator.speechtotext;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sliderofimages extends AppCompatActivity {
    private ViewPager viewPager;
    private slideadapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliderofimages);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        b= findViewById(R.id.start);
        myadapter = new slideadapter(this);
        viewPager.setAdapter(myadapter);


    }
}
