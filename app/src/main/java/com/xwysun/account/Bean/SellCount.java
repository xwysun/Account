package com.xwysun.account.Bean;

import android.os.Handler;

import java.util.HashMap;

import cn.bmob.v3.BmobUser;

/**
 * Created by xwysu on 2016/8/8.
 */
public class SellCount {
    private BmobUser user;
    private HashMap<Commodity,Double> sellMap=new HashMap<>();
    private double countMoney;
    public SellCount(){

    }
    public SellCount(BmobUser bmobUser){
        this.user=bmobUser;
    }
    public double getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(double countMoney) {
        this.countMoney = countMoney;
    }

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public HashMap<Commodity, Double> getSellMap() {
        return sellMap;
    }

    public void setSellMap(HashMap<Commodity, Double> sellMap) {
        this.sellMap = sellMap;
    }
}
