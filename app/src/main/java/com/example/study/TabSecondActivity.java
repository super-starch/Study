package com.example.study;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TabSecondActivity extends Activity implements View.OnClickListener {

    private EditText et_selfstudy_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_second);
        et_selfstudy_time=(EditText)findViewById(R.id.et_selfstudy_time);

        findViewById(R.id.btn_selfstudy_start).setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btn_selfstudy_start){
            String time=et_selfstudy_time.getText().toString();
            if(time.length()<1){
                Toast.makeText(this,"请输入自习时间",Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                int time_int=Integer.parseInt(time);
                Intent intent = new Intent(this, selfstudyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("time", time_int);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    protected void onRestart(){
        et_selfstudy_time.setText("");
        super.onRestart();
    }
}
