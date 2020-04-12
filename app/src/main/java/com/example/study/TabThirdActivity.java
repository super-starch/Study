package com.example.study;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.SharedPreferences;

public class TabThirdActivity extends Activity implements View.OnClickListener {
    private SharedPreferences shared;
    private boolean Islogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_third);

        findViewById(R.id.btn_ctrl).setOnClickListener(this);
        findViewById(R.id.btn_per_data).setOnClickListener(this);

        shared=getSharedPreferences("share",MODE_PRIVATE);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btn_ctrl){
            Intent intent = new Intent(this,Ctrl.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.btn_per_data){
            Islogin=shared.getBoolean("islogin",false);
            if (!Islogin){
                Intent intent = new Intent(this,login.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this,Pre_data.class);
                startActivity(intent);
            }
        }
    }
}
