package com.fanhy.rongcloudim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_user_list, R.id.btn_talk_list, R.id.btn_talk_group, R.id.btn_team_group, R.id.btn_chat_room, R.id.btn_sys_talk, R.id.btn_cus_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_user_list:
                startActivity(new Intent(this, UserListActivity.class));
                break;
            case R.id.btn_talk_list:
                RongIM.getInstance().startConversationList(this);
                break;
            case R.id.btn_talk_group:
                startActivity(new Intent(this, TalkGroupActivity.class));
                break;
            case R.id.btn_team_group:
                startActivity(new Intent(this, TeamGroupActivity.class));
                break;
            case R.id.btn_chat_room:
                break;
            case R.id.btn_sys_talk:
                break;
            case R.id.btn_cus_service:
                break;
        }
    }
}
