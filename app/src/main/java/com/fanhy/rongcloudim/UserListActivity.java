package com.fanhy.rongcloudim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import com.fanhy.rongcloudim.adapter.MyRecyclerAdapter;
import com.fanhy.rongcloudim.app.RongCloudApp;
import com.fanhy.rongcloudim.contans.ContansURL;
import com.fanhy.rongcloudim.entity.BaseUser;
import com.fanhy.rongcloudim.util.OkHttpUtil;
import com.fanhy.rongcloudim.view.RecyclerDiver;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserListActivity extends AppCompatActivity {
    @InjectView(R.id.rv_user_list)
    RecyclerView rvUserList;
    @InjectView(R.id.activity_user_list)
    RelativeLayout activityUserList;

    MyRecyclerAdapter adapter;
    List model = new ArrayList<>();
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.inject(this);

        Log.d("fanhy", "UserList --> onCreate");
        String url = ContansURL.getAllUser;
        OkHttpUtil.get(url, callback);
    }

    OkHttpUtil.ResultCallback callback = new OkHttpUtil.ResultCallback() {
        @Override
        public void onSuccess(Object response) {
            BaseUser[] arr = new Gson().fromJson(response.toString(), BaseUser[].class);
            model.clear();
            RongCloudApp.baseUserList.clear();
            RongCloudApp.baseUserList.addAll(Arrays.asList(arr));
            model.addAll(Arrays.asList(arr));

            if(adapter == null){
                adapter = new MyRecyclerAdapter(model, UserListActivity.this);
                manager = new LinearLayoutManager(UserListActivity.this, LinearLayoutManager.VERTICAL, false);
                rvUserList.setLayoutManager(manager);
                rvUserList.addItemDecoration(new RecyclerDiver(UserListActivity.this, LinearLayoutManager.HORIZONTAL));
                rvUserList.setAdapter(adapter);
            } else{
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Exception e) {
            Log.d("fanhy", "onFailure----用户列表"+"异常:"+e.toString());
        }
    };
}
