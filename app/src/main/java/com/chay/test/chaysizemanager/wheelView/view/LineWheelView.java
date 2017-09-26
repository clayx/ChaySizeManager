package com.chay.test.chaysizemanager.wheelView.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.util.CommUtil;
import com.chay.test.chaysizemanager.wheelView.adapter.ArrayWheelAdapter;
import com.chay.test.chaysizemanager.wheelView.lib.WheelView;
import com.chay.test.chaysizemanager.wheelView.listener.OnItemSelectedListener;

import java.util.ArrayList;

/**
 * Created by Chay on 2016/11/8.
 */

public class LineWheelView<T> extends RelativeLayout {

    private TextView pickerview_country_name;
    private ImageView pickerview_country_iv;
    private WheelView pickerview_country_wheelview;

    private ImageView pickerview_line_iv;

    private ArrayList<T> data;

    int len = 12;

    public OnItemSelectedListener listener;

    private Context context;

    public LineWheelView(Context context) {
        this(context,null);
    }

    public LineWheelView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initViews(context);
    }

    private void initViews(Context context){
        LayoutInflater.from(context).inflate(R.layout.pickerview_line_options_layout,this,true);
        pickerview_country_name = (TextView) findViewById(R.id.pickerview_country_name);
        pickerview_country_iv = (ImageView) findViewById(R.id.pickerview_country_iv);
        pickerview_country_wheelview = (WheelView) findViewById(R.id.pickerview_country_wheelview);
        pickerview_line_iv = (ImageView) findViewById(R.id.pickerview_line_iv);
    }

    public void setData(ArrayList<T> data){
        this.data = data;
        if (data != null && data.size() > 0){
            pickerview_country_wheelview.setAdapter(new ArrayWheelAdapter(data, len));// 设置显示数据
            pickerview_country_wheelview.setCurrentItem(0);
        }
    }

    //设置分割线的展示
    public void setLineShow(boolean isShow){
        if (isShow){
            pickerview_line_iv.setVisibility(VISIBLE);
        }else {
            pickerview_line_iv.setVisibility(GONE);
        }
    }

    //设置国家/标识图标
    public void setIvRes(int res){
        if (res != 0){
            pickerview_country_iv.setImageResource(res);
        }
    }

    //设置国家/标识的名称
    public void setTvName(String name){
        if (name != null && name.length() > 0){
            pickerview_country_name.setText(name);
        }
    }

    //设置WheelView的选择的监听
    public void setOnItemSelectedListener(OnItemSelectedListener listener){
        this.listener = listener;
        if (this.listener != null){
            pickerview_country_wheelview.setOnItemSelectedListener(this.listener);
        }
    }

    //设置WheelView的字体大小
    public void setWheelSize(int size){
        pickerview_country_wheelview.setTextSize(size);
    }

    //设置WheelView是否循环滑动
    public void setWheelRecycle(boolean isReycler){
        pickerview_country_wheelview.setCyclic(isReycler);
    }

    //获得当前WheelView的Item的index
    public int getCurrentItem(){
        return pickerview_country_wheelview.getCurrentItem();
    }

    //设置当前WheelView的Item的index
    public void setCurrentItem(int dex){
        pickerview_country_wheelview.setCurrentItem(dex);
    }

    //设置当前的ImagevIew和WheelView的Width
    public void setWholeWidth(int num){
        int width = CommUtil.getScreenW((Activity) context) / num;
        ViewGroup.LayoutParams lp1  =  pickerview_country_iv.getLayoutParams();
        lp1.width = width;
        pickerview_country_iv.setLayoutParams(lp1);
        ViewGroup.LayoutParams lp2  =  pickerview_country_wheelview.getLayoutParams();
        lp2.width = width;
        pickerview_country_wheelview.setLayoutParams(lp2);
    }

}

