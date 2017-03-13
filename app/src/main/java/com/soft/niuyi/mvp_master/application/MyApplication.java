package com.soft.niuyi.mvp_master.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.soft.niuyi.mvp_master.MainActivity;


/**
 * 作者：${牛毅} on 2016/7/4 16:15
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());//全局异常捕获处理
    }

    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 未能捕获的异常处理
     */
    class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            ex.printStackTrace();

            Intent intent = new Intent(MyApplication.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyApplication.this.startActivity(intent);

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);//非正常退出
        }
    }
}
