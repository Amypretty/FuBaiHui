package com.cheng.fubaihui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheng.fubaihui.R;
import com.cheng.fubaihui.bean.MallnameInfo;

import java.util.List;

public class MallnameAdapter extends RecyclerView.Adapter<MallnameAdapter.ViewHolder> {
    private final Context mContext;
    private final List<MallnameInfo.DataBean> mList;

    public MallnameAdapter(Context context, List<MallnameInfo.DataBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mall_name_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTvname.setText(mList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickItemClick!=null){
                    mOnClickItemClick.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvname;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvname = itemView.findViewById(R.id.tv_name);
        }
    }
    private OnClickItemClick mOnClickItemClick;

    public void setOnClickItemClick(OnClickItemClick onClickItemClick) {
        mOnClickItemClick = onClickItemClick;
    }

    public interface OnClickItemClick{
        void OnItemClick(int position);
    }
}
