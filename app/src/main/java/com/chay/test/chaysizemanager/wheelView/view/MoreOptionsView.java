package com.chay.test.chaysizemanager.wheelView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.wheelView.listener.OnItemSelectedListener;
import com.chay.test.chaysizemanager.wheelView.model.IMoreOptionsData;
import com.chay.test.chaysizemanager.wheelView.model.IPickerViewData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chay on 2016/11/8.
 * 联动滚轮的整体组合view
 */

public class MoreOptionsView extends RelativeLayout {

    private LinearLayout pickerview_more_options_rl;

    private List<LineWheelView> wheelViewList;

    private List<IMoreOptionsData> list;

    private Context mContext;

    private int size = 12;

    private boolean isCycle = false;

    private OnWheelChangeListener listener;

    public MoreOptionsView(Context context) {
        this(context, null);
    }

    public MoreOptionsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoreOptionsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.pickerview_more_options_layout, this, true);
        pickerview_more_options_rl = (LinearLayout) findViewById(R.id.pickerview_more_options_rl);
        wheelViewList = new ArrayList<>();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCycle(boolean cycle) {
        isCycle = cycle;
    }

    //设置整体数据源
    public void setList(List<IMoreOptionsData> list) {
        this.list = list;
    }

    public void setListener(OnWheelChangeListener listener) {
        this.listener = listener;
    }

    //根据数据的多少，来映射到View中
    public void mapData() {
        if (list != null && list.size() > 0) {
            //先将基础数据存放到相应的View中
            for (int i = 0; i < list.size(); i++) {
                IMoreOptionsData wheelData = list.get(i);
                LineWheelView lineWheelView = new LineWheelView(mContext);
                lineWheelView.setWheelRecycle(isCycle);
                //统一字体大小
                lineWheelView.setWheelSize(12);
                lineWheelView.setData(wheelData.getData());
                lineWheelView.setIvRes(wheelData.getCountryRes());
                lineWheelView.setTvName(wheelData.getCountryName());
                if (i == list.size() - 1) {
                    lineWheelView.setLineShow(false);
                } else {
                    lineWheelView.setLineShow(true);
                }
                wheelViewList.add(lineWheelView);
                pickerview_more_options_rl.addView(lineWheelView);
                if (list.size() >= 7) {
                    lineWheelView.setWholeWidth(list.size());
                }
            }

            //继续设置每个LineWheelView的相关性
            for (int i = 0; i < list.size(); i++) {
                final int fi = i;
                wheelViewList.get(fi).setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int index) {

                        int outIdx = list.get(fi).mapOutterIdx(index);

                        for (int j = 0; j < list.size(); j++) {
                            int innerIdx = list.get(j).mapInnerIdx(outIdx);
                            wheelViewList.get(j).setCurrentItem(innerIdx);
                        }
                        if (listener != null) {
                            boolean isRepeatData = list.get(fi).isRepeat(outIdx);
                            listener.onSeekbarChange(outIdx, isRepeatData);
                        }
                    }
                });
            }

        }
    }

    //获得当前滑动的位置
    public int getCurrentItem() {
        if (wheelViewList != null && wheelViewList.size() > 0) {
            return wheelViewList.get(0).getCurrentItem();
        }
        return -1;
    }

    //设置滚动的位置
    public void setCurrentItem(int num) {
        if (wheelViewList != null && wheelViewList.size() > 0) {
            for (int i = 0; i < wheelViewList.size(); i++) {
                //进行映射下 每个滚轮的对应的 索引
                int perWheelIdx = list.get(i).mapInnerIdx(num);
                wheelViewList.get(i).setCurrentItem(perWheelIdx);
            }
        }
    }

    public interface OnWheelChangeListener {
        void onSeekbarChange(int index, boolean isRepeat);
    }

    /**
     * 两种方案的字体大小  12sp 和 14 sp
     * 如果滚轮最大长度 小于 5个 用 14sp
     * 否则用 12sp
     *
     * @param textList
     * @return
     */
    private int selectFontSize(ArrayList<IPickerViewData> textList) {

        int maxLenth = 0;
        int SIZE_MORE_THAN_5 = 12;
        int SIZE_LESS_THAN_5 = 15;
        for (IPickerViewData item : textList) {
            int curLen = item.getPickerViewText().length();
            if (curLen > maxLenth) {
                maxLenth = curLen;
            }
        }

        if (maxLenth < 5) {
            return SIZE_LESS_THAN_5;
        }
        return SIZE_MORE_THAN_5;


    }
}
