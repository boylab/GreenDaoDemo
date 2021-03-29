package com.boylab.greendaodemo;

import android.app.Application;

import com.boylab.greendaodemo.db.manager.DBManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化数据库
        DBManager.newInstance().setDataBase(this);
    }
}
