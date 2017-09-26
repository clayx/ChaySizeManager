package com.chay.test.chaysizemanager.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.chay.test.chaysizemanager.otherView.RippleView;

/**
 * Created by Chay on 16/3/28.
 */
public abstract class BaseNewActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        handleIntent(getIntent());

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(getContentView());

        initView();

        initData();

    }


    @Override
    protected void onResume() {
        super.onResume();
        handleOnResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handleOnStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handleOnDestroy();
    }


    protected void initView() {

    }

    private void initData() {
        handleBackImg();
        initDatas();
    }


    /**
     * 跳转
     *
     * @param targetActivity
     */
    protected void handleJump(Class<Activity> targetActivity) {

        startActivity(new Intent(this, targetActivity));
    }

    /**
     * toast提示
     *
     * @param toastContent
     */
    protected void showToast(String toastContent) {
        Toast.makeText(this, toastContent, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回按钮的处理
     */
    protected void handleBackImg() {

        View backImg = getBackImg();
        if (backImg != null) {
            if (backImg instanceof RippleView) {
                RippleView mRippleView = (RippleView) backImg;
                mRippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                    @Override
                    public void onComplete(RippleView rippleView) {
                        finish();
                    }
                });
                return;
            }
            backImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

    }

    protected abstract View getBackImg();


    /**
     * ondestroy生命周期
     */
    protected abstract void handleOnDestroy();

    /**
     * onstop生命周期
     */
    protected abstract void handleOnStop();

    /**
     * onResume生命周期
     */
    protected abstract void handleOnResume();

    /**
     * 设置ContentView
     *
     * @return
     */
    protected abstract int getContentView();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();


    /**
     * 继承者选择性复写
     *
     * @param intent
     */
    protected void handleIntent(Intent intent) {
    }

    /**
     * 由于异常原因需要退出本activity
     *
     * @param err
     */
    protected void exitMe(String err) {
        if (!TextUtils.isEmpty(err)) {
            Toast.makeText(this,err,Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
