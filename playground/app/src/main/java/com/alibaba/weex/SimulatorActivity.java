package com.alibaba.weex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SimulatorActivity extends AppCompatActivity {
    private ImageView mBackImageView;
    private EditText mDebugHostEditText;
    private EditText mDebugPortEditText;
    private EditText mServerEditText;
    private Button mConnectDevToolButton;
    private Button mConnectServerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);
        initView();
        initListener();
    }

    /**
     * 初始化视图
     */
    private void initView(){
        mBackImageView = (ImageView) findViewById(R.id.simulator_iv_back);
        mDebugHostEditText = (EditText) findViewById(R.id.simulator_et_debug_host);
        mDebugPortEditText = (EditText) findViewById(R.id.simulator_et_debug_port);
        mServerEditText = (EditText) findViewById(R.id.simulator_et_server);
        mConnectDevToolButton = (Button)findViewById(R.id.simulator_btn_connect_devtool);
        mConnectServerButton = (Button)findViewById(R.id.simulator_btn_connect_server);
    }

    /**
     * 初始化监听
     */
    private void initListener(){
        mConnectDevToolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //连接debug
            }
        });

        mConnectServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //连接Server
            }
        });
    }
}
