package com.xwysun.account.Bean;

import java.io.Serializable;
import java.util.ArrayList;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by xwysun on 2016/5/23.
 */
public class SellRecords extends BmobObject implements Serializable{
    private BmobUser user;
    private ArrayList<SellBean> sellBeen;
    private double saleSum;
    private double saleSum_expect;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getSaleSum_expect() {
        return saleSum_expect;
    }

    public void setSaleSum_expect(double saleSum_expect) {
        this.saleSum_expect = saleSum_expect;
    }

    @Override
    public String toString() {
        return "SellRecords{" +
                "user=" + user +
                ", sellBeen=" + sellBeen +
                ", saleSum=" + saleSum +
                ", saleSum_expect=" + saleSum_expect +
                ", userName='" + userName + '\'' +
                '}';
    }

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public ArrayList<SellBean> getSellBeen() {
        return sellBeen;
    }
    public void setSellBeen(ArrayList<SellBean> sellBeen) {
        this.sellBeen = sellBeen;
    }

    public double getSaleSum() {
        return saleSum;
    }

    public void setSaleSum(double saleSum) {
        this.saleSum = saleSum;
    }
}
