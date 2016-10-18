package com.fanhy.rongcloudim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fanhy.rongcloudim.app.RongCloudApp;
import com.fanhy.rongcloudim.contans.ContansURL;
import com.fanhy.rongcloudim.entity.BaseUser;
import com.fanhy.rongcloudim.util.OkHttpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.edt_login_name)
    EditText edtLoginName;
    @InjectView(R.id.edt_login_pwd)
    EditText edtLoginPwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.activity_register)
    LinearLayout activityRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    OkHttpUtil.ResultCallback callback = new OkHttpUtil.ResultCallback<String>() {

        @Override
        public void onSuccess(String response) {
            if (response != null) {
                BaseUser baseUser = new Gson().fromJson(response.toString(), BaseUser.class);
                Toast.makeText(getApplicationContext(), "登录成功:"+baseUser.getName(), Toast.LENGTH_SHORT).show();
                RongCloudApp.token = baseUser.getToken();
                RongCloudApp.connect(RongCloudApp.token);
                RongCloudApp.baseUser = baseUser;

                startActivity(new Intent(LoginActivity.this, ChatActivity.class));

                finish();
            } else{
                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(getApplicationContext(), "登陆失败.:"+toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @OnClick(R.id.btn_login)
    public void onClick() {
        String name = edtLoginName.getText().toString();
        String pwd = edtLoginPwd.getText().toString();

        String url = ContansURL.login;
        List<OkHttpUtil.Param> params = new ArrayList<>();
        params.add(new OkHttpUtil.Param("name", name));
        params.add(new OkHttpUtil.Param("pwd", pwd));

        OkHttpUtil.post(url, callback, params);
    }
}
