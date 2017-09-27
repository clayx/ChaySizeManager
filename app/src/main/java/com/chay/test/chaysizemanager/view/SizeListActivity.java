package com.chay.test.chaysizemanager.view;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.adapter.SizeListAdapter;
import com.chay.test.chaysizemanager.base.BaseNewActivity;
import com.chay.test.chaysizemanager.info.SizeDaoInfo;
import com.chay.test.chaysizemanager.info.SizeListInfo;
import com.chay.test.chaysizemanager.otherView.RippleView;
import com.chay.test.chaysizemanager.pop.CommButtonDlg;
import com.chay.test.chaysizemanager.util.Preconditions;
import com.chay.test.chaysizemanager.util.dao.SizeDaoUtils;

import java.util.ArrayList;
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

    private List<SizeDaoInfo> list;

    private CommButtonDlg dlg;

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
        list = SizeDaoUtils.queryAll();
        if (Preconditions.isNullOrEmpty(list)) {
            list = new ArrayList<>();
        }
        adapter = new SizeListAdapter(this, list);
        size_list_view.setAdapter(adapter);
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
        size_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Long id = list.get(i).getId();
                String isDefault = Preconditions.isNullOrEmpty(list.get(i).getIsDefault()) ? "0" : list.get(i).getIsDefault();
                String sex = Preconditions.nullToEmpty(list.get(i).getSex());
                Intent sizeSettingIntent = new Intent(SizeListActivity.this, SizeSettingActivity.class);
                sizeSettingIntent.putExtra("isAdd", true);
                sizeSettingIntent.putExtra("id", id);
                sizeSettingIntent.putExtra("default", isDefault);
                sizeSettingIntent.putExtra("sex", sex);
                startActivityForResult(sizeSettingIntent, SIZESETTING_REQUEST);
            }
        });

        size_list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDeleteDialog(view, i);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SIZESETTING_REQUEST:
                list = SizeDaoUtils.queryAll();
                adapter.setList(list);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //删除弹窗
    public void showDeleteDialog(View view, final int pos) {
        dlg = new CommButtonDlg(this, "是否删除此角色？", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
                if (view.getId() == R.id.bnConfirm) {
                    SizeDaoInfo info = list.get(pos);
                    //先删除list中的数据
                    list.remove(info);
                    adapter.setList(list);
                    //在删除数据库中的数据
                    SizeDaoUtils.deleteSizeDate(info.getId());
                }
            }
        }, true);
        dlg.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

}
