package com.example.graceapp.base;

import android.nfc.cardemulation.OffHostApduService;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.example.graceapp.R;

/**
 * Created by chenyou729 on 16/12/23.
 */

public class BaseActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        mFrameLayout = findView(R.id.activity_content);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, mFrameLayout, true);
    }


    public <K extends View> K findView(@IdRes int id) {
        return (K) super.findViewById(id);
    }
}
