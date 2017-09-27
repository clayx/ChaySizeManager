package com.chay.test.chaysizemanager.view;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.base.BaseNewActivity;
import com.chay.test.chaysizemanager.info.SizeDaoInfo;
import com.chay.test.chaysizemanager.info.SizeItemInfo;
import com.chay.test.chaysizemanager.itemView.SizeItemView;
import com.chay.test.chaysizemanager.listener.SexClickListener;
import com.chay.test.chaysizemanager.otherView.RippleView;
import com.chay.test.chaysizemanager.otherView.RoundImageView;
import com.chay.test.chaysizemanager.pop.SexOrHeadPopupWindow;
import com.chay.test.chaysizemanager.presenter.ISizeSettingPresenter;
import com.chay.test.chaysizemanager.util.AbtainTwoWatcher;
import com.chay.test.chaysizemanager.util.AssetsUtils;
import com.chay.test.chaysizemanager.util.CharsetUtils;
import com.chay.test.chaysizemanager.util.CommUtil;
import com.chay.test.chaysizemanager.util.ConnectUtils;
import com.chay.test.chaysizemanager.util.CustomLengthFilter;
import com.chay.test.chaysizemanager.util.JsonUtils;
import com.chay.test.chaysizemanager.util.Preconditions;
import com.chay.test.chaysizemanager.util.bean.CategorySizeBean;
import com.chay.test.chaysizemanager.util.bean.Constant;
import com.chay.test.chaysizemanager.util.bean.SizeTableBean;
import com.chay.test.chaysizemanager.util.dao.SizeDaoUtils;
import com.chay.test.chaysizemanager.util.model.SizeListOperation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chay on 2016/11/9.
 *
 * @Description:设置Body详情界面
 */

public class SizeSettingActivity extends BaseNewActivity implements View.OnClickListener, RippleView.OnRippleCompleteListener {

    private final int CONNECT_PERSON_REQUEST = 1001;

    private RippleView page_title_back_rip;
    private TextView page_title_text_1;
    private TextView right_text;

    private RoundImageView size_setting_user_icon_iv;
    private ImageView size_setting_user_moren_iv;
    private EditText size_setting_user_name_et;
    private ImageView size_setting_user_name_connect_iv;
    private TextView size_setting_user_sex_tv;
    private EditText size_setting_user_height_et;
    private EditText size_setting_user_weight_et;
    private EditText size_setting_user_chest_et;
    private EditText size_setting_user_waistline_et;
    private EditText size_setting_user_hipline_et;
    private EditText size_setting_user_headline_et;
    private EditText size_setting_user_collar_et;
    private EditText size_setting_user_shoulder_et;
    private EditText size_setting_user_hand_et;
    private EditText size_setting_user_leg_et;
    private EditText size_setting_user_foot_et;
    private TextView size_setting_user_detail_tv;

    //下面整体布局的各个View
    private SizeItemView size_setting_hat_sizeitemview;
    private SizeItemView size_setting_coat_sizeitemview;
    private SizeItemView size_setting_shirt_sizeitemview;
    private SizeItemView size_setting_trousers_sizeitemview;
    private SizeItemView size_setting_underpants_sizeitemview;
    private SizeItemView size_setting_shoes_sizeitemview;
    private SizeItemView size_setting_glove_sizeitemview;

    private Map<String, SizeItemView> sizeItemViewMap;
    private Map<String, SizeItemInfo> sizeItemInfoMap;

    //展开部分相关View
    private RelativeLayout size_setting_show_rl;
    private LinearLayout size_setting_user_hide_ll;
    private TextView size_setting_show_tv;
    private ImageView size_setting_show_iv;

    //展示各种数据图
    private AlertDialog myDialog;

    //从Assets中获取的数据源
    private SizeTableBean sizeTableBean;
    //区分男女的数据
    private SizeTableBean.PersonSizeDataBean personSizeDataBean;
    private SexOrHeadPopupWindow mPopupWindows;

    private Map<String, EditText> editMap;

    //默认为显示
    private boolean isAdd = true;
    private Long sizeId;
    private String sizeIdStr = "0";
    //默认性别为男
    private String sex = "男";
    //默认为不是默认尺码
    private String defaultSize = "0";
    //获得尺码数据用户名
    private String sizeUserName = "";

    private ISizeSettingPresenter presenter;

    //返回弹窗比较数据
    private Map<String, String> originalData;
    private Map<String, String> compareData;

    private Map<String, Map<String, String>> maxNumMap;
    private Map<String, List<String>> cateMap;
    private Map<String, CategorySizeBean> categorySizeBeanMap;

