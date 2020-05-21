package com.example.study;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rg_login;
    private RadioButton rb_password;
    private RadioButton rb_verifycode;
    private EditText et_phone;
    private TextView tv_password;
    private EditText et_password;
    private Button btn_forget;
    private CheckBox ck_remeber;
    private Button btn_login;
    private Button btn_regist;

    private int mRequestCode=0;
    private boolean bRemeber=false;
    private String mpassword="111111";
    private String mVerifyCode;

    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rg_login=(RadioGroup) findViewById(R.id.rg_login);
        rb_password=(RadioButton) findViewById(R.id.rb_password);
        rb_verifycode=(RadioButton) findViewById(R.id.rb_verifycode);
        et_phone=(EditText) findViewById(R.id.et_phone);
        tv_password=(TextView)findViewById(R.id.tv_password);
        et_password=(EditText) findViewById(R.id.et_password);
        btn_forget=(Button) findViewById(R.id.btn_forget);
        ck_remeber=(CheckBox) findViewById(R.id.ck_remember);
        btn_login=(Button) findViewById(R.id.btn_login);
        btn_regist=(Button) findViewById(R.id.btn_regist);

        rg_login.setOnCheckedChangeListener(new RadioListener());
        ck_remeber.setOnCheckedChangeListener(new CheckListener());
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone));
        et_password.addTextChangedListener(new HideTextWatcher(et_password));
        btn_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_regist.setOnClickListener(this);

        shared=getSharedPreferences("share",MODE_PRIVATE);
        String Phone=shared.getString("phone","");
        String password=shared.getString("password","");
        boolean Rember=shared.getBoolean("rember",false);
        et_phone.setText(Phone);
        et_password.setText(password);
        bRemeber=Rember;
        if (Rember==true) ck_remeber.setChecked(true);
    }

    private class RadioListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group,int checkedId){
            if (checkedId==R.id.rb_password){
                tv_password.setText("登陆密码:");
                et_password.setHint("请输入密码");
                btn_forget.setText("忘记密码");
                ck_remeber.setVisibility(View.VISIBLE);
            }
            else if (checkedId==R.id.rb_verifycode){
                tv_password.setText("  验证码:");
                et_password.setHint("请输入验证码");
                btn_forget.setText("获取验证码");
                ck_remeber.setVisibility(View.INVISIBLE);
            }
        }
    }

    private class CheckListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
            if (buttonView.getId()==R.id.ck_remember){
                bRemeber=isChecked;
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
                ViewUtil.hideOneInputMethod(login.this,mView);
            }
        }
    }

    @Override
    public void onClick(View v){
        String phone=et_phone.getText().toString();
        if (v.getId()==R.id.btn_forget){
            if (phone==null||phone.length()<11){
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rb_password.isChecked()==true){
                Intent intent=new Intent(this,LoginForgetActivity.class);
                intent.putExtra("phone",phone);
                startActivityForResult(intent,mRequestCode);
            }
            else if (rb_verifycode.isChecked()==true){
                mVerifyCode=String.format("%06d",(int)(Math.random()*1000000%1000000));
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号"+phone+"，本次验证码是"+mVerifyCode+"，请输入验证码");
                builder.setPositiveButton("好的",null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
        else if (v.getId()==R.id.btn_login){
            mpassword=shared.getString(et_phone.getText().toString(),"");
            if (phone==null||phone.length()<11){
                Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                return;
            }
            if(mpassword==null){
                Toast.makeText(this,"账号不存在或密码错误",Toast.LENGTH_SHORT).show();
                return;
            }
            if (rb_password.isChecked()==true){
                if (et_password.getText().toString().equals(mpassword)!=true){
                    Toast.makeText(this,"账号不存在或密码错误",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    loginSuccess();
                }
            }
            if (rb_verifycode.isChecked()==true){
                if (et_password.getText().toString().equals(mVerifyCode)!=true){
                    Toast.makeText(this,"账号不存在或验证码错误",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    loginSuccess();
                }
            }
        }
        else if (v.getId()==R.id.btn_regist){
            Intent intent=new Intent(this,RegistActivity.class);
            startActivity(intent);
        }
    }

    //@Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && data != null) {
            mpassword = data.getStringExtra("new_password");
            SharedPreferences.Editor editor=shared.edit();
            editor.putString(et_phone.getText().toString(),mpassword);
            editor.commit();
        }
    }

    @Override
    protected void onRestart(){
        et_password.setText("");
        super.onRestart();
    }

    private void loginSuccess() {
        if (bRemeber){
            SharedPreferences.Editor editor=shared.edit();
            editor.putString("phone",et_phone.getText().toString());
            editor.putString("nowphone",et_phone.getText().toString());
            editor.putString("password",et_password.getText().toString());
            editor.putString(et_phone.getText().toString(),et_password.getText().toString());
            editor.putBoolean("rember",true);
            editor.putBoolean("islogin",true);
            editor.commit();
        }
        else {
            SharedPreferences.Editor editor=shared.edit();
            editor.putString("phone","");
            editor.putString("nowphone",et_phone.getText().toString());
            editor.putString("password","");
            editor.putBoolean("rember",false);
            editor.putBoolean("islogin",true);
            editor.commit();
        }
        String desc = String.format("您的手机号码是%s,恭喜你通过登录验证",
                et_phone.getText().toString());
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("登陆成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}
