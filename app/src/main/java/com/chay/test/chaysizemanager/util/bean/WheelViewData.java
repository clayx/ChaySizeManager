package com.chay.test.chaysizemanager.util.bean;

import com.chay.test.chaysizemanager.wheelView.model.IPickerViewData;

/**
 * Created by Chay on 2016/11/11.
 */

public class WheelViewData implements IPickerViewData {
    private String name = "";

    public WheelViewData(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
