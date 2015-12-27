package com.teach.yo.toollibrary.adapter;

import android.view.View;

/**
 * Created by Administrator on 2015/8/27.
 */
public abstract class TeaViewHolder<T> {

    public abstract void init(View view);

    public abstract void setItemData(T data, int position);
}
