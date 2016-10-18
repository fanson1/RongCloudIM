package com.fanhy.rongcloudim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class SystemMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_create_sys)
    public void onClick() {
        RongIM.getInstance().startConversation(this,
                Conversation.ConversationType.SYSTEM, "9527", "标题");
    }
}
