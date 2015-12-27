package com.teach.yo.codeshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.teach.yo.toollibrary.view.pull.PullToRefreshListView;

/**
 * 作者：chenyo on 2015/12/26 11:30
 */
public class PullListViewTest extends AppCompatActivity {

    private PullToRefreshListView pull_listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_act_pulllistview_layout);
    }

    private void initView(){
        pull_listView = (PullToRefreshListView) findViewById(R.id.pull_listView);


    }
}
