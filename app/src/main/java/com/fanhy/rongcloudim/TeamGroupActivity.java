package com.fanhy.rongcloudim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fanhy.rongcloudim.app.RongCloudApp;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class TeamGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_group);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_create_team)
    public void onClick() {
        /*RongIM.getInstance().startConversation(this, Conversation.ConversationType.CHATROOM,
                "9527", "");*/
        RongIM.getInstance().startChatRoomChat(this, "10010", true);
    }
}