    //数据库中默认的pos
    private int daoDefault = -1;

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
        sizeTableBean = getSizeTableBeanFromAssets();
        isAdd = intent.getBooleanExtra("isAdd", true);
        sizeId = intent.getLongExtra("id", 0);
        sizeIdStr = String.valueOf(sizeId);
        if (sizeTableBean != null && !isAdd && sizeIdStr.equals("0")) {
            //初始化默认为男士---新增
            personSizeDataBean = sizeTableBean.getSizeTable().get(0);
        }
        if (sizeTableBean != null && isAdd && !sizeIdStr.equals("0")) {
            //性别数据
            sex = intent.getStringExtra("sex");
            if (sex.equals("男")) {
                personSizeDataBean = sizeTableBean.getSizeTable().get(0);
            } else {
                personSizeDataBean = sizeTableBean.getSizeTable().get(1);
            }
            defaultSize = intent.getStringExtra("default");
            if (Preconditions.isNullOrEmpty(defaultSize)) {
                defaultSize = "0";
            }
            sizeUserName = intent.getStringExtra("sizeUserName");
        }

        initDaoDefaultPos();

    }

    //获取数据库中的默认角标
    private void initDaoDefaultPos() {
        List<SizeDaoInfo> list = SizeDaoUtils.queryAll();
        if (Preconditions.isNullOrEmpty(list)) {
            return;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (!Preconditions.isNullOrEmpty(list.get(i).getIsDefault()) &&
                        list.get(i).getIsDefault().equals("1")) {
                    daoDefault = i;
                }
            }
        }
    }

    private SizeTableBean getSizeTableBeanFromAssets() {
        SizeTableBean bean = null;
        String json = AssetsUtils.getFromAssets(this, "sizeData.json");
        if (!Preconditions.isNullOrEmpty(json)) {
            bean = JsonUtils.parseObject(json, SizeTableBean.class);
        }
        return bean;
    }

    @Override
    protected int getContentView() {
        return R.layout.size_setting_layout;
    }

    @Override
    protected void initDatas() {
        if (!sizeIdStr.equals("0")) {
            initSizeData(sizeId);
        } else {
            addDataTopMap(originalData);
        }
    }

    //从数据库中获取响应的SizeDaoInfo
    private void initSizeData(Long sizeId) {
        List<SizeDaoInfo> list = SizeDaoUtils.queryAll();
        SizeDaoInfo info = null;
        if (Preconditions.isNullOrEmpty(list)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == sizeId) {
                info = list.get(i);
            }
        }

        loadData2View(info);

        addDataTopMap(originalData);

    }

    //将数据库数据加载到相应的View里
    private void loadData2View(SizeDaoInfo info) {
        doTvEdtData(info.getFullName(), size_setting_user_name_et);
        doTvEdtData(info.getSex(), size_setting_user_sex_tv);
        doTvEdtData(info.getHeight(), size_setting_user_height_et);
        doTvEdtData(info.getWeight(), size_setting_user_weight_et);
        doTvEdtData(info.getChest(), size_setting_user_chest_et);
        doTvEdtData(info.getWaist(), size_setting_user_waistline_et);
        doTvEdtData(info.getHip(), size_setting_user_hipline_et);
        doTvEdtData(info.getHeadCircu(), size_setting_user_headline_et);
        doTvEdtData(info.getNeckCircu(), size_setting_user_collar_et);
        doTvEdtData(info.getShoulder(), size_setting_user_shoulder_et);
        doTvEdtData(info.getHandsLength(), size_setting_user_hand_et);
        doTvEdtData(info.getThighCircu(), size_setting_user_leg_et);
        doTvEdtData(info.getFootLength(), size_setting_user_foot_et);
        doSizeItemViewData(info.getHat(), "INTL", size_setting_hat_sizeitemview);
        doSizeItemViewData(info.getCoat(), "CN", size_setting_coat_sizeitemview);
        doSizeItemViewData(info.getShirt(), "INTL", size_setting_shirt_sizeitemview);
        doSizeItemViewData(info.getTrousers(), "CN", size_setting_trousers_sizeitemview);
        doSizeItemViewData(info.getUnderpants(), "INTL", size_setting_underpants_sizeitemview);
        doSizeItemViewData(info.getShoes(), "CN", size_setting_shoes_sizeitemview);
        doSizeItemViewData(info.getGlove(), "INTL", size_setting_glove_sizeitemview);
    }

    /**
     * 将数据放入到相关的View和EditText中
     *
     * @param value 具体数据
     * @param view  相关View
     */
    private void doTvEdtData(String value, TextView view) {
        String checkValue = Preconditions.nullToEmpty(value);
        view.setText(checkValue);
    }

    /**
     * 将数据放入到响应的sizeItemView中
     *
     * @param value   传入的具体数据
     * @param country 国家
     * @param view    相关View
     */
    private void doSizeItemViewData(String value, String country, SizeItemView view) {
        String checkValue = Preconditions.nullToEmpty(value);
        view.setRightText(checkValue);
        if (Preconditions.isNullOrEmpty(checkValue)) {
            return;
        }
        if (country.equals("CN")) {
            view.setRightDrawable(R.drawable.sizeitem_china);
        } else {
            view.setRightDrawable(R.drawable.sizeitem_international);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        editMap = new HashMap<>();
        sizeItemViewMap = new HashMap<>();
        sizeItemInfoMap = new HashMap<>();
        originalData = new HashMap<>();
        compareData = new HashMap<>();

        maxNumMap = new HashMap<>();
        cateMap = new HashMap<>();
        categorySizeBeanMap = new HashMap<>();

        //初始化Presenter
        presenter = new ISizeSettingPresenter();

        presenter.initCateList(cateMap, personSizeDataBean);
        initCategoryMap(personSizeDataBean);

        page_title_back_rip = (RippleView) findViewById(R.id.page_title_back_rip);
        page_title_text_1 = (TextView) findViewById(R.id.page_title_text_1);
        right_text = (TextView) findViewById(R.id.right_text);

        size_setting_user_icon_iv = (RoundImageView) findViewById(R.id.size_setting_user_icon_iv);
        size_setting_user_moren_iv = (ImageView) findViewById(R.id.size_setting_user_moren_iv);
        if (isAdd) {
            //如果不从新增来，则显示设为默认
            size_setting_user_moren_iv.setVisibility(View.VISIBLE);
        }
        if (defaultSize.equals("0")) {
            size_setting_user_moren_iv.setBackgroundResource(R.drawable.size_setting_false_icon);
        } else {
            size_setting_user_moren_iv.setBackgroundResource(R.drawable.size_setting_true_icon);
        }
        size_setting_user_name_et = (EditText) findViewById(R.id.size_setting_user_name_et);
        size_setting_user_name_et.setFilters(new InputFilter[]{new CustomLengthFilter(16)});
        size_setting_user_name_et.setText(Preconditions.nullToEmpty(sizeUserName));
        size_setting_user_name_et.setSelection(size_setting_user_name_et.length());
        size_setting_user_name_connect_iv = (ImageView) findViewById(R.id.size_setting_user_name_connect_iv);
        size_setting_user_sex_tv = (TextView) findViewById(R.id.size_setting_user_sex_tv);

        if (sex.equals("男")) {
            size_setting_user_sex_tv.setText("男");
        } else {
            size_setting_user_sex_tv.setText("女");
        }

        setEditTextData();
        size_setting_user_detail_tv = (TextView) findViewById(R.id.size_setting_user_detail_tv);

        setSizeItemData();

        setSizeItemLeftDrawable();

        size_setting_show_rl = (RelativeLayout) findViewById(R.id.size_setting_show_rl);
        size_setting_user_hide_ll = (LinearLayout) findViewById(R.id.size_setting_user_hide_ll);
        size_setting_show_tv = (TextView) findViewById(R.id.size_setting_show_tv);
        size_setting_show_iv = (ImageView) findViewById(R.id.size_setting_show_iv);

        page_title_text_1.setText("尺码助手");
        right_text.setText("保存");

        page_title_back_rip.setOnRippleCompleteListener(this);

        //展开与收起监听
        size_setting_show_rl.setOnClickListener(this);

        //BODY详情数据
        size_setting_user_detail_tv.setOnClickListener(this);

        //男女选择弹窗
        size_setting_user_sex_tv.setOnClickListener(this);

        //跳转联系人
        size_setting_user_name_connect_iv.setOnClickListener(this);

        //设置默认角色
        size_setting_user_moren_iv.setOnClickListener(this);

        right_text.setOnClickListener(this);

        size_setting_user_icon_iv.setOnClickListener(this);

        setSizeItemListener();

        //处理EdiText的数据
        dealEditTextFilter();

    }

    //初始化尺码助手下面Item
    private void setSizeItemData() {
        size_setting_hat_sizeitemview = (SizeItemView) findViewById(R.id.size_setting_hat_sizeitemview);
        size_setting_coat_sizeitemview = (SizeItemView) findViewById(R.id.size_setting_coat_sizeitemview);
        size_setting_shirt_sizeitemview = (SizeItemView) findViewById(R.id.size_setting_shirt_sizeitemview);
        size_setting_trousers_sizeitemview = (SizeItemView) findViewById(R.id.size_setting_trousers_sizeitemview);
        size_setting_underpants_sizeitemview = (SizeItemView) findViewById(R.id.size_setting_underpants_sizeitemview);
        size_setting_shoes_sizeitemview = (SizeItemView) findViewById(R.id.size_setting_shoes_sizeitemview);
        size_setting_glove_sizeitemview = (SizeItemView) findViewById(R.id.size_setting_glove_sizeitemview);

        sizeItemViewMap.put(Constant.SizeItemType.TYPE_HAT, size_setting_hat_sizeitemview);
        sizeItemViewMap.put(Constant.SizeItemType.TYPE_COAT, size_setting_coat_sizeitemview);
        sizeItemViewMap.put(Constant.SizeItemType.TYPE_SHIRT, size_setting_shirt_sizeitemview);
        sizeItemViewMap.put(Constant.SizeItemType.TYPE_TROUSERS, size_setting_trousers_sizeitemview);
        sizeItemViewMap.put(Constant.SizeItemType.TYPE_UNDERPANTS, size_setting_underpants_sizeitemview);
        sizeItemViewMap.put(Constant.SizeItemType.TYPE_SHOES, size_setting_shoes_sizeitemview);
        sizeItemViewMap.put(Constant.SizeItemType.TYPE_GLOVE, size_setting_glove_sizeitemview);
    }

    //处理EditText的最大数据
    public void dealMaxNum(String cateName) {
        List<String> newList = cateMap.get(cateName);
        for (int i = 0; i < newList.size(); i++) {
            Map<String, String> maxValueMap = presenter.initMaxMap(editMap, maxNumMap,
                    personSizeDataBean, newList.get(i));
            Set<String> set = maxValueMap.keySet();
            Iterator<String> iterator = set.iterator();
            String maxType = "";
            String max = "";
            while (iterator.hasNext()) {
                maxType = iterator.next();
                max = maxValueMap.get(maxType);
            }
            String currentNum = presenter.getCurrentValue(editMap, cateName);
            String value = "";
            CategorySizeBean bean = categorySizeBeanMap.get(newList.get(i));
            int flag = presenter.getCountryFlag(bean.getDefaultCountry());
            List<CategorySizeBean.CataDataBean.ShowSizeBean> showSizeBeen =
                    bean.getCataData().getShowSize();
            List<CategorySizeBean.CataDataBean.BodyMeasureBean> bodyMeasureBeen =
                    bean.getCataData().getBodyMeasure();
            List<String> showSizeStringList = null;
            List<String> bodyMeaureStringList = null;
            List<String> compareBodyMeaureStringList = null;
            for (int j = 0; j < showSizeBeen.size(); j++) {
                if (showSizeBeen.get(j).getType().equals(bean.getDefaultCountry())) {
                    showSizeStringList = showSizeBeen.get(j).getSizeList();
                    break;
                }
            }
            for (int k = 0; k < bodyMeasureBeen.size(); k++) {
                if (bodyMeasureBeen.get(k).getType().equals(cateName)) {
                    bodyMeaureStringList = bodyMeasureBeen.get(k).getSizeList();
                }
                if (bodyMeasureBeen.get(k).getType().equals(maxType)) {
                    compareBodyMeaureStringList = bodyMeasureBeen.get(k).getSizeList();
                }
            }
            SizeListOperation operation = null;
            SizeListOperation compareOperation = null;
            int index = -1;
            if (bodyMeaureStringList != null && bodyMeaureStringList.size() > 0) {
                operation = new SizeListOperation(bodyMeaureStringList);
            }
            if (compareBodyMeaureStringList != null && compareBodyMeaureStringList.size() > 0) {
                compareOperation = new SizeListOperation(compareBodyMeaureStringList);
            }

            if (!max.equals("0.0") && compareOperation != null) {
                int maxIndex = compareOperation.findIndex(max);
                if (maxIndex > index) {
                    index = maxIndex;
                }
            }

            if (!max.equals("0.0") && operation != null && showSizeStringList != null && showSizeStringList.size() > 0) {
                int currentIndex = operation.findIndex(currentNum);

                if (currentIndex > index) {
                    index = currentIndex;
                }

                value = showSizeStringList.get(index);
            }

            if (!Preconditions.isNullOrEmpty(value) && !max.equals("0.0")) {
                sizeItemViewMap.get(newList.get(i)).setRightText(value);
                sizeItemViewMap.get(newList.get(i)).setRightDrawable(flag);
            } else {
                sizeItemViewMap.get(newList.get(i)).setRightText("");
                sizeItemViewMap.get(newList.get(i)).setRightDrawable(0);
            }
        }
    }

    //初始化各个CategorySizeBean
    public void initCategoryMap(SizeTableBean.PersonSizeDataBean personSizeDataBean) {
        List<CategorySizeBean> list = personSizeDataBean.getSizeData();
        for (int i = 0; i < list.size(); i++) {
            categorySizeBeanMap.put(list.get(i).getName(), list.get(i));
        }
    }

    //设置下面Item的监听事件
    private void setSizeItemListener() {
        size_setting_hat_sizeitemview.setOnClickListener(this);
        size_setting_coat_sizeitemview.setOnClickListener(this);
        size_setting_shirt_sizeitemview.setOnClickListener(this);
        size_setting_trousers_sizeitemview.setOnClickListener(this);
        size_setting_underpants_sizeitemview.setOnClickListener(this);
        size_setting_shoes_sizeitemview.setOnClickListener(this);
        size_setting_glove_sizeitemview.setOnClickListener(this);
    }

    //处理男女显示图示
    public void setSizeItemLeftDrawable() {
        if (sex.equals("男")) {
            size_setting_hat_sizeitemview.setLeftDrawable(R.drawable.size_setting_hat_man);
            size_setting_shirt_sizeitemview.setLeftDrawable(R.drawable.size_setting_shirt_man);
            size_setting_shoes_sizeitemview.setLeftDrawable(R.drawable.size_setting_shoes_man);
            size_setting_underpants_sizeitemview.setLeftDrawable(R.drawable.size_setting_underpants_man);
        } else {
            size_setting_hat_sizeitemview.setLeftDrawable(R.drawable.size_setting_hat_woman);
            size_setting_shirt_sizeitemview.setLeftDrawable(R.drawable.size_setting_shirt_woman);
            size_setting_shoes_sizeitemview.setLeftDrawable(R.drawable.size_setting_shoes_woman);
            size_setting_underpants_sizeitemview.setLeftDrawable(R.drawable.size_setting_underpants_woman);
        }
    }

    //设置相关的EditText条件的数据
    private void setEditTextData() {
        size_setting_user_height_et = (EditText) findViewById(R.id.size_setting_user_height_et);
        size_setting_user_weight_et = (EditText) findViewById(R.id.size_setting_user_weight_et);
        size_setting_user_chest_et = (EditText) findViewById(R.id.size_setting_user_chest_et);
        size_setting_user_waistline_et = (EditText) findViewById(R.id.size_setting_user_waistline_et);
        size_setting_user_hipline_et = (EditText) findViewById(R.id.size_setting_user_hipline_et);
        size_setting_user_headline_et = (EditText) findViewById(R.id.size_setting_user_headline_et);
        size_setting_user_collar_et = (EditText) findViewById(R.id.size_setting_user_collar_et);
        size_setting_user_shoulder_et = (EditText) findViewById(R.id.size_setting_user_shoulder_et);
        size_setting_user_hand_et = (EditText) findViewById(R.id.size_setting_user_hand_et);
        size_setting_user_leg_et = (EditText) findViewById(R.id.size_setting_user_leg_et);
        size_setting_user_foot_et = (EditText) findViewById(R.id.size_setting_user_foot_et);
        presenter.setEditTextFilter(size_setting_user_height_et, null);
        presenter.setEditTextFilter(size_setting_user_weight_et, null);

        editMap.put(Constant.BodyType.TYPE_BUST, size_setting_user_chest_et);
        editMap.put(Constant.BodyType.TYPE_WAIST, size_setting_user_waistline_et);
        editMap.put(Constant.BodyType.TYPE_HIP, size_setting_user_hipline_et);
        editMap.put(Constant.BodyType.TYPE_HEAD, size_setting_user_headline_et);
        editMap.put(Constant.BodyType.TYPE_NECK, size_setting_user_collar_et);
        editMap.put(Constant.BodyType.TYPE_SHOULDER, size_setting_user_shoulder_et);
        editMap.put(Constant.BodyType.TYPE_HAND, size_setting_user_hand_et);
        editMap.put(Constant.BodyType.TYPE_THIGH, size_setting_user_leg_et);
        editMap.put(Constant.BodyType.TYPE_FOOT, size_setting_user_foot_et);

    }

    //处理EditText的过滤（范围和监听改变ItemView）
    public void dealEditTextFilter() {
        presenter.deleteEditLastDot(size_setting_user_height_et);
        presenter.deleteEditLastDot(size_setting_user_weight_et);
        presenter.setEditTextFilter(size_setting_user_chest_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_BUST);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_chest_et);
        presenter.setEditTextFilter(size_setting_user_waistline_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_WAIST);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_waistline_et);
        presenter.setEditTextFilter(size_setting_user_hipline_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_HIP);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_hipline_et);
        presenter.setEditTextFilter(size_setting_user_headline_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_HEAD);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_headline_et);
        presenter.setEditTextFilter(size_setting_user_collar_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_NECK);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_collar_et);
        presenter.setEditTextFilter(size_setting_user_shoulder_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_SHOULDER);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_shoulder_et);
        presenter.setEditTextFilter(size_setting_user_hand_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_HAND);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_hand_et);
        presenter.setEditTextFilter(size_setting_user_leg_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_THIGH);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_leg_et);
        presenter.setEditTextFilter(size_setting_user_foot_et, new AbtainTwoWatcher.OnSizeItemViewListener() {
            @Override
            public void changeSizeItemView(String num) {
                dealMaxNum(Constant.BodyType.TYPE_FOOT);
            }
        });
        presenter.deleteEditLastDot(size_setting_user_foot_et);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CONNECT_PERSON_REQUEST:
                if (data == null) {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri = data.getData();
                String[] contacts = ConnectUtils.getPhoneContacts(SizeSettingActivity.this, uri);
                size_setting_user_name_et.setText(Preconditions.nullToEmpty(contacts[0]));
                size_setting_user_name_et.setSelection(size_setting_user_name_et.length());
                break;
            case Constant.ResposeType.TYPE_HAT:
                dealReturnData(data, personSizeDataBean.getSizeData().get(0));
                break;
            case Constant.ResposeType.TYPE_COAT:
                dealReturnData(data, personSizeDataBean.getSizeData().get(1));
                break;
            case Constant.ResposeType.TYPE_SHIRT:
                dealReturnData(data, personSizeDataBean.getSizeData().get(2));
                break;
            case Constant.ResposeType.TYPE_TROUSERS:
                dealReturnData(data, personSizeDataBean.getSizeData().get(3));
                break;
            case Constant.ResposeType.TYPE_UNDERPANTS:
                dealReturnData(data, personSizeDataBean.getSizeData().get(4));
                break;
            case Constant.ResposeType.TYPE_SHOES:
                dealReturnData(data, personSizeDataBean.getSizeData().get(5));
                break;
            case Constant.ResposeType.TYPE_GLOVE:
                dealReturnData(data, personSizeDataBean.getSizeData().get(6));
                break;
        }
        //设置返回时光标位置
        presenter.setCurrentFocusPos(SizeSettingActivity.this);

        super.onActivityResult(requestCode, resultCode, data);

    }

    //处理获得的数据+
    public void dealReturnData(Intent data, CategorySizeBean bean) {
        if (bean != null && data != null) {
            SizeItemInfo info = (SizeItemInfo) data.getSerializableExtra("sizeItemInfo");
            sizeItemViewMap.get(info.getCateName()).setRightText(info.getValue());
            sizeItemViewMap.get(info.getCateName()).setRightDrawable(info.getCountryFlag());
            sizeItemInfoMap.put(info.getCateName(), info);

            List<CategorySizeBean.CataDataBean.BodyMeasureBean> list =
                    bean.getCataData().getBodyMeasure();
            for (int i = 0; i < list.size(); i++) {
                String content = data.getStringExtra(list.get(i).getType());
                content = CharsetUtils.subZeroAndDot(content);
                editMap.get(list.get(i).getType()).setText(content);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.size_setting_show_rl) {
            //展开按钮事件
            clickOpenOrClose();
        }
        if (id == R.id.size_setting_user_detail_tv) {
            //展开具体数据
            myDialog = new AlertDialog.Builder(this).create();
            myDialog.show();
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.more_bodydate_bigimg_layout, null);
            linearLayout.findViewById(R.id.more_bodydate_view).setOnClickListener(this);

            myDialog.getWindow().setContentView(linearLayout);
            myDialog.setCanceledOnTouchOutside(true);
            myDialog.setCancelable(true);
        }
        if (id == R.id.more_bodydate_view) {
            //点击图片隐藏
            myDialog.dismiss();
        }
        if (id == R.id.size_setting_user_sex_tv) {
            //男女选择弹窗
            if (mPopupWindows == null) {
                mPopupWindows = new SexOrHeadPopupWindow(this);
            }
            mPopupWindows.showSexDialog(new SexClickListener() {
                @Override
                public void click(String sex) {
                    SizeSettingActivity.this.sex = sex;
                    size_setting_user_sex_tv.setText(sex);
                    //设置相关的男女数据对应
                    if (size_setting_user_sex_tv.getText().equals("男")) {
                        personSizeDataBean = sizeTableBean.getSizeTable().get(0);
                    } else {
                        personSizeDataBean = sizeTableBean.getSizeTable().get(1);
                    }
                    presenter.initCateList(cateMap, personSizeDataBean);
                    initCategoryMap(personSizeDataBean);

                    setSizeItemLeftDrawable();

                    presenter.resetListData(editMap);
                }
            });
        }
        //跳转到联系人界面
        if (id == R.id.size_setting_user_name_connect_iv) {
            startConnectActivity();
        }
        //设置默认角色
        if (id == R.id.size_setting_user_moren_iv) {
            if (defaultSize.equals("0")) {
                setDaoDefault(true);
            } else {
                setDaoDefault(false);
            }

        }
        if (id == R.id.right_text) {
            presenter.clearCurrentFocus(SizeSettingActivity.this);
            addDataTopMap(compareData);

            if (presenter.compareMap(originalData, compareData)) {
                if (isAdd) {
                    Toast.makeText(this, "数据未做任何修改，直接关闭", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "没有新增任何数据，直接关闭", Toast.LENGTH_SHORT).show();
                }
                finish();
            } else {
                saveData();
            }

        }
        if (id == R.id.size_setting_user_icon_iv) {
            //设置头像
        }
        //跳转相关底部的Activity
        if (id == R.id.size_setting_hat_sizeitemview) {
            startSizeWheelActivity(personSizeDataBean.getSizeData().get(0),
                    Constant.ResposeType.TYPE_HAT, Constant.SizeItemType.TYPE_HAT);
        }
        if (id == R.id.size_setting_coat_sizeitemview) {
            startSizeWheelActivity(personSizeDataBean.getSizeData().get(1),
                    Constant.ResposeType.TYPE_COAT, Constant.SizeItemType.TYPE_COAT);
        }
        if (id == R.id.size_setting_shirt_sizeitemview) {
            startSizeWheelActivity(personSizeDataBean.getSizeData().get(2),
                    Constant.ResposeType.TYPE_SHIRT, Constant.SizeItemType.TYPE_SHIRT);
        }
        if (id == R.id.size_setting_trousers_sizeitemview) {
            startSizeWheelActivity(personSizeDataBean.getSizeData().get(3),
                    Constant.ResposeType.TYPE_TROUSERS, Constant.SizeItemType.TYPE_TROUSERS);
        }
        if (id == R.id.size_setting_underpants_sizeitemview) {
            startSizeWheelActivity(personSizeDataBean.getSizeData().get(4),
                    Constant.ResposeType.TYPE_UNDERPANTS, Constant.SizeItemType.TYPE_UNDERPANTS);
        }
        if (id == R.id.size_setting_shoes_sizeitemview) {
            startSizeWheelActivity(personSizeDataBean.getSizeData().get(5),
                    Constant.ResposeType.TYPE_SHOES, Constant.SizeItemType.TYPE_SHOES);
        }
        if (id == R.id.size_setting_glove_sizeitemview) {
            startSizeWheelActivity(personSizeDataBean.getSizeData().get(6),
                    Constant.ResposeType.TYPE_GLOVE, Constant.SizeItemType.TYPE_GLOVE);
        }
    }

    //设置默认数据 只有1个
    private void setDaoDefault(boolean isSet) {
        List<SizeDaoInfo> list = SizeDaoUtils.queryAll();
        if (isSet) {
            defaultSize = "1";
            if (Preconditions.isNullOrEmpty(list)) {
                return;
            } else {
                for (SizeDaoInfo sizeDaoInfo : list) {
                    sizeDaoInfo.setIsDefault("0");
                }
            }
        } else {
            defaultSize = "0";
            if (Preconditions.isNullOrEmpty(list)) {
                return;
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (i == daoDefault) {
                        list.get(i).setIsDefault("1");
                    }
                }
            }
        }

        if (defaultSize.equals("0")) {
            size_setting_user_moren_iv.setBackgroundResource(R.drawable.size_setting_false_icon);
        } else {
            size_setting_user_moren_iv.setBackgroundResource(R.drawable.size_setting_true_icon);
        }
    }

    // 获得需要存储的数据信息
    public SizeDaoInfo getSizeDao() {

        SizeDaoInfo info = new SizeDaoInfo();
        if (!sizeIdStr.equals("0")) {
            info.setId(sizeId);
        }
        info.setFullName(Preconditions.nullToEmpty(size_setting_user_name_et.getText().toString()));
        info.setSex(Preconditions.nullToEmpty(size_setting_user_sex_tv.getText().toString()));
        info.setHeight(Preconditions.nullToEmpty(size_setting_user_height_et.getText().toString()));
        info.setWeight(Preconditions.nullToEmpty(size_setting_user_weight_et.getText().toString()));
        info.setChest(Preconditions.nullToEmpty(size_setting_user_chest_et.getText().toString()));
        info.setWaist(Preconditions.nullToEmpty(size_setting_user_waistline_et.getText().toString()));
        info.setHip(Preconditions.nullToEmpty(size_setting_user_hipline_et.getText().toString()));
        info.setHeadCircu(Preconditions.nullToEmpty(size_setting_user_headline_et.getText().toString()));
        info.setNeckCircu(Preconditions.nullToEmpty(size_setting_user_collar_et.getText().toString()));
        info.setShoulder(Preconditions.nullToEmpty(size_setting_user_shoulder_et.getText().toString()));
        info.setHandsLength(Preconditions.nullToEmpty(size_setting_user_hand_et.getText().toString()));
        info.setThighCircu(Preconditions.nullToEmpty(size_setting_user_leg_et.getText().toString()));
        info.setFootLength(Preconditions.nullToEmpty(size_setting_user_foot_et.getText().toString()));
        info.setHat(Preconditions.nullToEmpty(size_setting_hat_sizeitemview.getRightText()));
        info.setCoat(Preconditions.nullToEmpty(size_setting_coat_sizeitemview.getRightText()));
        info.setShirt(Preconditions.nullToEmpty(size_setting_shirt_sizeitemview.getRightText()));
        info.setTrousers(Preconditions.nullToEmpty(size_setting_trousers_sizeitemview.getRightText()));
        info.setUnderpants(Preconditions.nullToEmpty(size_setting_underpants_sizeitemview.getRightText()));
        info.setShoes(Preconditions.nullToEmpty(size_setting_shoes_sizeitemview.getRightText()));
        info.setGlove(Preconditions.nullToEmpty(size_setting_glove_sizeitemview.getRightText()));
        info.setIsDefault(Preconditions.nullToEmpty(defaultSize));

        return info;
    }

    //保存数据到数据库
    public void saveData() {

        if (Preconditions.isNullOrEmpty(size_setting_user_name_et.getText().toString())) {
            Toast.makeText(this, "名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Preconditions.isNullOrEmpty(size_setting_user_sex_tv.getText().toString())) {
            Toast.makeText(this, "性别不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //保存数据
        SizeDaoUtils.insertSizeDate(getSizeDao());
        setResult(1001);
        finish();
    }

    /**
     * 跳转到轮子界面
     *
     * @param categorySizeBean 规则对象
     * @param requestCode      请求码
     */
    public void startSizeWheelActivity(CategorySizeBean categorySizeBean, int requestCode, String cateName) {
        //去除当前的焦点View的点
        if (getCurrentFocus() instanceof EditText) {
            EditText editText = (EditText) getCurrentFocus();
            presenter.clearEditTextDot(editText);
            editText.setSelection(editText.length());
        }
        Intent intent = new Intent(this, SizeWheelActivity.class);
        List<CategorySizeBean.CataDataBean.BodyMeasureBean> list =
                categorySizeBean.getCataData().getBodyMeasure();
        for (int i = 0; i < list.size(); i++) {
            intent.putExtra(list.get(i).getType(), editMap.get(list.get(i).getType()).getText().toString());
        }
        intent.putExtra("categorySizeBean", categorySizeBean);
        if (sizeItemInfoMap.get(cateName) == null) {
            SizeItemInfo info = new SizeItemInfo();
            info.setCateName(cateName);
            presenter.chooseDefaultCountry(info, categorySizeBean);
            sizeItemInfoMap.put(cateName, info);
        }
        intent.putExtra("sex", sex);
        intent.putExtra("sizeItemInfo", sizeItemInfoMap.get(cateName));
        startActivityForResult(intent, requestCode);
    }

    public void startConnectActivity() {
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, CONNECT_PERSON_REQUEST);
    }

    // 存储初始化数据,方便返回比较数据变化是否弹窗
    public void addDataTopMap(Map<String, String> map) {
        map.put("name", Preconditions.nullToEmpty(size_setting_user_name_et.getText().toString()));
        map.put("sex", Preconditions.nullToEmpty(size_setting_user_sex_tv.getText().toString()));
        map.put("height", Preconditions.nullToEmpty(size_setting_user_height_et.getText().toString()));
        map.put("weight", Preconditions.nullToEmpty(size_setting_user_weight_et.getText().toString()));
        map.put("isDefault", Preconditions.nullToEmpty(defaultSize));
        //循环获取EditText数据
        Set<String> etSet = editMap.keySet();
        Iterator<String> etIterator = etSet.iterator();
        while (etIterator.hasNext()) {
            String key = etIterator.next();
            String value = Preconditions.nullToEmpty(editMap.get(key).getText().toString());
            map.put(key, value);
        }

        Set<String> itemSet = sizeItemViewMap.keySet();
        Iterator<String> itemIterator = itemSet.iterator();
        while (itemIterator.hasNext()) {
            String key = itemIterator.next();
            String value = sizeItemViewMap.get(key).getRightText();
            map.put(key, value);
        }
    }

    /**
     * 展开或伸缩点击
     *
     * @param oVoid
     */
    private int closeAndOpen;

    public void clickOpenOrClose() {
        if (closeAndOpen % 2 == 0) {
            size_setting_show_tv.setText("收起");
            arrowRotate(0f, 180f);
            OpenAnimation();

        } else {
            size_setting_show_tv.setText("展开");
            arrowRotate(180f, 0f);
            closeAnimation();
        }
        closeAndOpen++;
    }

    /**
     * 展开关闭arrow的动画
     *
     * @param
     */
    private void arrowRotate(float start, float end) {
        RotateAnimation rotateAnimation = new RotateAnimation(start, end, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(200);
        rotateAnimation.setFillAfter(true);
        size_setting_show_iv.startAnimation(rotateAnimation);
    }

    /**
     * 展开动画
     */
    private void OpenAnimation() {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, CommUtil.dp2px(this, 305));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int mHeight = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutparams = size_setting_user_hide_ll.getLayoutParams();
                layoutparams.height = mHeight;
                size_setting_user_hide_ll.setLayoutParams(layoutparams);
            }
        });
        valueAnimator.setDuration(400).start();
    }

    /**
     * 关闭动画
     */
    private void closeAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(CommUtil.dp2px(this, 305), 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int mHeight = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = size_setting_user_hide_ll.getLayoutParams();
                layoutParams.height = mHeight;
                size_setting_user_hide_ll.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.setDuration(400).start();
    }

    @Override
    public void onComplete(RippleView rippleView) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

}
