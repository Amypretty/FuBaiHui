
package com.cheng.fubaihui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cheng.fubaihui.R;
import com.cheng.fubaihui.bean.NewsinformationInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final Context mContext;
    private final ArrayList<NewsinformationInfo.ListBean> mListBeans;
    private static final String TAG = "NewsAdapter";
    public NewsAdapter(Context context, ArrayList<NewsinformationInfo.ListBean> listBeans) {
        mContext = context;
        mListBeans = listBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.new_information_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mListBeans.get(position).getTitle());
        holder.mType.setText(mListBeans.get(position).getType());
        holder.mPresent.setText(mListBeans.get(position).getPresent());
        String picture = mListBeans.get(position).getPicture();
        String url="http://newwasj.zhangtongdongli.com"+picture;
        Log.i(TAG, "onBindViewHolder: "+url);
        Glide.with(mContext).load(url).into(holder.mPicture);
    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.type)
        TextView mType;
        @BindView(R.id.present)
        TextView mPresent;
        @BindView(R.id.picture)
        ImageView mPicture;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}


