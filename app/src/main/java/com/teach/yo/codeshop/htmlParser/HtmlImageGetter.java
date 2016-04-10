package com.teach.yo.codeshop.htmlParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 说明：html 标签里面的图片下载器
 *
 * @author chenyou
 * @date 2016/04/04
 */
public class HtmlImageGetter implements Html.ImageGetter {

    // 展示Html 文字的 textView
    private TextView mTextView;

    public HtmlImageGetter(TextView textView) {
        mTextView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {

        ImageLoader loader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .build();

        File file = loader.getDiscCache().get(source);
        //如果已经有下载过的图片了，直接从磁盘缓存中加载，同步返回bitmap数据显示
        if (file != null && file.exists()) {
            try {

                BitmapDrawable bitmapDrawable = new BitmapDrawable(BitmapFactory.decodeStream(new FileInputStream(file)));
                //根据屏幕宽度计算边界，将图片显示在正中间
                return calculateBounds(bitmapDrawable);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        //本地没有下载过图片数据，异步从服务器下载
        loader.loadImage(source, options, new ImageLoadingListener() {
            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                //下载完成后重新设置html显示文本
                mTextView.setText(Html.fromHtml(HtmlTest.htmlContent, HtmlImageGetter.this, null));
            }


            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }


            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

        return null;
    }

    private BitmapDrawable calculateBounds(BitmapDrawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        int screenWidth = mTextView.getContext().getResources().getDisplayMetrics().widthPixels;
        int maxWidth = screenWidth * 3 / 4;
        if (width > maxWidth) {
            float ratio = (float) width / maxWidth;
            height = (int) (height / ratio);
            width = maxWidth;
        }

        int offset = (screenWidth - width) / 2;
        drawable.setBounds(offset, 0, width + offset,
                height);
        return drawable;
    }

}
