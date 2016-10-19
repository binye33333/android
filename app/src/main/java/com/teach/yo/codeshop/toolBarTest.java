package com.teach.yo.codeshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by binye33333 on 2016/6/19.
 */
public class ToolBarTest extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_toolbar_layout);

        //不这么设置 toolbar 内容不显示
        Toolbar toolbar = (Toolbar) findViewById(R.id.test_toolBar);

        toolbar.setLogo(R.drawable.icon_delete);
        // 这个设置要在         setSupportActionBar(toolbar)钱才有效果

        toolbar.setTitle("My app");
        toolbar.setSubtitle("an app for test");
        setSupportActionBar(toolbar);
    }
}
