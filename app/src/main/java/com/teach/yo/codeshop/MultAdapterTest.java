package com.teach.yo.codeshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.teach.yo.codeshop.bean.CommonBean;
import com.teach.yo.toollibrary.baseAdapter.BaseAdapterHelper;
import com.teach.yo.toollibrary.baseAdapter.MultiItemTypeSupport;
import com.teach.yo.toollibrary.baseAdapter.QuickAdapter;

import java.util.ArrayList;

/**
 * 作者：chenyo on 2016/3/13 20:31
 */
public class MultAdapterTest extends AppCompatActivity {


    private ListView mCommonListView;
    private ArrayList<CommonBean> mCommonListBean = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_common_listview_layout);
        mCommonListView = findView(R.id.test_common_lv);

        initData();
        initView();

    }


    private void initData() {

        for (int i = 0; i < 15; i++) {
            CommonBean bean = new CommonBean();
            if (i % 2 == 0) {
                bean.type = 1;
                bean.name = "小李";
                bean.description = "我是小李说的话";
            } else {
                bean.type = 2;
                bean.name = "小张";
                bean.description = "我是小张说的话";
            }
            mCommonListBean.add(bean);
        }

    }


    private void initView() {

        MultiItemTypeSupport<CommonBean> multiItemTypeSupport = new MultiItemTypeSupport<CommonBean>() {
            @Override
            public int getLayoutId(int position, CommonBean commonBean) {
                if (mCommonListBean.get(position).type == 1) {
                    return R.layout.item_test_style1_layout;
                } else {
                    return R.layout.item_test_style2_layout;
                }
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position, CommonBean commonBean) {
                return mCommonListBean.get(position).type;
            }
        };

        QuickAdapter<CommonBean> helper = new QuickAdapter<CommonBean>(this, mCommonListBean, multiItemTypeSupport) {


            @Override
            protected void convert(BaseAdapterHelper helper, CommonBean item) {
                if (item.type == 1) {
                    helper.setText(R.id.item_tv, item.description);
                } else {
                    helper.setText(R.id.item_tv2, item.description);
                }

            }
        };

        mCommonListView.setAdapter(helper);
    }


    private <T extends View> T findView(int id) {
        View view = findViewById(id);
        return (T) view;
    }


}
