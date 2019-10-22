package com.cheng.fubaihui.frame;

import com.cheng.fubaihui.bean.NewsinformationInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by 任小龙 on 2019/9/19.
 */
public interface INetService {
    ////http://wasj.zhangtongdongli.com/APP/Xone/goodslist?pageno=1
    @GET("APP/Xone/goodslist")
    Observable<Object> getPersonRankInfo(@Query("pageno") String page);

    //新闻资讯接口
    @POST("APP/Love/information")
    Observable<NewsinformationInfo> getnewsinformation();
}
