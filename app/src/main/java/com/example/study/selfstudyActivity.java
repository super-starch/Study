package com.example.study;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import java.util.Timer;
import java.util.TimerTask;

public class selfstudyActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int NOTIFICATION_FLAG = 0x00000008;
    private int minute;
    private int second;
    private TextView tv_selfstudy_time;
    private Button btn_selfstudy_finsh;
    private Timer timer = null;
    private TimerTask timerTask = null;
    private String mPackageName;
    private Context mContext;
    private Boolean isfail=false;
    private SharedPreferences shared;
    private int integral;
    private String nowphone;
    private int point;
    private int cnt=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfstudy);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        minute=bundle.getInt("time",0);
        //second=5;
        btn_selfstudy_finsh=(Button)findViewById(R.id.btn_selfstudy_finsh);
        tv_selfstudy_time=(TextView)findViewById(R.id.tv_selfstudy_time);

        btn_selfstudy_finsh.setOnClickListener(this);

        mPackageName="com.example.study";
        mContext=this;

        shared=getSharedPreferences("share",MODE_PRIVATE);
        integral=shared.getInt("integral",0);
        nowphone=shared.getString("nowphone","");
        point=shared.getInt(nowphone+"point",0);

        check();

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
                if (!isfail){
                    if (!BackgroundUtil.isForeground(mContext,3,mPackageName)){
                        if (cnt<5){
                            notification();
                            cnt++;
                        }
                        else {
                            isfail=true;
                        }
                    }
                    else {
                        cnt=0;
                    }
                }
                else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
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
        SharedPreferences.Editor editor=shared.edit();
        editor.putInt(nowphone+"point",point+1);
        editor.commit();
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

    public void notification(){
        Intent intent = new Intent(this,selfstudyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        NotificationManager mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder=new Notification.Builder(this,"5996773");
        }else {
            builder=new Notification.Builder(this);
        }
        //设置标题
        builder.setContentTitle("请关闭其他应用");
        //设置内容
        builder.setContentText("继续使用其他应用可能会导致自习失败");
        //设置状态栏显示的图标，建议图标颜色透明
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // 设置通知灯光（LIGHTS）、铃声（SOUND）、震动（VIBRATE）、（ALL 表示都设置）
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        //灯光三个参数，颜色（argb）、亮时间（毫秒）、暗时间（毫秒）,灯光与设备有关
        //builder.setLights(Color.RED, 200, 200);
        // 铃声,传入铃声的 Uri（可以本地或网上）我这没有铃声就不传了
        //builder.setSound(Uri.parse("")) ;
        // 震动，传入一个 long 型数组，表示 停、震、停、震 ... （毫秒）
        builder.setVibrate(new long[]{0, 200, 200, 200, 200, 200});
        // 通知栏点击后自动消失
        builder.setAutoCancel(true);
        // 简单通知栏设置 Intent
        //builder.setContentIntent(pendingIntent);
        builder.setPriority(Notification.PRIORITY_HIGH);

        //设置下拉之后显示的图片
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.tab_bg_normal));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("5996773", "安卓10a", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);//是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN);//小红点颜色
            channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
            mNManager.createNotificationChannel(channel);
        }
        notification=builder.build();
        mNManager.notify(1,notification);
    }

    private void check(){
        NotificationManagerCompat notification = NotificationManagerCompat.from(this);
        boolean isEnabled = notification.areNotificationsEnabled();
        if (!isEnabled) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
