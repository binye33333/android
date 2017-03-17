package com.teach.yo.codeshop;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teach.yo.codeshop.view.ChartBean;
import com.teach.yo.codeshop.view.FanChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyou729 on 17/3/17.
 */

public class ChartViewTest extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_chart_view_anim);


        final FanChartView fanChartView = (FanChartView) findViewById(R.id.fanChartView);

        final List<ChartBean> list = new ArrayList<>();
        ChartBean chartBean = new ChartBean();
        chartBean.color = Color.BLUE;
        chartBean.number = 4;
        chartBean.name = "小马";
        list.add(chartBean);


        ChartBean chartBean1 = new ChartBean();
        chartBean1.color = Color.RED;
        chartBean1.number = 1;
        chartBean1.name = "小张";
        list.add(chartBean1);


        ChartBean chartBean2 = new ChartBean();
        chartBean2.color = Color.GRAY;
        chartBean2.number = 3;
        chartBean2.name = "小王";
        list.add(chartBean2);


        ChartBean chartBean3 = new ChartBean();
        chartBean3.color = Color.GREEN;
        chartBean3.number = 4;
        chartBean3.name = "小李";
        list.add(chartBean3);


        ChartBean chartBean4 = new ChartBean();
        chartBean4.color = Color.BLACK;
        chartBean4.number = 5;
        chartBean4.name = "小美";
        list.add(chartBean4);


        ChartBean chartBean5 = new ChartBean();
        chartBean5.color = Color.CYAN;
        chartBean5.number = 2;
        chartBean5.name = "小芳";
        list.add(chartBean5);


        fanChartView.postDelayed(new Runnable() {
            @Override
            public void run() {
                fanChartView.setData(list);
            }
        }, 2000);
    }
}
