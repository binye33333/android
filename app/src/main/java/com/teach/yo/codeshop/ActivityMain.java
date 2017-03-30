package com.teach.yo.codeshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.teach.yo.codeshop.htmlParser.HtmlTest;
import com.teach.yo.codeshop.recycleView.RecycleViewActivity;
import com.teach.yo.codeshop.viewpager.ViewPagerDemo;
import com.teach.yo.toollibrary.adapter.TeaAdapter;
import com.teach.yo.toollibrary.adapter.TeaViewHolder;

import java.util.Arrays;

/**
 * 作者：chenyo on 2015/12/26 11:33
 */
public class ActivityMain extends AppCompatActivity {

    private String[] activity = {"EditTextWithClear", "PullListView", "DrawerLayout", "MaterialDesign", "SwipeRefresh", "CropTest", "CustomerProgressTest",
            "autoLayout", "ObjectAnimTest", "MultAdapterTest", "HtmlTest", "ToolbarTest", "progressAnim", "BannerTabTest", "ViewPagerDemo"
            , "FragmentPagerReFresh", "RecycleViewActivity", "ScrollLayoutTest", "OkhttpTest", "ChartViewTest", "LuckPanTest"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_act_main_layout);
        initView();
    }


    private void initView() {
        ListView test_main_lv = (ListView) findViewById(R.id.test_main_lv);
        InnerAdapter innerAdapter = new InnerAdapter(this);
        innerAdapter.setData(Arrays.asList(activity));
        test_main_lv.setAdapter(innerAdapter);
        test_main_lv.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(ActivityMain.this, ClearEditTextTest.class);
                    break;
                case 1:
                    intent = new Intent(ActivityMain.this, PullToRefreshListActivity.class);
                    break;
                case 2:
                    intent = new Intent(ActivityMain.this, DrawerLayoutTest.class);
                    break;
                case 3:
                    intent = new Intent(ActivityMain.this, MaterialDesignTest.class);
                    break;
                case 4:
                    intent = new Intent(ActivityMain.this, SwapRefreshTest.class);
                    break;
                case 5:
                    intent = new Intent(ActivityMain.this, CropActivityTest.class);
                    break;
                case 6:
                    intent = new Intent(ActivityMain.this, CustomerProgressTest.class);
                    break;
                case 7:
                    intent = new Intent(ActivityMain.this, AutoLayoutTest.class);
                    break;
                case 8:
                    intent = new Intent(ActivityMain.this, ObjectAnimTest.class);
                    break;
                case 9:
                    intent = new Intent(ActivityMain.this, MultAdapterTest.class);
                    break;
                case 10:
                    intent = new Intent(ActivityMain.this, HtmlTest.class);
                    break;
                case 11:
                    intent = new Intent(ActivityMain.this, ToolBarTest.class);
                    break;
                case 12:
                    intent = new Intent(ActivityMain.this, ProgressBarTest.class);
                    break;
                case 13:
                    intent = new Intent(ActivityMain.this, BannerTabTest.class);
                    break;
                case 14:
                    intent = new Intent(ActivityMain.this, ViewPagerDemo.class);
                    break;
                case 15:
                    intent = new Intent(ActivityMain.this, FragmentDataRefresh.class);
                    break;
                case 16:
                    intent = new Intent(ActivityMain.this, RecycleViewActivity.class);
                    break;
                case 17:
                    intent = new Intent(ActivityMain.this, ScrollLayoutTest.class);
                    break;
                case 18:
                    intent = new Intent(ActivityMain.this, OkHttpTest.class);
                    break;
                case 19:
                    intent = new Intent(ActivityMain.this, ChartViewTest.class);
                    break;
                case 20:
                    intent = new Intent(ActivityMain.this, LuckPanTest.class);
                    break;
            }
            startActivity(intent);
        }
    };
}


class InnerAdapter extends TeaAdapter<String> {


    public InnerAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.textview_layout;
    }

    @Override
    protected TeaViewHolder<String> getViewHolder() {
        return new InnerHolder();
    }
}

class InnerHolder extends TeaViewHolder<String> {

    TextView textView;

    @Override
    public void init(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
    }

    @Override
    public void setItemData(String data, int position) {
        textView.setText(data);
    }
}


