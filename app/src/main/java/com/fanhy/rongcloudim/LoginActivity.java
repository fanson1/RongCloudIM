package com.fanhy.rongcloudim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

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
                RongCloudApp.token = baseUser.getToken();
                RongCloudApp.connect(RongCloudApp.token);
            }
        }

        @Override
        public void onFailure(Exception e) {

        }
    };

    @OnClick(R.id.btn_login)
    public void onClick() {
    }
}
