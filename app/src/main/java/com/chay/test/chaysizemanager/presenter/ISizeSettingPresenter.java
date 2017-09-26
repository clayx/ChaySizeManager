package com.chay.test.chaysizemanager.presenter;

import android.app.Activity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.info.SizeItemInfo;
import com.chay.test.chaysizemanager.info.UserSizeDetailInfo;
import com.chay.test.chaysizemanager.itemView.SizeItemView;
import com.chay.test.chaysizemanager.util.AbtainTwoWatcher;
import com.chay.test.chaysizemanager.util.CharsetUtils;
import com.chay.test.chaysizemanager.util.InputFilterMinMax;
import com.chay.test.chaysizemanager.util.Preconditions;
import com.chay.test.chaysizemanager.util.bean.CategorySizeBean;
import com.chay.test.chaysizemanager.util.bean.Constant;
import com.chay.test.chaysizemanager.util.bean.SizeTableBean;
import com.chay.test.chaysizemanager.util.model.SizeListOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chay on 2016/11/15.
 */

public class ISizeSettingPresenter {

    //获取每个围的对应的每个item数据
    public List<String> getCateList(SizeTableBean.PersonSizeDataBean personSizeDataBean, String cateName) {
        List<String> cateList = new ArrayList<>();
        List<CategorySizeBean> list = personSizeDataBean.getSizeData();
        for (int i = 0; i < list.size(); i++) {
            List<CategorySizeBean.CataDataBean.BodyMeasureBean> data =
                    list.get(i).getCataData().getBodyMeasure();
            for (int j = 0; j < data.size(); j++) {
                if (data.get(j).getType().equals(cateName)) {
                    cateList.add(list.get(i).getName());
                    break;
                }
            }
        }
        return cateList;
    }

