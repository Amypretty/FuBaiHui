package com.cheng.fubaihui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheng.fubaihui.R;
import com.cheng.fubaihui.bean.LocationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yingzi on 2019/10/24.
 */

public class ShopTypeRclAdapter extends RecyclerView.Adapter<ShopTypeRclAdapter.ViewHolder>{
    private List<LocationBean.DataBean.DpflBean> mList;
    private Context mContext;
    private final LayoutInflater mInflater;

    public ShopTypeRclAdapter(List<LocationBean.DataBean.DpflBean> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.shop_type_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("1112111111",mList.get(position).getName());
        holder.mShopType.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_type)
        TextView mShopType;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
