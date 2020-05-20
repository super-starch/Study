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

    private SharedPreferences shared;

    private int integral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_data);

        btn_logout=(Button) findViewById(R.id.btn_logout);
        btn_shop=(Button) findViewById(R.id.btn_shop);
        tv_integral=(TextView) findViewById(R.id.tv_integral);
        btn_logout.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        shared=getSharedPreferences("share",MODE_PRIVATE);
        integral=shared.getInt("integral",0);
        String integrals=Integer.toString(integral);
        tv_integral.setText("积分："+integrals);
    }

    public void onClick(View v){
        if (v.getId()==R.id.btn_logout){
            SharedPreferences.Editor editor=shared.edit();
            editor.putBoolean("islogin",false);
            editor.putBoolean("rember",false);
            editor.putString("phone","");
            editor.putString("password","");
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
    }
}
