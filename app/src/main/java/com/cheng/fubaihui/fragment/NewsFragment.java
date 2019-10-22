
package com.cheng.fubaihui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cheng.fubaihui.R;
import com.cheng.fubaihui.adapter.NewsAdapter;
import com.cheng.fubaihui.bean.NewsinformationInfo;
import com.cheng.fubaihui.frame.ApiConfig;
import com.cheng.fubaihui.frame.BaseFragment;
import com.cheng.fubaihui.frame.ICommonModel;
import com.cheng.fubaihui.model.TestModel;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment {
    static NewsFragment fragment;
    private RecyclerView mRec;
    private SwipeRefreshLayout mRefresh;
    private ArrayList<NewsinformationInfo.ListBean> mListBeans = new ArrayList<>();
    ;
    private NewsAdapter mNewsAdapter;

    public static NewsFragment newInstance() {
        if (fragment == null) fragment = new NewsFragment();
        return fragment;
    }


    @Override
    protected void initView(View view) {
        mRec = (RecyclerView) view.findViewById(R.id.rec);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mRec.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mNewsAdapter = new NewsAdapter(getContext(), mListBeans);
        mRec.setAdapter(mNewsAdapter);

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.setRefreshing(true);//刷新控件进行刷新
            }
        });
    }

    @Override
    protected void initdata() {
        //新闻资讯
    }


    private void getData(int loadMode) {
        //新闻资讯
        mMainPresenter.getData(ApiConfig.POST_NEWS_INFORMATION_TEST, loadMode);
    }

    @Override
    protected ICommonModel setModle() {
        return new TestModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onSuccess(int whichApi, Object successResult) {
        switch (whichApi) {
            case ApiConfig.POST_NEWS_INFORMATION_TEST:
                NewsinformationInfo newsinformationInfo = (NewsinformationInfo) successResult;
                List<NewsinformationInfo.ListBean> infoList = newsinformationInfo.getList();
                if (newsinformationInfo.getList() != null && newsinformationInfo.getList().size() > 0)
                    mListBeans.addAll(infoList);
                mNewsAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onFailed(int whichApi, Throwable failedResult) {

    }
}
