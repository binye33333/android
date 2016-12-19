package com.teach.yo.codeshop.recycleView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.teach.yo.codeshop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyou729 on 16/12/18.
 */

public class RecycleViewActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mRecycleViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_recyle_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        mRecycleViewAdapter = new RecycleViewAdapter(this);
        mRecyclerView.setAdapter(mRecycleViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.addItemDecoration(new RecycleGridDividerDecoration(this));
        initTestData();
    }


    private void initTestData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            data.add("data " + i);
        }
        mRecycleViewAdapter.setData(data);
    }

}
