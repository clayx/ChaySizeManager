package com.chay.test.chaysizemanager.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Chay on 2016/11/10.
 */

public class AbtainTwoWatcher implements TextWatcher {

    private OnSizeItemViewListener listener;

    public AbtainTwoWatcher() {
    }

    public AbtainTwoWatcher(OnSizeItemViewListener listener) {
        this.listener = listener;
    }

    public void setListener(OnSizeItemViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String temp = s.toString();
        int posDot = temp.indexOf(".");
        if (posDot > 0) {
            if (temp.length() - posDot - 1 > 1) {
                s.delete(posDot + 2, posDot + 3);
            }
        }
        if (listener != null) {
            listener.changeSizeItemView(s.toString());
        }

    }

    //方便处理SizeSettingActivity的关联
    public interface OnSizeItemViewListener {
        void changeSizeItemView(String num);
    }
}
