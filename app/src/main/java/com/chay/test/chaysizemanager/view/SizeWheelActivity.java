package com.chay.test.chaysizemanager.view;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.base.BaseNewActivity;
import com.chay.test.chaysizemanager.info.SizeItemInfo;
import com.chay.test.chaysizemanager.itemView.SeekBarView;
import com.chay.test.chaysizemanager.otherView.RippleView;
import com.chay.test.chaysizemanager.util.CharsetUtils;
import com.chay.test.chaysizemanager.util.CommUtil;
import com.chay.test.chaysizemanager.util.NumberParserUtils;
import com.chay.test.chaysizemanager.util.Preconditions;
import com.chay.test.chaysizemanager.util.bean.CategorySizeBean;
import com.chay.test.chaysizemanager.util.bean.Constant;
import com.chay.test.chaysizemanager.util.bean.WheelViewInfo;
import com.chay.test.chaysizemanager.util.model.SizeListOperation;
import com.chay.test.chaysizemanager.util.model.WheelInfoUtils;
import com.chay.test.chaysizemanager.wheelView.model.IMoreOptionsData;
import com.chay.test.chaysizemanager.wheelView.view.MoreOptionsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chay on 2016/11/10.
 */

public class SizeWheelActivity extends BaseNewActivity implements View.OnClickListener, RippleView.OnRippleCompleteListener {

    private RippleView page_title_back_rip;
    private TextView page_title_text_1;
    private TextView right_text;

    private RelativeLayout size_wheel_top_rl;

    private MoreOptionsView size_wheel_more_opions_view;

    private LinearLayout size_wheel_seekbar_whole_ll;

    private List<SeekBarView> seekBarViews;
    private List<SizeListOperation> operations;

    private List<String> valueStringList;
    private List<String> compareStringList;
    private List<CategorySizeBean.CataDataBean.BodyMeasureBean> list;
    private List<WheelViewInfo> wheelViewInfos;

    private SizeItemInfo info;

    private CategorySizeBean bean;

    private String title = "";

    private String sex = "男";

    private boolean isFirstIn = true;

    @Override
    protected View getBackImg() {
        return null;
    }

    @Override
    protected void handleOnDestroy() {

    }

    @Override
    protected void handleOnStop() {

    }

    @Override
    protected void handleOnResume() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    /**
     * 处理从上一个界面获得的数据
     *
     * @param intent 携带数据的INTENT
     */
    public void dealLastData(Intent intent) {
        bean = (CategorySizeBean) intent.getSerializableExtra("categorySizeBean");
        sex = intent.getStringExtra("sex");
        if (bean != null) {
            title = Preconditions.nullToEmpty(bean.getName());
            page_title_text_1.setText(title);
            list = bean.getCataData().getBodyMeasure();
            //处理布局View
            for (int i = 0; i < list.size(); i++) {
                SeekBarView seekBarView = new SeekBarView(this);
                size_wheel_seekbar_whole_ll.addView(seekBarView);
                if (i != 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.topMargin = CommUtil.dp2px(this, 51);
                    seekBarView.setLayoutParams(lp);
                }
                seekBarViews.add(seekBarView);
            }
            //处理SeekBarView数据
            for (int i = 0; i < list.size(); i++) {
                seekBarViews.get(i).setLeftTv(list.get(i).getName());
                List<String> stringList = list.get(i).getSizeList();
                SizeListOperation operation = new SizeListOperation(stringList);
                operations.add(operation);
                final float step = Float.parseFloat(list.get(i).getStep());
                final int min = getMinNum(list.get(i).getSizeRange(), step);
                int max = getMaxNum(list.get(i).getSizeRange(), step);
                seekBarViews.get(i).setMaxProgress(
                        getMax(min, max));
                if (Preconditions.isNullOrEmpty(intent.getStringExtra(list.get(i).getType()))) {
                    //设置SeekBar默认选择的数字
                    float chooseNum = (Float.parseFloat(list.get(i).getSelectedSize()) - min * step) / step;
                    seekBarViews.get(i).setProgress((int) chooseNum);
                    seekBarViews.get(i).setRightTv(list.get(i).getSelectedSize());
                    valueStringList.add(list.get(i).getSelectedSize());
                    compareStringList.add(list.get(i).getSelectedSize());
                } else {
                    float chooseNum = (Float.parseFloat(intent.getStringExtra(list.get(i).getType())) - min * step) / step;
                    seekBarViews.get(i).setProgress((int) chooseNum);
                    seekBarViews.get(i).setRightTv(Preconditions
                            .nullToEmpty(intent.getStringExtra(list.get(i).getType())));
                    valueStringList.add(intent.getStringExtra(list.get(i).getType()));
                    compareStringList.add(intent.getStringExtra(list.get(i).getType()));
                }

                final int index = i;

                seekBarViews.get(i).setListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        //shezhi设置SeekBarView右侧的文字
                        progress = progress + min;
                        String newString = String.valueOf(((float) progress * step));
                        int dex = newString.indexOf(".");
                        String showTest = newString.substring(0, dex + 2);
                        seekBarViews.get(index).setRightTv(CharsetUtils.subZeroAndDot(showTest));

                        valueStringList.set(index, showTest);

                        //第一次进入处理联动数据
                        if (isFirstIn) {
                            int index = getIndex(list);
                            size_wheel_more_opions_view.setCurrentItem(index);
                            isFirstIn = false;
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (!isFirstIn) {
                            int index = getIndex(list);
                            size_wheel_more_opions_view.setCurrentItem(index);
                        }
                    }
                });

            }

