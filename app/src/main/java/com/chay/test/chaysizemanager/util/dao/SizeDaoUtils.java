package com.chay.test.chaysizemanager.util.dao;

import com.chay.test.chaysizemanager.base.BaseApplication;
import com.chay.test.chaysizemanager.info.SizeDaoInfo;

import java.util.List;

/**
 * Created by Chay
 * Date on 2017/9/26.
 * <p>
 * 本地数据库查询Utils
 * </p>
 */

public class SizeDaoUtils {

    //添加数据，如果有重复的则直接覆盖
    public static void insertSizeDate(SizeDaoInfo info) {
        BaseApplication.getDaoInstant().getSizeDaoInfoDao().insertOrReplace(info);
    }

    //删除数据
    public static void deleteSizeDate(Long key) {
        BaseApplication.getDaoInstant().getSizeDaoInfoDao().deleteByKey(key);
    }

    //更新数据
    public static void updateSizeDate(SizeDaoInfo info) {
        BaseApplication.getDaoInstant().getSizeDaoInfoDao().update(info);
    }

    //查询全部数据
    public static List<SizeDaoInfo> queryAll() {
        return BaseApplication.getDaoInstant().getSizeDaoInfoDao().loadAll();
    }

}
