package com.example.study;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TabFirstActivity extends Activity {
    private TextView etUSERNUMBER;
    private TextView etPASSWORD;

    private Button mBtnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_first);

        initView();
        initListenner();
    }

    private void initListenner() {
        mBtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUSERNUMBER.getText().toString();
                final String password = etPASSWORD.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    etUSERNUMBER.setError("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    etPASSWORD.setError("密码不能为空");
                    return;
                }
                /*ThreadUtils.runThread(new Runnable(){
                    public void run(){
                        try {
                        //创建连接配置对象
                        Connection confige = new ("192.168.1.100", 5222) ;

                        //主机IP，端口号

                        //开始创建连接对象
                        XmlPullParser connection = new XmlPullPaser(confige);
                        //开始连接
                        connection.connect();
                        //开始登录
                        connection.login(username,password);
                        //连接成功
                            ToastUtils.showToastSafe(LoginActivity.this,"登陆成功");
                            finish();
                            //调到主界面
                            Intent intent=new Intent(LoginActivity.this,Main2Activity.class)//主界面
                    }catch(XmlPullParserException e){
                        e.printStackTrace();
                            ToastUtils.showToastSafe(LoginActivity.this,"登陆失败");
                    }
                    }
                });*/


            }
        });
    }

    private void initView() {
        etUSERNUMBER = (TextView) findViewById(R.id.et_username);
        etPASSWORD = (TextView) findViewById(R.id.et_password);
        mBtnlogin = (Button) findViewById(R.id.btn_login);
    }
}
