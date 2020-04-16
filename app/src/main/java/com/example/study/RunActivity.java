package com.example.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class RunActivity extends AppCompatActivity implements View.OnClickListener {
    private int distance;
    private int minute=0;
    private int second=0;
    private Timer timer = null;
    private TimerTask timerTask = null;
    private TextView tv_run_time;
    private Button btn_run_finsh;
    private Button btn_run_pause;
    private Boolean ispause=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        distance=bundle.getInt("distance",0);

        tv_run_time=(TextView)findViewById(R.id.tv_run_time);
        btn_run_finsh=(Button)findViewById(R.id.btn_run_finsh);
        btn_run_pause=(Button)findViewById(R.id.btn_run_pause);

        btn_run_pause.setOnClickListener(this);
        btn_run_finsh.setOnClickListener(this);

        startTime();
    }

    //@Override
    public void onClick(View v){
        if (v.getId()==R.id.btn_run_pause){
            if (ispause){
                ispause=false;
            }
            else {
                ispause=true;
            }
        }
        if (v.getId()==R.id.btn_run_finsh){
            forcefinshsrun();
        }
    }

    private void startTime(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask = null;
        }
        timerTask = new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        };


        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }


    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (!ispause){
                if (minute == 0) {
                    second++;
                    if (second >= 10) {
                        tv_run_time.setText("0" + minute + ":" + second);
                    } else {
                        tv_run_time.setText("0" + minute + ":0" + second);
                    }
                } else {
                    if (second == 59) {
                        second = 0;
                        minute++;
                        if (minute >= 10) {
                            tv_run_time.setText(minute + ":" + second);
                        } else {
                            tv_run_time.setText("0" + minute + ":" + second);
                        }
                    } else {
                        second++;
                        if (second >= 10) {
                            if (minute >= 10) {
                                tv_run_time.setText(minute + ":" + second);
                            } else {
                                tv_run_time.setText("0" + minute + ":" + second);
                            }
                        } else {
                            if (minute >= 10) {
                                tv_run_time.setText(minute + ":0" + second);
                            } else {
                                tv_run_time.setText("0" + minute + ":0" + second);
                            }
                        }
                    }
                }

                return false;
            }

            return false;
        }
    });

    private void forcefinshsrun() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("结束运动");
        builder.setMessage("确定要结束运动吗？");
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                finish();
            }
        });
        builder.setNegativeButton("取消",null);
        AlertDialog alert=builder.create();
        alert.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            forcefinshsrun();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }
}
