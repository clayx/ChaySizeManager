package com.chay.test.chaysizemanager.pop;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chay.test.chaysizemanager.R;

/**
 * Created by Chay on 2016/11/8.
 * 模仿IOS的popupwindow
 */
public class CommButtonDlg extends PopupWindow {
    private TextView textMessage;
    private TextView title;
    private TextView bnConfirm;
    private TextView bnCancel;
    private View mMenuView;

    /**
     * 常用无标题  提示框
     * <p>
     * --------------
     * 提示信息
     * --------------
     * 取 消| 确 定
     *
     * @param context
     * @param textMsg
     * @param itemsOnClick
     */
    public CommButtonDlg(Activity context, String textMsg, OnClickListener itemsOnClick, boolean isOnclick) {

        this(context, null, textMsg, null, null, itemsOnClick, null, true, isOnclick);

    }


    /**
     * 带标题  --左右btn提示修改
     * <p>
     * 右边点击 监听 ，左边点击取消  ，边沿点击消失
     *
     * @param context
     * @param textMsg
     * @param leftMsg
     * @param rigthMsg
     * @param itemsOnClick
     */
    public CommButtonDlg(final Activity context, String titleMsg, String textMsg, String leftMsg, String rigthMsg, OnClickListener itemsOnClick, boolean isOnclick) {
        this(context, titleMsg, textMsg, leftMsg, rigthMsg, itemsOnClick, null, true, isOnclick);
    }

    /**
     * 带标题   ---左右btn提示修改  左右点击都监听
     * <p>
     * 边沿点击可以选择隐藏于不隐藏
     *
     * @param context
     * @param titleMsg
     * @param textMsg
     * @param leftMsg
     * @param rigthMsg
     * @param rightOnclick         右边按钮点击
     * @param leftOnclick          左边按钮点击
     * @param canDissmisOtherClick 是否可以空白处点击消失
     */
    public CommButtonDlg(final Activity context, String titleMsg, String textMsg, String leftMsg,
                         String rigthMsg, OnClickListener rightOnclick, OnClickListener leftOnclick,
                         boolean canDissmisOtherClick, boolean isOnclick) {
        this(context, titleMsg, textMsg, leftMsg, rigthMsg, rightOnclick, leftOnclick, canDissmisOtherClick, isOnclick, false);
    }

    /**
     * 带标题   ---左右btn提示修改  左右点击都监听
     * <p>
     * 边沿点击可以选择隐藏于不隐藏
     *
     * @param context
     * @param titleMsg
     * @param textMsg
     * @param leftMsg
     * @param rigthMsg
     * @param rightOnclick         右边按钮点击
     * @param leftOnclick          左边按钮点击
     * @param canDissmisOtherClick 是否可以空白处点击消失
     * @param isHighligtCompare    是否需要显示强烈对比颜色
     */
    public CommButtonDlg(final Activity context, String titleMsg, String textMsg, String leftMsg,
                         String rigthMsg, OnClickListener rightOnclick, OnClickListener leftOnclick,
                         boolean canDissmisOtherClick, boolean isOnclick, boolean isHighligtCompare) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.comm_button_alert_dlg, null);
        title = (TextView) mMenuView.findViewById(R.id.title);
        textMessage = (TextView) mMenuView.findViewById(R.id.textMessage);
        bnConfirm = (TextView) mMenuView.findViewById(R.id.bnConfirm);
        bnCancel = (TextView) mMenuView.findViewById(R.id.bnCancel);
        //需要进行强烈的对比颜色
        if (isHighligtCompare) {
            bnCancel.setTextColor(Color.parseColor("#A1A1A1"));
            bnConfirm.setTextColor(Color.parseColor("#d2041f"));
        }

        if (!TextUtils.isEmpty(rigthMsg)) {
            bnConfirm.setText(rigthMsg);
        }

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams mLp = context.getWindow().getAttributes();
                mLp.alpha = 1f;
                context.getWindow().setAttributes(mLp);
            }
        });

        if (isOnclick) {
            bnConfirm.setOnClickListener(rightOnclick);

            //左边按钮的监听
            if (leftOnclick != null) {
                bnCancel.setOnClickListener(leftOnclick);
            } else {
                bnCancel.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        }
        if (!TextUtils.isEmpty(leftMsg)) {
            bnCancel.setText(leftMsg);
        }
        if (TextUtils.isEmpty(textMsg)) {
            textMessage.setVisibility(View.GONE);
        } else {
            textMessage.setText(textMsg);
            if (textMessage.getLineCount() == 1) {
                textMessage.setGravity(Gravity.CENTER);
            }
        }
        if (TextUtils.isEmpty(titleMsg)) {
            title.setVisibility(View.GONE);
        } else {
            title.setText(titleMsg);
        }

        this.setContentView(mMenuView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        this.setFocusable(false);
        this.setBackgroundDrawable(new BitmapDrawable());
        if (canDissmisOtherClick) {
            mMenuView.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        dismiss();
                    }
                    return true;
                }
            });
        }
    }

}
