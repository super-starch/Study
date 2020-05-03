package com.example.study;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;

import java.util.Timer;
import java.util.TimerTask;

public class RunActivity extends AppCompatActivity implements View.OnClickListener,LocationSource, AMapLocationListener {
    private int goladdistance;
    private float distance=0;
    private int minute=0;
    private int second=0;
    private Timer timer = null;
    private TimerTask timerTask = null;
    private TextView tv_run_time;
    private Button btn_run_finsh;
    private Button btn_run_pause;
    private Boolean ispause=false;
    private TextView tv_run_distance;

    private AMap aMap;
    private MapView mMapView=null;
    //以前的定位点
    private LatLng oldLatLng;
    //是否是第一次定位
    private boolean isFirstLatLng;
    private Marker makerA;
    private Marker makerB;

    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        goladdistance=bundle.getInt("distance",0);

        tv_run_time=(TextView)findViewById(R.id.tv_run_time);
        btn_run_finsh=(Button)findViewById(R.id.btn_run_finsh);
        btn_run_pause=(Button)findViewById(R.id.btn_run_pause);
        tv_run_distance=(TextView)findViewById(R.id.tv_run_distance);

        btn_run_pause.setOnClickListener(this);
        btn_run_finsh.setOnClickListener(this);

        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        isFirstLatLng = true;
        init();

        tv_run_distance.setText("-1");
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
                /*if (minute == 0) {
                    second++;
                    if (second >= 10) {
                        tv_run_time.setText("0" + minute + ":" + second);
                    } else {
                        tv_run_time.setText("0" + minute + ":0" + second);
                    }
                } else {*/
                    if (second == 59) {
                        second = 0;
                        minute++;
                        if (minute >= 10) {
                            tv_run_time.setText(minute + ":" + "00");
                        } else {
                            tv_run_time.setText("0" + minute + ":" + "00");
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
                //}

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

    private  void init(){
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //定位
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位 LOCATION_TYPE_LOCATE、跟随 LOCATION_TYPE_MAP_FOLLOW 或地图根据面向方向旋转 LOCATION_TYPE_MAP_ROTATE
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);

        //画线
        // 缩放级别（zoom）：地图缩放级别范围为【4-20级】，值越大地图越详细
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        //使用 aMap.setMapTextZIndex(2) 可以将地图底图文字设置在添加的覆盖物之上
        aMap.setMapTextZIndex(2);
//        setUpMap(new LatLng(43.828, 87.621), new LatLng(43.800, 87.621));


    }
    /**绘制两个坐标点之间的线段,从以前位置到现在位置*/
    private void setUpMap(LatLng oldData,LatLng newData ) {

        // 绘制一个大地曲线
        aMap.addPolyline((new PolylineOptions())
                .add(oldData, newData)
                .geodesic(true).color(Color.GREEN));

    }


    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                //mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
//                //定位成功
                LatLng newLatLng = new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
//                Log.e("Amap", amapLocation.getLatitude() + "," + amapLocation.getLongitude());
//                Toast.makeText(this, amapLocation.getLatitude() + "," + amapLocation.getLongitude() , Toast.LENGTH_SHORT).show();

                if(isFirstLatLng){
                    //记录第一次的定位信息
                    oldLatLng = newLatLng;
                    isFirstLatLng = false;
                }
                //位置有变化
                if(oldLatLng != newLatLng){
                    Log.e("Amap", amapLocation.getLatitude() + "," + amapLocation.getLongitude());
                    setUpMap( oldLatLng , newLatLng );
                    oldLatLng = newLatLng;
                    makerA = aMap.addMarker(new MarkerOptions().position(oldLatLng)
                            .draggable(true)
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    makerB = aMap.addMarker(new MarkerOptions().position(newLatLng)
                            .draggable(true)
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                    distance = distance+AMapUtils.calculateLineDistance(makerA.getPosition(),makerB.getPosition());
                    //distance = distance+1;
                    String distances=Double.toString(distance);
                    tv_run_distance.setText(distances);
                }

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
//                Toast.makeText(this, errText, Toast.LENGTH_SHORT).show();
                if(isFirstLatLng){
                    Toast.makeText(this, errText, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            mLocationOption.setOnceLocation(false);
            /**
             * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
             * 注意：只有在高精度模式下的单次定位有效，其他方式无效
             */
            mLocationOption.setGpsFirst(true);
            // 设置发送定位请求的时间间隔,最小值为10000ms,10秒更新一次定位信息
            mLocationOption.setInterval(10000);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
