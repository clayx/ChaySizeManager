package com.chay.test.chaysizemanager.pop;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.listener.SexClickListener;

import java.util.ArrayList;

/**
 * Created by Chay
 * Date on 2017/9/26.
 */

public class SexOrHeadPopupWindow {

    private Activity mContext;

    /**
     * @param context
     */
    public SexOrHeadPopupWindow(final Activity context) {
        this.mContext = context;
    }

    private static final int MODIFY_SEX = 1;
    private int modifyFlag;
    private String tempSex;

    public static final int SIZE_TAKE_PHOTO = 8001;
    public static final int SIZE_CHOOSE_IMAGES = 8002;
    public static final int SIZE_CUT_PICTURE = 8003;

    // 弹出性别选择框
    public void showSexDialog(final SexClickListener listener) {

        final Dialog dialog = new Dialog(mContext, R.style.sex_dialog);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("男");
        list.add("女");

        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.size_setting_sex_dialog_layout, null);
        ListView listView = (ListView) dialogView.findViewById(R.id.size_setting_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                modifyFlag = MODIFY_SEX;
                if (i == 0) {
                    tempSex = "男";
                } else {
                    tempSex = "女";
                }
                if (listener != null) {
                    listener.click(tempSex);
                }
                dialog.cancel();
            }
        });
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int arg0) {
                return list.get(arg0);
            }

            @Override
            public long getItemId(int arg0) {
                return arg0;
            }

            @Override
            public View getView(int position, View view, ViewGroup arg2) {
                LinearLayout layout = new LinearLayout(mContext);
                layout.setGravity(Gravity.CENTER_VERTICAL);
                layout.setBackgroundResource(R.drawable.size_setting_sex_selector);

                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView text2 = new TextView(mContext);
                text2.setTextColor(mContext.getResources().getColor(R.color.black));
                text2.setText(list.get(position));
                text2.setTextSize(16);
                lp2.bottomMargin = 30;
                lp2.leftMargin = 20;
                lp2.topMargin = 30;
                layout.addView(text2, lp2);
                return layout;
            }
        });
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
