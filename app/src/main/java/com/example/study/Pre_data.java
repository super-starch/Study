package com.example.study;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Pre_data extends AppCompatActivity implements View.OnClickListener {

    private Button btn_logout;
    private Button btn_shop;
    private TextView tv_integral;
    private Button btn_changepassword;

    private SharedPreferences shared;
    private int mRequestCode=0;

    private int integral;
    private int point;
    private String phone;
    private String nowphone;
    private String mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_data);

        btn_logout=(Button) findViewById(R.id.btn_logout);
        btn_shop=(Button) findViewById(R.id.btn_shop);
        tv_integral=(TextView) findViewById(R.id.tv_integral);
        btn_changepassword=(Button) findViewById(R.id.btn_changepassword);
        btn_logout.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        btn_changepassword.setOnClickListener(this);
        shared=getSharedPreferences("share",MODE_PRIVATE);
        integral=shared.getInt("integral",0);
        nowphone=shared.getString("nowphone","");
        point=shared.getInt(nowphone+"point",0);
        String integrals=Integer.toString(point);
        tv_integral.setText("积分："+integrals);
    }

    public void onClick(View v){
        if (v.getId()==R.id.btn_logout){
            SharedPreferences.Editor editor=shared.edit();
            editor.putBoolean("islogin",false);
            editor.putBoolean("rember",false);
            editor.putString("phone","");
            editor.putString("password","");
            editor.putString("nowphone","");
            editor.commit();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("注销成功");
            builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    Intent intent = new Intent(Pre_data.this, login.class);
                    startActivity(intent);
                }
            });
            AlertDialog alert=builder.create();
            alert.show();
        }
        if(v.getId()==R.id.btn_shop){
            Intent intent1 = new Intent(this,bookshelfActivity.class);
            startActivity(intent1);
        }
        if (v.getId()==R.id.btn_changepassword){
            Intent intent=new Intent(this,LoginForgetActivity.class);
            phone=shared.getString("nowphone","");
            intent.putExtra("phone",phone);
            startActivityForResult(intent,mRequestCode);
        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && data != null) {
            mpassword = data.getStringExtra("new_password");
            SharedPreferences.Editor editor=shared.edit();
            editor.putString(phone,mpassword);
            editor.commit();
        }
    }
}
