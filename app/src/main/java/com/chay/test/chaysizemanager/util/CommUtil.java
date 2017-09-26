package com.chay.test.chaysizemanager.util;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Chay
 * Date on 2017/9/26.
 */

public class CommUtil {

    /**
     * @Description: 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @author Beta
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @Description: 得到屏幕宽度
     * @author Beta
     */
    public static int getScreenW(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

}