            List<IMoreOptionsData> optionsDatas = new ArrayList<>();
            wheelViewInfos = new ArrayList<>();
            for (int i = 0; i < bean.getCataData().getShowSize().size(); i++) {
                optionsDatas.add(WheelInfoUtils.transfrom(bean.getCataData().getShowSize().get(i)));
                wheelViewInfos.add(WheelInfoUtils.transfrom(bean.getCataData().getShowSize().get(i)));
            }

            size_wheel_more_opions_view.setList(optionsDatas);
            size_wheel_more_opions_view.mapData();
            size_wheel_more_opions_view.setCurrentItem(getIndex(list));
            size_wheel_more_opions_view.setListener(new MoreOptionsView.OnWheelChangeListener() {
                @Override
                public void onSeekbarChange(int index, boolean isRepeat) {
                    setSeekBarViewsData(bean, index, isRepeat);
                }
            });

        }

        info = (SizeItemInfo) intent.getSerializableExtra("sizeItemInfo");

    }

    //获得默认的参值
    public void setValue() {
        for (int i = 0; i < wheelViewInfos.size(); i++) {
            if (wheelViewInfos.get(i).getType().equals(info.getAreaFlag())) {
                int index = size_wheel_more_opions_view.getCurrentItem();
                String value = wheelViewInfos.get(i).getData().get(index).getPickerViewText();
                info.setValue(value);
            }
        }
    }

    //根据当前的index设置
    public void setSeekBarViewsData(CategorySizeBean bean, int index, boolean isRepeat) {
        List<CategorySizeBean.CataDataBean.BodyDefualtBean> bodys =
                bean.getCataData().getBodyDefualt();
        List<CategorySizeBean.CataDataBean.BodyMeasureBean> list =
                bean.getCataData().getBodyMeasure();
        for (int i = 0; i < bodys.size(); i++) {
            float step = Float.parseFloat(list.get(i).getStep());
            int min = getMinNum(list.get(i).getSizeRange(), step);
            float defaultNum = 0.0f;
            if (sex.equals("男")
                    && title.equals(Constant.SizeItemType.TYPE_TROUSERS)
                    && isRepeat) {
                float a1 = Float.parseFloat(bodys.get(i).getSizeList().get(index));
                float a2 = 0.0f;
                //防止数组越界，容错处理
                if (index + 1 < bodys.get(i).getSizeList().size() &&
                        bodys.get(i).getSizeList().get(index + 1) != null) {
                    a2 = Float.parseFloat(bodys.get(i).getSizeList().get(index + 1));
                }
                defaultNum = (a1 + a2) / 2;
            } else {
                defaultNum = Float.parseFloat(bodys.get(i).getSizeList().get(index));
            }
            float chooseNum = (defaultNum - min * step) / step;
            seekBarViews.get(i).setProgress((int) chooseNum);
        }
    }

    //获得SeekBarViews的最大索引
    public int getIndex(
            List<CategorySizeBean.CataDataBean.BodyMeasureBean> list) {
        int index = 0;
        int logIndex = 0;
        for (int i = 0; i < seekBarViews.size(); i++) {
            logIndex = operations.get(i).findIndex(seekBarViews.get(i).getCurrentRightString());
            if (logIndex >= index) {
                index = logIndex;
            }
        }
        return index;
    }

    //获得范围的min
    public int getMinNum(String data, float step) {
        int num = data.indexOf('-');
        String newString = data.substring(0, num);
        return (int) (NumberParserUtils.parseInt(newString) / step);
    }

    //获得范围的max
    public int getMaxNum(String data, float step) {
        int num = data.indexOf("-");
        String newString = data.substring(num + 1, data.length());
        return (int) (NumberParserUtils.parseInt(newString) / step);
    }

    //获得差值
    public int getMax(int min, int max) {
        return max - min;
    }

    @Override
    protected int getContentView() {
        return R.layout.size_wheel_layout;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {
        super.initView();

        seekBarViews = new ArrayList<>();
        operations = new ArrayList<>();
        valueStringList = new ArrayList<>();
        compareStringList = new ArrayList<>();

        page_title_back_rip = (RippleView) findViewById(R.id.page_title_back_rip);
        page_title_text_1 = (TextView) findViewById(R.id.page_title_text_1);
        right_text = (TextView) findViewById(R.id.right_text);

        size_wheel_top_rl = (RelativeLayout) findViewById(R.id.size_wheel_top_rl);
        setTopHeight();
        size_wheel_seekbar_whole_ll = (LinearLayout) findViewById(R.id.size_wheel_seekbar_whole_ll);

        size_wheel_more_opions_view = (MoreOptionsView) findViewById(R.id.size_wheel_more_opions_view);

        ViewGroup.LayoutParams lp = size_wheel_more_opions_view.getLayoutParams();
        lp.height = 436;
        size_wheel_more_opions_view.setLayoutParams(lp);
        right_text.setText("保存");
        dealLastData(getIntent());

        right_text.setOnClickListener(this);

        page_title_back_rip.setOnRippleCompleteListener(this);
    }

    private void setTopHeight() {
        //按比例对其页面进行设置5/4
        int height = CommUtil.getScreenW(this) * 4 / 5;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                height);
        size_wheel_top_rl.setLayoutParams(lp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.right_text) {
            saveData();
        }
    }

    @Override
    public void onComplete(RippleView rippleView) {
        closeThisPage();
    }

    //关闭此页面
    public void closeThisPage(){
        boolean isClose = true;
        //判断数据是否修改，如果修改，则先保存数据在关闭
        for (int i = 0; i < valueStringList.size(); i++) {
            float a = Float.parseFloat(valueStringList.get(i));
            float b = Float.parseFloat(compareStringList.get(i));
            if (a != b) {
                isClose = false;
                break;
            }
        }
        if (isClose) {
            finish();
        } else {
            saveData();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            closeThisPage();
        }
        return false;
    }

    //保存数据
    public void saveData() {
        if (info != null) {
            setValue();
            Intent returnIntent = new Intent();
            for (int i = 0; i < list.size(); i++) {
                returnIntent.putExtra(list.get(i).getType(), valueStringList.get(i));
            }
            returnIntent.putExtra("sizeItemInfo", info);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

}

