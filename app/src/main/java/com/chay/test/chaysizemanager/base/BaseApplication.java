package com.chay.test.chaysizemanager.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.chay.test.chaysizemanager.info.DaoMaster;
import com.chay.test.chaysizemanager.info.DaoSession;

/**
 * Created by Chay
 * Date on 2017/9/26.
 */

public class BaseApplication extends Application {

    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    /**
     * 配置数据库
     */
    public void setupDatabase() {
        //创建表名为"size_manager"的数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"size_manager.db",null);
        //获取可写的数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant(){
        return daoSession;
    }
}
