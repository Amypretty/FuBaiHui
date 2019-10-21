package com.cheng.fubaihui.frame;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 任小龙 on 2019/9/19.
 */
public interface INetService {
    ////http://wasj.zhangtongdongli.com/APP/Xone/goodslist?pageno=1
    @GET("APP/Xone/goodslist")
    Observable<Object> getPersonRankInfo(@Query("pageno") String page);
}
