package com.example.study;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pre_data extends AppCompatActivity implements View.OnClickListener {

    private Button btn_logout;

    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_data);

        btn_logout=(Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);
        shared=getSharedPreferences("share",MODE_PRIVATE);
    }

    public void onClick(View v){
        if (v.getId()==R.id.btn_logout){
            SharedPreferences.Editor editor=shared.edit();
            editor.putBoolean("islogin",false);
            editor.commit();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("注销成功");
            builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    finish();
                }
            });
            AlertDialog alert=builder.create();
            alert.show();
        }
    }
}
