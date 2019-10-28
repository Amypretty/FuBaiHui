package com.cheng.fubaihui.bean;

/**
 * Created by yingzi on 2019/10/25.
 */

public class ShopDetailsBean {

    /**
     * code : 200
     * msg : 请求成功
     * data : {"id":"170","name":"福百惠会员银积分兑换中心","contacts":"邹先生","phone":"13466408873","longitude":"115.992599","latitude":"39.661141","logo":"/Uploads/Garage/pic/20190730/5d3ffa05dad8a.png","content":"<p class=\"MsoNormal\" style=\"text-indent:21.0000pt;\">\r\n\t<span>福百惠<\/span>APP是由我爱司机（北京）信息技术有限公司创办，是为司机朋友提供便捷汽车养护并帮助汽修厂解决网上开店、锁客拓客一款综合性服务平台软件，平台设有福百惠商城板块、银积分商城板块、加盟商家板块几大板块。福百惠构建消费者全球智慧生活场景，实现消费全球结算、消费贵宾折扣等同现金使用，消费体验更便利、更便宜、更安全、更愉悦。\r\n<\/p>","province":"北京","city":"北京","district":"房山区","address_detail":"石楼镇府前街17号","juli":10297.35}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 170
         * name : 福百惠会员银积分兑换中心
         * contacts : 邹先生
         * phone : 13466408873
         * longitude : 115.992599
         * latitude : 39.661141
         * logo : /Uploads/Garage/pic/20190730/5d3ffa05dad8a.png
         * content : <p class="MsoNormal" style="text-indent:21.0000pt;">
         <span>福百惠</span>APP是由我爱司机（北京）信息技术有限公司创办，是为司机朋友提供便捷汽车养护并帮助汽修厂解决网上开店、锁客拓客一款综合性服务平台软件，平台设有福百惠商城板块、银积分商城板块、加盟商家板块几大板块。福百惠构建消费者全球智慧生活场景，实现消费全球结算、消费贵宾折扣等同现金使用，消费体验更便利、更便宜、更安全、更愉悦。
         </p>
         * province : 北京
         * city : 北京
         * district : 房山区
         * address_detail : 石楼镇府前街17号
         * juli : 10297.35
         */

        private String id;
        private String name;
        private String contacts;
        private String phone;
        private String longitude;
        private String latitude;
        private String logo;
        private String content;
        private String province;
        private String city;
        private String district;
        private String address_detail;
        private double juli;

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

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public double getJuli() {
            return juli;
        }

        public void setJuli(double juli) {
            this.juli = juli;
        }
    }
}
