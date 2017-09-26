package com.chay.test.chaysizemanager.itemView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.util.Preconditions;

/**
 * Created by Chay on 2016/11/11.
 */

public class SeekBarView extends RelativeLayout {

    private TextView size_wheel_line_left_tv;
    private SeekBar size_wheel_line_seekbar;
    private TextView size_wheel_line_right_tv;

    public SeekBarView(Context context) {
        this(context, null);
    }

    public SeekBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.seekbar_view_layout, this, true);
        size_wheel_line_left_tv = (TextView) findViewById(R.id.size_wheel_line_left_tv);
        size_wheel_line_seekbar = (SeekBar) findViewById(R.id.size_wheel_line_seekbar);
        size_wheel_line_right_tv = (TextView) findViewById(R.id.size_wheel_line_right_tv);
    }

    //给SeekBar设置相应的监听
    public void setListener(SeekBar.OnSeekBarChangeListener listener) {
        if (listener !=null){
            size_wheel_line_seekbar.setOnSeekBarChangeListener(listener);
        }
    }

    //设置左面的文字
    public void setLeftTv(String leftTv) {
        size_wheel_line_left_tv.setText(Preconditions.nullToEmpty(leftTv));
    }

    //设置右面的进度文字
    public void setRightTv(String rightTv) {
        size_wheel_line_right_tv.setText(Preconditions.nullToEmpty(rightTv));
    }

    //设置SeekBar的当前进度
    public void setProgress(int current) {
        size_wheel_line_seekbar.setProgress(current);
    }

    //设置SeekBar的最大值
    public void setMaxProgress(int max){
        size_wheel_line_seekbar.setMax(max);
    }

    //获得当前的SeekBar的值
    public int getCurrentProgress(){
        return size_wheel_line_seekbar.getProgress();
    }

    //获得当前的右侧的数字的值
    public String getCurrentRightString(){
        return size_wheel_line_right_tv.getText().toString();
    }

}
