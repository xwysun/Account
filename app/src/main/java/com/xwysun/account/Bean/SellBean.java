package com.xwysun.account.Bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by xwysun on 2016/5/22.
 */
public class SellBean extends BmobObject implements Serializable{
    private Commodity commodity;
    private BmobUser user;
    private double sales;
    private double saleMoney;
    public SellBean(Commodity commodity,BmobUser user,double sales,double saleMoney){
        this.commodity=commodity;
        this.user=user;
        this.sales=sales;
        this.saleMoney=saleMoney;
    }
    public SellBean(){

    }
    public SellBean(Commodity c){
        this.commodity=c;
    }


    public double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(double saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }
}
