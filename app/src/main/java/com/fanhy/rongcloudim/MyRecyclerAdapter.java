package com.fanhy.rongcloudim;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2016/10/9 0009.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<BaseUser> model;
    private LayoutInflater mInflater;
    private Context context;

    public MyRecyclerAdapter(List<BaseUser> model, Context context) {
        this.model = model;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_layout, null);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String name = model.get(position).getName();
        String path = "asset:///header.jpg";
        //holder.iv_item.setImageURI(Uri.parse(path));
        holder.tv_user_name.setText(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击:"+position, Toast.LENGTH_SHORT).show();
                BaseUser user = model.get(position);
                RongIM.getInstance().startPrivateChat(context, user.getUserId(), "与"+user.getName()+"交谈中...");
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_item;
        TextView tv_user_name;
        View itemView;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            iv_item = (ImageView) itemView.findViewById(R.id.iv_item);
            tv_user_name = (TextView) itemView.findViewById(R.id.tv_user_name);

        }
    }
}
