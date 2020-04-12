package com.example.study;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_password_first;
    private EditText et_password_second;
    private EditText et_vertfyCode;
    private String mVerifyCode;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);
        et_password_first=(EditText) findViewById(R.id.et_password_first);
        et_password_second=(EditText) findViewById(R.id.et_password_second);
        et_vertfyCode=(EditText) findViewById(R.id.et_verifycode);
        findViewById(R.id.btn_verifycode).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
        mPhone=getIntent().getStringExtra("phone");

        et_password_first.addTextChangedListener(new HideTextWatcher(et_password_first));
        et_password_second.addTextChangedListener(new HideTextWatcher(et_password_second));
        et_vertfyCode.addTextChangedListener(new HideTextWatcher(et_vertfyCode));
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.btn_verifycode){
            if (mPhone==null||mPhone.length()<11){
                Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                return;
            }
            mVerifyCode=String.format("%06d",(int)(Math.random() * 1000000 % 1000000));
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("请记住验证码");
            builder.setMessage("手机号"+mPhone+"，本次验证码是"+mVerifyCode+"，请输入验证码");
            builder.setPositiveButton("好的",null);
            AlertDialog alert=builder.create();
            alert.show();
        }
        else if (v.getId()==R.id.btn_confirm){
            String password_first=et_password_first.getText().toString();
            String password_second=et_password_second.getText().toString();
            if (password_first==null||password_first.length()<6||
                    password_second==null||password_second.length()<6){
                Toast.makeText(this,"请输入正确的新密码",Toast.LENGTH_SHORT).show();
                return;
            }
            if (password_first.equals(password_second)!=true){
                Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                return;
            }
            if (et_vertfyCode.getText().toString().equals(mVerifyCode)!=true){
                Toast.makeText(this,"请输入正确的验证码",Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Toast.makeText(this,"密码修改成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.putExtra("new_password",password_first);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        }
    }

    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLengrh;
        private CharSequence mStr;

        public HideTextWatcher(EditText v) {
            super();
            mView=v;
            mMaxLengrh= ViewUtil.getMaxLength(v);
        }

        @Override
        public void beforeTextChanged(CharSequence s,int start,int count,int after){

        }

        @Override
        public void onTextChanged(CharSequence s,int start,int before,int count){
            mStr=s;
        }

        @Override
        public void afterTextChanged(Editable s){
            if (mStr==null||mStr.length()==0)
                return;
            if((mStr.length()==11&&mMaxLengrh==11)||
                    (mStr.length()==6&&mMaxLengrh==6)){
                ViewUtil.hideOneInputMethod(LoginForgetActivity.this,mView);
            }
        }
    }
}
