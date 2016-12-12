package com.teach.yo.codeshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teach.yo.codeshop.bean.FragmentInfo;

/**
 * Created by chenyou729 on 16/11/14.
 */

public class TextShowFragment extends Fragment {

    public static final String PARAM_KEY = "param_key";

    private TextView textView;
    private FragmentInfo mFragmentInfo;
    private int clickCount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        textView = new TextView(getContext());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                showText();

            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFragmentInfo = (FragmentInfo) getArguments().getSerializable(PARAM_KEY);
        showText();
    }

    public FragmentInfo getFragmentInfo() {
        return mFragmentInfo;
    }

    private void showText() {
        textView.setText(String.format("当前tab :%s\n 点击次数： %d", mFragmentInfo.name, clickCount));
    }
}
