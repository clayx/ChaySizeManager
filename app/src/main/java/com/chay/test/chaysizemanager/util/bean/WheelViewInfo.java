package com.chay.test.chaysizemanager.util.bean;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.util.model.SizeListOperation;
import com.chay.test.chaysizemanager.wheelView.model.IMoreOptionsData;
import com.chay.test.chaysizemanager.wheelView.model.IPickerViewData;

import java.util.ArrayList;

/**
 * Created by Chay on 2016/11/11.
 */

public class WheelViewInfo implements IMoreOptionsData {

    private String type;
    private String name;
    private ArrayList<WheelViewData> sizeList = new ArrayList<>();
    private ArrayList<String> originalList = new ArrayList<>();//储存，作为原始数据用来查询索引
    private ArrayList<String> curNameList = new ArrayList<>();//存储，作为当前名字用来匹配索引


    public WheelViewInfo(String type, String name, ArrayList<WheelViewData> sizeList) {
        this.type = type;
        this.name = name;
        if (sizeList != null && !sizeList.isEmpty()) {
            backOrignalData(sizeList);
            reMoveDuplicateData(sizeList);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<WheelViewData> getSizeList() {
        return sizeList;
    }

    @Override
    public String getCountryName() {
        return name;
    }

    @Override
    public int getCountryRes() {
        int res = 0;
        if (type.equals("INTL")) {
            res = R.drawable.size_country_intl;
        }
        if (type.equals("EU")) {
            res = R.drawable.size_country_eu;
        }
        if (type.equals("US")) {
            res = R.drawable.size_country_us;
        }
        if (type.equals("UK")) {
            res = R.drawable.size_country_uk;
        }
        if (type.equals("FR")) {
            res = R.drawable.size_country_fr;
        }
        if (type.equals("IT")) {
            res = R.drawable.size_country_it;
        }
        if (type.equals("CN")) {
            res = R.drawable.size_country_cn;
        }
        if (type.equals("CHI")) {
            res = R.drawable.size_country_chi;
        }
        if (type.equals("INCH")) {
            res = R.drawable.size_country_inch;
        }
        if (type.equals("IV")) {
            res = R.drawable.size_country_iv;
        }
        return res;
    }

    private void backOrignalData(ArrayList<WheelViewData> sizeList) {
        for (WheelViewData item : sizeList) {
            originalList.add(item.getPickerViewText());
        }
    }

    @Override
    public ArrayList<IPickerViewData> getData() {
        ArrayList<IPickerViewData> datas = new ArrayList<>();
        if (sizeList != null && sizeList.size() > 0) {
            for (int i = 0; i < sizeList.size(); i++) {
                datas.add(sizeList.get(i));
            }
        }
        return datas;
    }

    /**
     * 数组去重处理
     *
     * @param dataList
     */
    private void reMoveDuplicateData(ArrayList<WheelViewData> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            if (i == 0) {
                sizeList.add(dataList.get(i));
            } else {
                if (!dataList.get(i).getPickerViewText().equalsIgnoreCase(dataList.get(i - 1).getPickerViewText())) {
                    sizeList.add(dataList.get(i));
                }
            }
        }

        //保存一份当前名字的列表，用于查找索引
        for (WheelViewData data : sizeList) {
            curNameList.add(data.getPickerViewText());
        }

    }

    /**
     * 从外部idx--》映射成 需要显示的滚轮的 索引
     *
     * @param outIndex
     * @return
     */
    public int mapInnerIdx(int outIndex) {
        String name = originalList.get(outIndex);
        SizeListOperation operation = new SizeListOperation(curNameList);
        return operation.findIndex(name);
    }

    /**
     * 从滚轮的索引映射到 标准的index
     *
     * @param innerIndex
     * @return
     */
    public int mapOutterIdx(int innerIndex) {
        String curName = sizeList.get(innerIndex).getPickerViewText();
        SizeListOperation operation = new SizeListOperation(originalList);
        return operation.findIndex(curName);

    }

    @Override
    public boolean isRepeat(int outIndex) {
        SizeListOperation operation = new SizeListOperation(originalList);
        return operation.isRepeatConcequence(outIndex);
    }
}
