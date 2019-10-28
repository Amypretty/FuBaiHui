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
import com.cheng.fubaihui.R;
import com.cheng.fubaihui.StoreActivity;
import com.cheng.fubaihui.bean.ShopInfo;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private final Context mContext;
    private final List<ShopInfo.DataBean> mShop;

    public ShopAdapter(Context context, List<ShopInfo.DataBean> shop) {
        mContext = context;
        mShop = shop;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_list_item_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(mShop.get(position).getTitle());
        holder.mPricesc.setText(mShop.get(position).getPrice_sc());
        holder.mPeople.setText(mShop.get(position).getPeople()+"人付款");
        String cover = mShop.get(position).getCover();
        Glide.with(mContext).load("http://newwasj.zhangtongdongli.com"+ cover).into(holder.mCover);
    }

    @Override
    public int getItemCount() {
        return mShop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mCover;
        TextView mTitle;
        TextView mPricesc;
        TextView mPeople;
        public ViewHolder(View itemView) {
            super(itemView);
            mCover = itemView.findViewById(R.id.cover);
            mTitle = itemView.findViewById(R.id.title);
            mPricesc = itemView.findViewById(R.id.price_sc);
            mPeople = itemView.findViewById(R.id.people);
        }
    }
}
