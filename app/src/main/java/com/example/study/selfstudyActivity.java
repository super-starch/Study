package com.example.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import com.example.study.BackgroundUtil;

public class selfstudyActivity extends AppCompatActivity implements View.OnClickListener {

    private int minute;
    private int second;
    private TextView tv_selfstudy_time;
    private Button btn_selfstudy_finsh;
    private Timer timer = null;
    private TimerTask timerTask = null;
    private String mPackageName;
    private Context mContext;
    private Boolean isfail=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfstudy);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        minute=bundle.getInt("time",0);
        btn_selfstudy_finsh=(Button)findViewById(R.id.btn_selfstudy_finsh);
        tv_selfstudy_time=(TextView)findViewById(R.id.tv_selfstudy_time);

        btn_selfstudy_finsh.setOnClickListener(this);

        mPackageName="com.example.study";
        mContext=this;

        startTime();
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.btn_selfstudy_finsh){
            forcefinshstudy();
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
                if (!isfail&&!BackgroundUtil.isForeground(mContext,3,mPackageName)){
                    isfail=true;
                }
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
            if(isfail) return false;
            if (minute == 0) {
                if (second == 0) {
                    //tv_selfstudy_time.setText("Time out !");
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    if (timerTask != null) {
                        timerTask = null;
                    }
                    finishstudy();
                } else {
                    second--;
                    if (second >= 10) {
                        tv_selfstudy_time.setText("0" + minute + ":" + second);
                    } else {
                        tv_selfstudy_time.setText("0" + minute + ":0" + second);
                    }
                }
            } else {
                if (second == 0) {
                    second = 59;
                    minute--;
                    if (minute >= 10) {
                        tv_selfstudy_time.setText(minute + ":" + second);
                    } else {
                        tv_selfstudy_time.setText("0" + minute + ":" + second);
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        if (minute >= 10) {
                            tv_selfstudy_time.setText(minute + ":" + second);
                        } else {
                            tv_selfstudy_time.setText("0" + minute + ":" + second);
                        }
                    } else {
                        if (minute >= 10) {
                            tv_selfstudy_time.setText(minute + ":0" + second);
                        } else {
                            tv_selfstudy_time.setText("0" + minute + ":0" + second);
                        }
                    }
                }
            }

            return false;
        }
    });

    private void finishstudy(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("自习结束");
        builder.setMessage("快去休息吧!");
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                finish();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }


    private void forcefinshstudy(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("确定要放弃自习吗？");
        builder.setMessage("提前放弃自习会失去奖励哦");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                finish();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    @Override
    protected void onRestart(){
        if (isfail){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("自习失败");
            builder.setMessage("失败了!可能是因为打开了其他应用");
            builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    finish();
                }
            });
            AlertDialog alert=builder.create();
            alert.show();
        }
        super.onRestart();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            forcefinshstudy();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }
}
