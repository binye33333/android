package com.teach.yo.codeshop.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 说明：
 *
 * @author chenyou
 * @date 2016/04/10
 */
public class TeaApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

    }
}
