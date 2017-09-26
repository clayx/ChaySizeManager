package com.chay.test.chaysizemanager.util;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * Created by Chay
 * Date on 2017/9/26.
 */

public class JsonUtils {

    public static final <T> T parseObject(@NonNull String text, @NonNull Class<T> clazz) {
        if (text == null || clazz == null) return null;
        try {
            return JSON.parseObject(text, clazz);
        } catch (JSONException jexp) {
            return null;
        }
    }

}
