package com.example.study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_regist_regist;
    private EditText et_regist_phone;
    private EditText et_regist_password;

    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        btn_regist_regist=(Button) findViewById(R.id.btn_regist_regist);
        et_regist_password=(EditText) findViewById(R.id.et_regist_password);
        et_regist_phone=(EditText) findViewById(R.id.et_regist_phone);

        btn_regist_regist.setOnClickListener(this);

        shared=getSharedPreferences("share",MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        String phone=et_regist_phone.getText().toString();
        String password=et_regist_password.getText().toString();
        if (v.getId()==R.id.btn_regist_regist){
            if (phone==null||phone.length()<11){
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length()<6||password.length()>18){
                Toast.makeText(this, "密码长度为6-18位", Toast.LENGTH_SHORT).show();
                return;
            }
            registsuccescs();
        }
    }

    private void registsuccescs() {
        SharedPreferences.Editor editor=shared.edit();
        editor.putString(et_regist_phone.getText().toString(),et_regist_password.getText().toString());
        editor.commit();
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }
}
