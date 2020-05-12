package com.example.study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;

import com.example.study.utils.ThreadUtils;

public class splashActivity extends AppCompatActivity {
    private SharedPreferences shared;
    private boolean Islogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        shared=getSharedPreferences("share",MODE_PRIVATE);
        //停留三秒进入主界面
        ThreadUtils.runThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                Islogin=shared.getBoolean("islogin",false);
                if (Islogin){
                    Intent intent = new Intent(splashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(splashActivity.this, login.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }
}
