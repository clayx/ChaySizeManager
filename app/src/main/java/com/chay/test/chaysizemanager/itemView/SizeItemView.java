package com.chay.test.chaysizemanager.itemView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.util.Preconditions;

/**
 * Created by Chay on 2016/11/9.
 */

public class SizeItemView extends RelativeLayout {

    private String leftStringName;
    private String rightStringName;
    private int leftDrawable;

    private ImageView size_itemview_left_iv;
    private TextView size_itemview_left_tv;
    private TextView size_itemview_right_tv;
    private ImageView size_itemview_right_iv;

    public SizeItemView(Context context) {
        this(context, null);
    }

    public SizeItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SizeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.sizeItemView);
        leftStringName = ta.getString(R.styleable.sizeItemView_leftStringName);
        rightStringName = ta.getString(R.styleable.sizeItemView_rightStringName);
        leftDrawable = ta.getResourceId(R.styleable.sizeItemView_leftDrawable, 0);

        ta.recycle();
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.size_itemview_layout, this, true);
        size_itemview_left_iv = (ImageView) findViewById(R.id.size_itemview_left_iv);
        size_itemview_right_iv = (ImageView) findViewById(R.id.size_itemview_right_iv);
        size_itemview_left_tv = (TextView) findViewById(R.id.size_itemview_left_tv);
        size_itemview_right_tv = (TextView) findViewById(R.id.size_itemview_right_tv);

        size_itemview_left_tv.setText(Preconditions.nullToEmpty(leftStringName));
        size_itemview_right_tv.setText(Preconditions.nullToEmpty(rightStringName));
        if (leftDrawable != 0) {
            size_itemview_left_iv.setBackgroundResource(leftDrawable);
        }
    }

    public void setRightDrawable(int right) {
        size_itemview_right_iv.setImageResource(right);
    }

    //获得ItemView左面文字
    public String getLeftStringName() {
        return leftStringName;
    }

    //在代码里设置左面文字
    public void setLeftText(String left) {
        size_itemview_left_tv.setText(Preconditions.nullToEmpty(left));
    }

    //在代码里设置右面文字
    public void setRightText(String right) {
        size_itemview_right_tv.setText(Preconditions.nullToEmpty(right));
    }

    //在代码里设置左面图片
    public void setLeftDrawable(int left) {
        if (leftDrawable != 0) {
            size_itemview_left_iv.setBackgroundResource(left);
        }
    }

    //获取右面文字
    public String getRightText() {
        return Preconditions.nullToEmpty(size_itemview_right_tv.getText().toString());
    }

}
