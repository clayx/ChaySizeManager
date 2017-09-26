package com.chay.test.chaysizemanager.util.model;

import com.chay.test.chaysizemanager.util.Preconditions;
import com.chay.test.chaysizemanager.util.bean.CategorySizeBean;
import com.chay.test.chaysizemanager.util.bean.WheelViewData;
import com.chay.test.chaysizemanager.util.bean.WheelViewInfo;

import java.util.ArrayList;

/**
 * Created by Chay on 2016/11/11.
 */

public class WheelInfoUtils {

    //将数据源对象转成需要的对象
    public static WheelViewInfo transfrom(CategorySizeBean.CataDataBean.ShowSizeBean bean) {
        WheelViewInfo info = null;
        if (bean != null) {
            String name = Preconditions.nullToEmpty(bean.getName());
            String type = Preconditions.nullToEmpty(bean.getType());
            ArrayList<WheelViewData> sizeList = new ArrayList<>();
            if (bean.getSizeList() != null && bean.getSizeList().size() > 0) {
                for (int i = 0; i < bean.getSizeList().size(); i++) {
                    WheelViewData data = new WheelViewData(bean.getSizeList().get(i));
                    sizeList.add(data);
                }
            }
            info = new WheelViewInfo(type,name,sizeList);
        }
        return info;
    }

}
