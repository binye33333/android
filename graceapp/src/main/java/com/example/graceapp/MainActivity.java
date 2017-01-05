package com.example.graceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.graceapp.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
