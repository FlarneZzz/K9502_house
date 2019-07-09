package com.team.house.utils;

public class SearchParam {
    private Double min_price;
    private Double max_price;
    private String sname;
    private String dname;
    private String tname;
    private String title;
    /*private Integer ispass;*/

    public Double getMin_price() {
        return min_price;
    }

    public void setMin_price(Double min_price) {
        this.min_price = min_price;
    }

    public Double getMax_price() {
        return max_price;
    }

    public void setMax_price(Double max_price) {
        this.max_price = max_price;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   /* public Integer getIspass() {
        return ispass;
    }

    public void setIspass(Integer ispass) {
        this.ispass = ispass;
    }*/
}
