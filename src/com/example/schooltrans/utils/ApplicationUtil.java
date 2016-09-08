package com.example.schooltrans.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by hxg on 2016/8/10.
 */
public class ApplicationUtil extends Application{
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
