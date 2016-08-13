package com.alibaba.weex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          //显示Splash页面1秒
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        startActivity(new Intent(SplashActivity.this, IndexActivity.class));
        finish();
      }
    }).start();
  }
}
