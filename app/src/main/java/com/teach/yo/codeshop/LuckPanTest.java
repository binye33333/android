package com.teach.yo.codeshop;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.teach.yo.codeshop.view.luckPan.LuckBean;
import com.teach.yo.codeshop.view.luckPan.LuckItemInfo;
import com.teach.yo.codeshop.view.luckPan.LuckPanListener;
import com.teach.yo.codeshop.view.luckPan.LuckView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyou729 on 17/3/28.
 */

public class LuckPanTest extends Activity {

    private LuckView luckView;

    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_pan_layout);

        luckView = (LuckView) findViewById(R.id.luck_view);


        LuckBean luckBean = new LuckBean();
        List<LuckItemInfo> list = new ArrayList<>(6);


        LuckItemInfo info = new LuckItemInfo();
        info.color = "#ffdb01";
        info.prize_name = "谢谢参与";
        list.add(info);

        LuckItemInfo info1 = new LuckItemInfo();
        info1.color = "#ff9600";
        info1.prize_name = "100积分";
        list.add(info1);

        LuckItemInfo info2 = new LuckItemInfo();
        info2.color = "#ffdb01";
        info2.prize_name = "福林橄榄油";
        list.add(info2);

        LuckItemInfo info3 = new LuckItemInfo();
        info3.color = "#ff9600";
        info3.prize_name = "200积分";
        list.add(info3);

        LuckItemInfo info4 = new LuckItemInfo();
        info4.color = "#ffdb01";
        info4.prize_name = "果酒";
        list.add(info4);

        LuckItemInfo info5 = new LuckItemInfo();
        info5.color = "#ff9600";
        info5.prize_name = "10积分";
        list.add(info5);
        luckBean.details = list;
        luckView.loadData(luckBean);

        luckView.setLuckPanListener(new LuckPanListener() {
            @Override
            public void onStartLottery() {
                Toast.makeText(LuckPanTest.this, "抽奖开始", Toast.LENGTH_SHORT).show();
                luckView.stopIndex(index++%6);
            }

            @Override
            public void onStopLottery(LuckItemInfo info) {
                Toast.makeText(LuckPanTest.this, info.prize_name, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
