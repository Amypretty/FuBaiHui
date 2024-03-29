package com.cheng.fubaihui.frame;

import com.cheng.fubaihui.bean.LocationBean;
import com.cheng.fubaihui.bean.LoginBean;
import com.cheng.fubaihui.bean.MallnameInfo;
import com.cheng.fubaihui.bean.NewsinformationInfo;
import com.cheng.fubaihui.bean.NoteCode_Bean;
import com.cheng.fubaihui.bean.RePwdBean;
import com.cheng.fubaihui.bean.RegisterBean;
import com.cheng.fubaihui.bean.ShopDetailsBean;
import com.cheng.fubaihui.bean.ShopInfo;
import com.cheng.fubaihui.bean.ShopsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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


    @POST("APP/Public/sendsms")
    @FormUrlEncoded
    Observable<NoteCode_Bean> phone_code(@Field("phone") String phone);


    @POST("APP/user/register")
    @FormUrlEncoded
    Observable<RegisterBean> register(@Field("username") String username,
                                      @Field("password") String password,
                                      @Field("recommend_code") Number recommend_code,
                                      @Field("agree") Number agree,
                                      @Field("yzm") Number yzm);



    // http://wasj.com/
    @POST("APP/user/login")  //登陆
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("username") String username,
                                @Field("password") String password);

    @POST("APP/User/forget")
    @FormUrlEncoded
    Observable<RePwdBean> rePwd(@Field("phone") String phone,
                                @Field("password")String password, @Field("yzm")String yzm);


    //http://newwasj.zhangtongdongli.com/APP/public/category_list
    @GET("APP/public/category_list")//福百惠商城,侧面垂直的tab
    Observable<MallnameInfo> mallName();

    //http://newwasj.zhangtongdongli.com/
    @GET("APP/Public/bander/name/shop_top")//福百惠商城，banner
    Observable<BannerInfo> bannerList();

    @GET("APP/Shop/index/cid/{id}")//福百惠商城，商品列表
    Observable<ShopInfo> shopList(@Path("id") String id);

    @GET("APP/Love/informationDetail/{id}")//新闻资讯的详情页
    Observable<ShopInfo> getnewsinfodetails(@Query("id")String id);



    @POST("APP/Xtojoin/garage_list")
    @FormUrlEncoded
    Observable<ShopsBean> getShopList(@Field("type") int type);

    @POST("APP/Xinv/sjtojoininfo")
    Observable<LocationBean> getLocation();

    @POST("APP/Xtojoin/mer_details")
    @FormUrlEncoded
    Observable<ShopDetailsBean> getShopDetails(@Field("id") String id);
}
