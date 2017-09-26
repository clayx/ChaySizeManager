package com.chay.test.chaysizemanager.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.adapter.SizeListAdapter;
import com.chay.test.chaysizemanager.base.BaseNewActivity;
import com.chay.test.chaysizemanager.info.SizeListInfo;
import com.chay.test.chaysizemanager.otherView.RippleView;

import java.util.List;

/**
 * Created by Chay on 2016/11/8.
 *
 * @Description:尺码列表
 */

public class SizeListActivity extends BaseNewActivity {

    private final int SIZESETTING_REQUEST = 1001;

    private RippleView page_title_back_rip;
    private TextView page_title_text_1;
    private Button right_button;

    private ListView size_list_view;

    private SizeListAdapter adapter;
    private SizeListInfo sizeListInfo;

    private List<SizeListInfo.UserSizeInfo> list;

    @Override
    protected View getBackImg() {
        return page_title_back_rip;
    }

    @Override
    protected void handleOnDestroy() {
        page_title_back_rip = null;
        page_title_text_1 = null;
        right_button = null;
        adapter = null;
        sizeListInfo = null;
        size_list_view = null;

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
    protected int getContentView() {
        return R.layout.size_list_layout;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initView() {
        super.initView();
        page_title_back_rip = (RippleView) findViewById(R.id.page_title_back_rip);
        page_title_text_1 = (TextView) findViewById(R.id.page_title_text_1);
        right_button = (Button) findViewById(R.id.right_button);
        size_list_view = (ListView) findViewById(R.id.size_list_view);

        right_button.setBackgroundResource(R.drawable.size_list_right_selector);
        page_title_text_1.setText("尺码助手");
        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list != null) {
                    //List不为空时，小于20条数据时，才可以添加，最多20条
                    if (list.size() < 20) {
                        //跳转到尺码设置界面
                        Intent sizeSettingIntent = new Intent(SizeListActivity.this, SizeSettingActivity.class);
                        sizeSettingIntent.putExtra("isAdd", false);
                        startActivityForResult(sizeSettingIntent, SIZESETTING_REQUEST);
                    } else {
                        Toast.makeText(SizeListActivity.this, "最多添加20个角色", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //跳转到尺码设置界面
                    Intent sizeSettingIntent = new Intent(SizeListActivity.this, SizeSettingActivity.class);
                    sizeSettingIntent.putExtra("isAdd", false);
                    startActivityForResult(sizeSettingIntent, SIZESETTING_REQUEST);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SIZESETTING_REQUEST:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
