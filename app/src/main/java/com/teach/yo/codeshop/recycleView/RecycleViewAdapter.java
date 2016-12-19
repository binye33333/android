package com.teach.yo.codeshop.recycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teach.yo.codeshop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyou729 on 16/12/19.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.InnerHolder> {


    private Context mContext;
    private List<String> data = new ArrayList<>();

    public RecycleViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<String> list) {
        data.clear();
        addData(list);
    }


    public void addData(List<String> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_test_style2_layout, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(InnerHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public InnerHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item_tv2);
        }
    }
}
