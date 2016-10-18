package com.fanhy.rongcloudim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.edt_register_name)
    EditText edtRegisterName;
    @InjectView(R.id.edt_register_pwd)
    EditText edtRegisterPwd;
    @InjectView(R.id.btn_register)
    Button btnRegister;
    OkHttpUtil.ResultCallback callback = new OkHttpUtil.ResultCallback<String>() {

        @Override
        public void onSuccess(String response) {
            if (response != null) {
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                BaseUser baseUser = new Gson().fromJson(response.toString(), BaseUser.class);
                RongCloudApp.token = baseUser.getToken();
                RongCloudApp.connect(RongCloudApp.token);
                RongCloudApp.baseUser = baseUser;
                finish();

            } else{
                Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(getApplicationContext(), "注册失败:"+e.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @InjectView(R.id.activity_register)
    LinearLayout activityRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
    };

    @OnClick(R.id.btn_register)
    public void onClick() {
        String name = edtRegisterName.getText().toString();
        String pwd = edtRegisterPwd.getText().toString();

        String url = ContansURL.register;
        List<OkHttpUtil.Param> params = new ArrayList<>();
        params.add(new OkHttpUtil.Param("name", name));
        params.add(new OkHttpUtil.Param("pwd", pwd));
        params.add(new OkHttpUtil.Param("appKey", RongCloudApp.appKey));
        params.add(new OkHttpUtil.Param("appSecret", RongCloudApp.appSecret));

        OkHttpUtil.post(url, callback, params);
    }
}
