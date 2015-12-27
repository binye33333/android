package com.teach.yo.toollibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/27.
 */
public abstract class TeaAdapter<T> extends BaseAdapter {

    protected List<T> mList;
    private Context mContext;

    public TeaAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    public TeaAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }


    public void setData(List<T> list) {
        mList.clear();
        mList.addAll(list) ;
        notifyDataSetChanged();
    }


    public void addData(List<T> list) {
        mList.addAll(list) ;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList==null?null:mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList==null?0:position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeaViewHolder<T> holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(getItemLayoutId(), null);
            holder = getViewHolder();
            holder.init(convertView);
            convertView.setTag(holder);
        } else {
            holder = (TeaViewHolder<T>) convertView.getTag();
        }
        holder.setItemData(mList.get(position), position);

        return convertView;
    }

    protected abstract int getItemLayoutId();

    protected abstract TeaViewHolder<T> getViewHolder();

}
