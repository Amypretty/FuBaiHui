package com.cheng.fubaihui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by yingzi on 2019/10/23.
 */

public class ShopsBean {

    /**
     * code : 200
     * msg : 请求成功
     * data : [{"id":"170","name":"福百惠会员银积分兑换中心","logo":"/Uploads/Garage/pic/20190730/5d3ffa05dad8a.png","longitude":"115.992599","latitude":"39.661141"},{"id":"181","name":"北京晳之密工作室","logo":"/Uploads/Garage/pic/20190805/5d480b2265d35.jpg","longitude":"115.978132","latitude":"39.721904"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 170
         * name : 福百惠会员银积分兑换中心
         * logo : /Uploads/Garage/pic/20190730/5d3ffa05dad8a.png
         * longitude : 115.992599
         * latitude : 39.661141
         */

        private String id;
        private String name;
        private String logo;
        private String longitude;
        private String latitude;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.logo);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.logo = in.readString();
            this.longitude = in.readString();
            this.latitude = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
