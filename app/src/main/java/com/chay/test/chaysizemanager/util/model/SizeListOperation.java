package com.chay.test.chaysizemanager.util.model;

import android.text.TextUtils;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chay on 2016/11/8.
 * 对于sizeList的 一些操作
 * <p>
 * 可以支持  如下数据的查找
 * 1、  {"0-24", "0-24",  "24-24.5", "24.5-25"}  //全部是范围数据
 * 2、  {"S", "M", "M", "L", "L", "XL", "XL", "XXL"} //全部是字符串
 * 3、  {"0-24", "0-24",  "24-24.5", "26"}//范围数据，数值 混合
 * 4、  {"0-24", "0-24",  "24-24.5", "26","XXL","XL"}//范围数据，数值、字符类型  混合
 */

public class SizeListOperation {
    private List<String> sizeList;/*需要初始化列表*/
    private String findSize;/*需要查找的值*/
    private final int ERROR_IDX = -1;/*没有查询到返回的索引*/

    public SizeListOperation(List<String> sizeList) {
        this.sizeList = sizeList;
    }

    public int findIndex(String find) {
        if (TextUtils.isEmpty(find) || sizeList == null || sizeList.isEmpty()) {
            return ERROR_IDX;
        }
        this.findSize = find;
        /*遍历判断*/
        if (!isSizeNumber(this.findSize)) {
            return findStr();
        } else {
            return findNumber();
        }

    }

    private int findStr() {
        int sizeLenth = sizeList.size();
        for (int i = 0; i < sizeLenth; i++) {
            if (sizeList.get(i).toLowerCase(Locale.US).equalsIgnoreCase(findSize.toLowerCase(Locale.US))) {
                return i;
            }
        }
        return ERROR_IDX;
    }

    private int findNumber() {

        int sizeLenth = sizeList.size();
        for (int index = 0; index < sizeLenth; index++) {
            if (isInCurrentPositionSize(index)) {
                return index;
            }
        }
        return ERROR_IDX;
    }

    private boolean isInCurrentPositionSize(int position) {
        float findNumber = Float.parseFloat(findSize);
        if (isRange(sizeList.get(position))) {
            //一个区间范围，进行判断
            float[] range = parseRange(sizeList.get(position));
            if (range != null) {
                return (isInRange(range[0], range[1], findNumber));
            }
        } else if (isSizeNumber(sizeList.get(position))) {
            //直接转成float 进行判断是否相等
            return Float.parseFloat(sizeList.get(position)) == findNumber;
        }
        return false;
    }

    /**
     * 目前只支持  完全符合格式的 例如 "0-100"  ,"0.44-199.3"
     *
     * @param rangStr
     * @return
     */
    private float[] parseRange(String rangStr) {
        String[] parserRange = rangStr.split("-");
        if (parserRange.length == 2) {
            if (!isSizeNumber(parserRange[0]) || !isSizeNumber(parserRange[1])) {
                return null;
            }
            return new float[]{Float.parseFloat(parserRange[0]), Float.parseFloat(parserRange[1])};
        } else {
            return null;
        }
    }

    private boolean isRange(String item) {
        return item.contains("-");
    }

    private boolean isInRange(float min, float max, float find) {
        return (find > min) && (find <= max);

    }

    private boolean isSizeNumber(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+[.]*[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是连续重复
     *
     * @param firstIdx
     * @return
     */
    public boolean isRepeatConcequence(int firstIdx) {
        String item = sizeList.get(firstIdx);
        //是否到了末尾
        if (sizeList.size() <= firstIdx + 1) {
            return false;
        }
        return sizeList.get(firstIdx + 1).equalsIgnoreCase(item);
    }
}
