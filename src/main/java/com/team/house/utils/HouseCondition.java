package com.team.house.utils;

public class HouseCondition extends Pageutils {
    private String title;
    private Integer did;
    private Integer sid;
    private Integer tid;
    private Integer min_price;
    private Integer max_price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getMin_price() {
        return min_price;
    }

    public void setMin_price(Integer min_price) {
        this.min_price = min_price;
    }

    public Integer getMax_price() {
        return max_price;
    }

    public void setMax_price(Integer max_price) {
        this.max_price = max_price;
    }
}
