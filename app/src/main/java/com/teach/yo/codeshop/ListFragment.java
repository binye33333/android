package com.teach.yo.codeshop;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.teach.yo.toollibrary.adapter.TeaAdapter;
import com.teach.yo.toollibrary.adapter.TeaViewHolder;
import com.teach.yo.toollibrary.baseAdapter.BaseAdapterHelper;
import com.teach.yo.toollibrary.view.pull.PullToRefreshBase;
import com.teach.yo.toollibrary.view.pull.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binye33333 on 2016/10/12.
 */

public class ListFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2 {

    PullToRefreshListView listView;
    ListAdapter mListAdapter;
    private FrameLayout frameLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_layout, container, false);
        listView = (PullToRefreshListView) rootView.findViewById(R.id.listView);
        listView.setOnRefreshListener(this);
        frameLayout = new FrameLayout(getContext());
        frameLayout.setMinimumHeight(480);
        listView.getRefreshableView().addHeaderView(frameLayout);
        return listView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && getView() != null) {
            BannerTabTest.banner.stopAutoScroll();
            ((ViewGroup) BannerTabTest.bannerLayout.getParent()).removeAllViews();
            BannerTabTest.bannerContainer.setVisibility(View.GONE);
            frameLayout.addView(BannerTabTest.bannerLayout);
            BannerTabTest.banner.startAutoScroll();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListAdapter = new ListAdapter(getContext());
        List<String> list = new ArrayList<>();
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        mListAdapter.setData(list);
        listView.setAdapter(mListAdapter);
    }

    public void setData() {
        mListAdapter.setData(getList());
    }

    public void addData() {
        mListAdapter.addData(getList());
    }

    @Override
    public void onPullDownToRefresh(final PullToRefreshBase refreshView) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                setData();
                refreshView.onRefreshComplete();
            }
        },2000);

    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase refreshView) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                addData();
                refreshView.onRefreshComplete();
            }
        },2000);
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        list.add("999999");
        return list;
    }


    class ListAdapter extends TeaAdapter<String> {


        public ListAdapter(Context context) {
            super(context);
        }

        @Override
        protected int getItemLayoutId() {
            return R.layout.item_fragment_list;
        }

        @Override
        protected TeaViewHolder<String> getViewHolder() {
            return new Holder();
        }

        class Holder extends TeaViewHolder<String> {


            @Override
            public void init(View view) {

            }

            @Override
            public void setItemData(String data, int position) {

            }
        }


    }


}
