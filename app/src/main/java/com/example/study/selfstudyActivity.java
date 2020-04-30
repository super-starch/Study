package com.example.study;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
                        if (cnt<3){
                            notificationAPI_16p();
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
        editor.putInt("integral",integral+1);
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

    public void notificationAPI_16p() {
        // 获取NotificationManager管理者对象
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 创建一个PendingIntent，和Intent类似，不同的是由于不是马上调用，需要在下拉状态条出发的Activity，所以采用的是PendingIntent,即点击Notification跳转启动到哪个Activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        // 通过Notification.Builder来创建通知，注意API Level 16之后才支持
        Notification notificationAPI_16p = new Notification.Builder(this)
                // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap icon)
                .setSmallIcon(R.mipmap.ic_launcher)
                // 设置在status bar上显示的提示文字
                .setTicker("TickerText:" + "您有新短消息，请注意查收！")
                // 设置在下拉status bar后显示的标题
                .setContentTitle("这里是标题（API 16+）")
                // 设置在下拉status bar后显示的内容
                .setContentText("这里是显示的内容")
                // 关联PendingIntent
                .setContentIntent(pendingIntent)
                // 设置在下拉status bar后显示的数字
                .setNumber(1)
                // 需要注意build()是在API level 16及之后增加的，API11可以使用getNotificatin()来替代
                .build();
        // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        notificationAPI_16p.flags |= Notification.FLAG_AUTO_CANCEL;
        // 通过通知管理器来发起通知
        manager.notify(NOTIFICATION_FLAG, notificationAPI_16p);
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
