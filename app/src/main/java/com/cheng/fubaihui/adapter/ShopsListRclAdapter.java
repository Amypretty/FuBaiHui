package com.cheng.fubaihui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cheng.fubaihui.R;
import com.cheng.fubaihui.ShopDetailsActivity;
import com.cheng.fubaihui.bean.ShopsBean;
import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.Config;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yingzi on 2019/10/23.
 */

public class ShopsListRclAdapter extends RecyclerView.Adapter<ShopsListRclAdapter.ViewHolder> {
    private List<ShopsBean.DataBean> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public ShopsListRclAdapter(List<ShopsBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.shops_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mNameShop.setText(mList.get(position).getName());
        holder.mDistance.setText(mList.get(position).getLatitude());
        RoundedCorners corners = new RoundedCorners(10);
        RequestOptions placeholder = new RequestOptions().placeholder(R.drawable.fbh);
        RequestOptions transform = placeholder.transform(corners);
        Glide.with(mContext)
                .load(Config.BASEURL+mList.get(position).getLogo())
                .apply(transform)
                .into(holder.mImgShop);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopDetailsActivity.startShopDetailsActivity(mContext,mList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_shop)
        ImageView mImgShop;
        @BindView(R.id.name_shop)
        TextView mNameShop;
        @BindView(R.id.distance)
        TextView mDistance;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
