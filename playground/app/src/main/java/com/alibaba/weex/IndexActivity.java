package com.alibaba.weex;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.weex.commons.AbstractWeexActivity;
import com.alibaba.weex.commons.util.OtherUtil;
import com.alibaba.weex.commons.util.ScreenUtil;
import com.alibaba.weex.constants.Constants;
import com.google.zxing.client.android.CaptureActivity;
import com.taobao.weex.WXRenderErrorCode;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXSoInstallMgrSdk;

/**
 * don' t delete or edit it, it will be used while packaging  release delete head  release delete tail
 * release delete head
 * release delete head
 * release delete head
 * release delete head
 * release delete head
 * release delete head
 */
/** release delete head */

/**
 * release delete tail
 */

public class IndexActivity extends AbstractWeexActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 0x1;

    private ProgressBar mProgressBar;
    private TextView mTipView;

    private BroadcastReceiver mReloadReceiver;
    private int mToolBarHeight = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setContainer((ViewGroup) findViewById(R.id.index_container));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("WEEX");
        setSupportActionBar(toolbar);

        mProgressBar = (ProgressBar) findViewById(R.id.index_progressBar);
        mTipView = (TextView) findViewById(R.id.index_tip);
        mProgressBar.setVisibility(View.VISIBLE);
        mTipView.setVisibility(View.VISIBLE);


        if (!WXSoInstallMgrSdk.isCPUSupport()) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mTipView.setText(R.string.cpu_not_support_tip);
            return;
        }

        render();


        mReloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                createWeexInstance();
                render();

                mProgressBar.setVisibility(View.VISIBLE);
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(mReloadReceiver, new IntentFilter(WXSDKEngine.JS_FRAMEWORK_RELOAD));

        //Release版本隐藏Debug版本中的TitleBar
        RelativeLayout.LayoutParams titlebarParam = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        if (!BuildConfig.DEBUG) {
            titlebarParam.height = 0;
            toolbar.setLayoutParams(titlebarParam);
        }
        mToolBarHeight = titlebarParam.height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return BuildConfig.DEBUG;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            if (Constants.WeexIndex.isUrl()) {
                //url入口才重新热部署
                createWeexInstance();
                render();

                mProgressBar.setVisibility(View.VISIBLE);
                return true;
            }
        } else if (id == R.id.action_scan) {
            if (OtherUtil.isEmulator(this)) {
                startActivity(new Intent(this, SimulatorDebugActivity.class));
                return true;
            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Toast.makeText(this, "please give me the permission", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                }
            } else {

                /** don' t delete or edit it, it will be used while packaging */
                /** release delete head */
                startActivity(new Intent(this, CaptureActivity.class));
                /** release delete tail */

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            /** don' t delete or edit it, it will be used while packaging */
            /** release delete head */
            startActivity(new Intent(this, CaptureActivity.class));
            /** release delete tail */

        } else {
            Toast.makeText(this, "request camara permission fail!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRenderSuccess(WXSDKInstance wxsdkInstance, int i, int i1) {
        mProgressBar.setVisibility(View.GONE);
        mTipView.setVisibility(View.GONE);
    }

    @Override
    public void onException(WXSDKInstance wxsdkInstance, String s, String s1) {
        mProgressBar.setVisibility(View.GONE);
        mTipView.setVisibility(View.VISIBLE);
        if (TextUtils.equals(s, WXRenderErrorCode.WX_NETWORK_ERROR)) {
            mTipView.setText(R.string.index_tip);
        } else {
            mTipView.setText("render error:" + s1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReloadReceiver);
    }

    /**
     * 渲染
     */
    private void render() {
        if (null == mInstance) {
            return;
        }

        mInstance.renderByUrl("weex", Constants.WeexIndex.getWeexIndex()
                , null, null, -1, ScreenUtil.getDisplayHeight(this) - mToolBarHeight, WXRenderStrategy.APPEND_ASYNC);
    }
}

