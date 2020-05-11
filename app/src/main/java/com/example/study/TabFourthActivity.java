package com.example.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TabFourthActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_run_distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_fourth);
        et_run_distance=(EditText)findViewById(R.id.et_run_distance);

        findViewById(R.id.btn_run_start).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btn_run_start){
            String distance=et_run_distance.getText().toString();
            if(distance.length()<1){
                Toast.makeText(this,"请输入运动距离",Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                int distance_int=Integer.parseInt(distance);
                Intent intent = new Intent(this, RunActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("distance", distance_int);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
}
