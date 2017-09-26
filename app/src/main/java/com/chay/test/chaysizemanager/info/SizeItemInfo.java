package com.chay.test.chaysizemanager.info;

import com.chay.test.chaysizemanager.R;

import java.io.Serializable;

/**
 * Created by Chay on 2016/11/15.
 */

public class SizeItemInfo implements Serializable {

    private String areaFlag;

    private String value;

    private String cateName;

    public String getAreaFlag() {
        return areaFlag;
    }

    public void setAreaFlag(String areaFlag) {
        this.areaFlag = areaFlag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getCountryFlag(){
        int flag = 0;
        if (areaFlag.equals("CN")){
            flag = R.drawable.sizeitem_china;
        }
        if (areaFlag.equals("INTL")){
            flag = R.drawable.sizeitem_international;
        }
        return flag;
    }
}
