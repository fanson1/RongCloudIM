package com.fanhy.rongcloudim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fanhy.rongcloudim.app.RongCloudApp;
import com.fanhy.rongcloudim.entity.BaseUser;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class TalkGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_group);
        ButterKnife.inject(this);
    }

    private void createTalkGroup() {
        ArrayList<String> userIds = new ArrayList<String>();
        for (BaseUser user : RongCloudApp.baseUserList) {
            userIds.add(user.getUserId());
        }
        Log.d("fanhy", "userIds:" + userIds);
        RongIM.getInstance().getRongIMClient().createDiscussion("讨论组", userIds, new RongIMClient.CreateDiscussionCallback() {
            @Override
            public void onSuccess(String s) {
                RongCloudApp.talkGroupId = s;
                Log.d("fanhy", "创建讨论组成功,组id:" + s);
                RongIM.getInstance().startDiscussionChat(TalkGroupActivity.this, s, "讨论一下");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("fanhy", "创建讨论组失败:" + errorCode);
            }
        });
    }

    @OnClick(R.id.btn_create_group)
    public void onClick() {
        createTalkGroup();
    }
}