    //将每个围的数据加入到Map中
    public void initCateList(Map<String, List<String>> cateMap, SizeTableBean.PersonSizeDataBean personSizeDataBean) {
        cateMap.put(Constant.BodyType.TYPE_BUST,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_BUST));
        cateMap.put(Constant.BodyType.TYPE_WAIST,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_WAIST));
        cateMap.put(Constant.BodyType.TYPE_HIP,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_HIP));
        cateMap.put(Constant.BodyType.TYPE_HEAD,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_HEAD));
        cateMap.put(Constant.BodyType.TYPE_NECK,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_NECK));
        cateMap.put(Constant.BodyType.TYPE_SHOULDER,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_SHOULDER));
        cateMap.put(Constant.BodyType.TYPE_HAND,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_HAND));
        cateMap.put(Constant.BodyType.TYPE_THIGH,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_THIGH));
        cateMap.put(Constant.BodyType.TYPE_FOOT,
                getCateList(personSizeDataBean, Constant.BodyType.TYPE_FOOT));
    }

    //默认情况下获取默认的国家信息
    public void chooseDefaultCountry(SizeItemInfo info, CategorySizeBean categorySizeBean) {
        if (categorySizeBean.getDefaultCountry().equals("INTL")) {
            info.setAreaFlag("INTL");
        } else {
            info.setAreaFlag("CN");
        }
    }

    //比较两个数据是否一样
    public boolean compareMap(Map<String, String> originalData, Map<String, String> compareData) {
        boolean isSame = true;
        Set<String> set = originalData.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String origial = originalData.get(key);
            String compare = compareData.get(key);
            if (!compare.equals(origial)) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }

    //如果当前的FoucsView是EditTest，则清除焦点
    public void clearCurrentFocus(Activity activity) {
        if (activity.getCurrentFocus() instanceof EditText) {
            EditText editText = (EditText) activity.getCurrentFocus();
            clearEditTextDot(editText);
            editText.setSelection(editText.length());
        }

    }

    //删除最后一位的"."和删除最后的".0"
    public void clearEditTextDot(EditText editText) {
        String content = Preconditions.nullToEmpty(editText.getText().toString());
        content = CharsetUtils.subZeroAndDot(content);
        editText.setText(content);
    }

    //如果在返回到本界面时，对一些数据进行了修改，修改当前的获得焦点View的EditText的光标位置
    public void setCurrentFocusPos(Activity activity) {
        if (activity.getCurrentFocus() instanceof EditText) {
            EditText editText = (EditText) activity.getCurrentFocus();
            editText.setSelection(editText.length());
        }
    }

    //重置EditText内容
    public void resetListData(Map<String, EditText> editMap) {
        Set<String> set = editMap.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            EditText editText = editMap.get(key);
            String content = Preconditions.nullToEmpty(editText.getText().toString());
            editText.setText(content);
        }
    }

    //如果EditText最后一位为. 则在EidtText失去焦点的时候，删除
    public void deleteEditLastDot(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    clearEditTextDot(editText);
                }
            }
        });
    }

    //设置相关的EditText的View条件
    public void setEditTextFilter(EditText editText, AbtainTwoWatcher.OnSizeItemViewListener listener) {
        editText.setFilters(new InputFilter[]{new InputFilterMinMax("1", "999")});
        editText.addTextChangedListener(new AbtainTwoWatcher(listener));
    }

    //初始化每个的最大数据值
    public Map<String, String> initMaxMap(Map<String, EditText> editMap,
                                          Map<String, Map<String, String>> maxNumMap,
                                          SizeTableBean.PersonSizeDataBean personSizeDataBean,
                                          String cateName) {
        List<CategorySizeBean> list = personSizeDataBean.getSizeData();
        for (int i = 0; i < list.size(); i++) {
            List<CategorySizeBean.CataDataBean.BodyMeasureBean> data =
                    list.get(i).getCataData().getBodyMeasure();
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < data.size(); j++) {
                String ok = editMap.get(data.get(j).getType()).getText().toString();
                map.put(data.get(j).getType(),
                        Preconditions.nullToEmpty(ok));
            }
            maxNumMap.put(list.get(i).getName(), map);
        }

        Map<String, String> maxMap = maxNumMap.get(cateName);
        float b = 0.0f;
        String type = cateName;
        int index = 0;
        Set<String> set = maxMap.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            float a = 0.0f;
            String key = iterator.next();
            String next = maxMap.get(key);
            List<CategorySizeBean> bean = personSizeDataBean.getSizeData();
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < bean.size(); i++) {
                if (bean.get(i).getName().equals(cateName)) {
                    List<CategorySizeBean.CataDataBean.BodyMeasureBean> showSizeBeen =
                            bean.get(i).getCataData().getBodyMeasure();

                    for (int j = 0; j < showSizeBeen.size(); j++) {
                        if (showSizeBeen.get(j).getType().equals(key)) {
                            stringList = showSizeBeen.get(j).getSizeList();
                            break;
                        }
                    }
                }
            }
            if (Preconditions.isNullOrEmpty(next)) {
                next = "-1";
            }
            if (next.endsWith(".")) {
                int posDot = next.indexOf(".");
                next = next.substring(0, posDot);
            }
            a = Float.parseFloat(next);
            SizeListOperation operation = new SizeListOperation(stringList);
            int currentIndex = 0;
            if (a >= 1) {
                currentIndex = operation.findIndex(a + "");
            }
            if (index <= currentIndex) {
                index = currentIndex;
                type = key;
                if (a != -1) {
                    b = a;
                }
            }

        }

        Map<String, String> maxValueMap = new HashMap<>();
        maxValueMap.put(type, b + "");

        return maxValueMap;

    }

    //获得当前输出框的值
    public String getCurrentValue(Map<String, EditText> editMap, String cateName) {
        String currentValue = editMap.get(cateName).getText().toString();
        currentValue = CharsetUtils.subZeroAndDot(currentValue);
        return currentValue;
    }

    //获得itemView的默认国旗资源
    public int getCountryFlag(String country) {
        if (country.equals("INTL")) {
            return R.drawable.sizeitem_international;
        } else {
            return R.drawable.sizeitem_china;
        }
    }

    //重新处理SizeItem数据
    public void dealSizeItem(Map<String, SizeItemInfo> sizeItemInfoMap,
                             Map<String, SizeItemView> sizeItemViewMap,
                             UserSizeDetailInfo.SizeDetailInfo detailInfo) {
        List<UserSizeDetailInfo.SizeDetailInfo.SizeAreaInfo> list = detailInfo.getList();
        for (int i = 0; i < list.size(); i++) {
            SizeItemInfo info = new SizeItemInfo();
            info.setAreaFlag(list.get(i).getAreaFlag());
            info.setCateName(list.get(i).getCateName());
            resetSizeItemData(info.getCateName(),sizeItemViewMap, detailInfo, info);
            sizeItemInfoMap.put(info.getCateName(), info);
        }
    }

    //重置部分SizeItemData
    public void resetSizeItemData(String cateName,
                                  Map<String, SizeItemView> sizeItemViewMap,
                                  UserSizeDetailInfo.SizeDetailInfo detailInfo,
                                  SizeItemInfo info) {
        if (cateName.equals(Constant.SizeItemType.TYPE_HAT)) {
            info.setValue(Preconditions.nullToEmpty(detailInfo.getHat()));
            if (!Preconditions.isNullOrEmpty(detailInfo.getHat())) {
                mapSizeItemData(sizeItemViewMap,Constant.SizeItemType.TYPE_HAT, info);
            }
        }
        if (cateName.equals(Constant.SizeItemType.TYPE_COAT)) {
            info.setValue(Preconditions.nullToEmpty(detailInfo.getCoat()));
            if (!Preconditions.isNullOrEmpty(detailInfo.getCoat())) {
                mapSizeItemData(sizeItemViewMap,Constant.SizeItemType.TYPE_COAT, info);
            }
        }
        if (cateName.equals(Constant.SizeItemType.TYPE_SHIRT)) {
            info.setValue(Preconditions.nullToEmpty(detailInfo.getShirt()));
            if (!Preconditions.isNullOrEmpty(detailInfo.getShirt())) {
                mapSizeItemData(sizeItemViewMap,Constant.SizeItemType.TYPE_SHIRT, info);
            }
        }
        if (cateName.equals(Constant.SizeItemType.TYPE_TROUSERS)) {
            info.setValue(Preconditions.nullToEmpty(detailInfo.getTrousers()));
            if (!Preconditions.isNullOrEmpty(detailInfo.getTrousers())) {
                mapSizeItemData(sizeItemViewMap,Constant.SizeItemType.TYPE_TROUSERS, info);
            }
        }
        if (cateName.equals(Constant.SizeItemType.TYPE_UNDERPANTS)) {
            info.setValue(Preconditions.nullToEmpty(detailInfo.getUnderpants()));
            if (!Preconditions.isNullOrEmpty(detailInfo.getUnderpants())) {
                mapSizeItemData(sizeItemViewMap,Constant.SizeItemType.TYPE_UNDERPANTS, info);
            }
        }
        if (cateName.equals(Constant.SizeItemType.TYPE_SHOES)) {
            info.setValue(Preconditions.nullToEmpty(detailInfo.getShoes()));
            if (!Preconditions.isNullOrEmpty(detailInfo.getShoes())) {
                mapSizeItemData(sizeItemViewMap,Constant.SizeItemType.TYPE_SHOES, info);
            }
        }
        if (cateName.equals(Constant.SizeItemType.TYPE_GLOVE)) {
            info.setValue(Preconditions.nullToEmpty(detailInfo.getGlove()));
            if (!Preconditions.isNullOrEmpty(detailInfo.getGlove())) {
                mapSizeItemData(sizeItemViewMap,Constant.SizeItemType.TYPE_GLOVE, info);
            }
        }
    }

    //将数据映射到View中
    public void mapSizeItemData(Map<String, SizeItemView> sizeItemViewMap,
                                String key, SizeItemInfo info) {
        sizeItemViewMap.get(key).setRightDrawable(info.getCountryFlag());
        sizeItemViewMap.get(key).setRightText(info.getValue());
    }
}
