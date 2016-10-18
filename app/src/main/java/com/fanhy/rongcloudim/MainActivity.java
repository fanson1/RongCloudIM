package com.fanhy.rongcloudim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_go_login, R.id.btn_go_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_go_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
