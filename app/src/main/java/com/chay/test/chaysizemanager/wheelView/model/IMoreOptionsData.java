package com.chay.test.chaysizemanager.wheelView.model;

import java.util.ArrayList;

/**
 * Created by Chay on 2016/11/8.
 */

public interface IMoreOptionsData {

    String getCountryName();

    int getCountryRes();

    ArrayList<IPickerViewData> getData();

    int mapInnerIdx(int outIndex);

    int mapOutterIdx(int innerIndex);

    boolean isRepeat(int outIndex);/*是否是一对多的，也就是所谓的需要去重的数据*/


}
