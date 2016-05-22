package com.xwysun.account.Bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by xwysun on 2016/5/21.
 */
public class Commodity extends BmobObject implements Serializable{
    private String name;
    private double price;
    private double oldStock;
    private double stock;
    private BmobUser owner;
    public Commodity(){

    }
    public Commodity(String name,double price,double oldStock,BmobUser user){
        this.name=name;
        this.price=price;
        this.oldStock=oldStock;
        this.owner=user;
    }
    public Commodity(String name,double price,double oldStock,double stock,BmobUser user){
        this.name=name;
        this.price=price;
        this.oldStock=oldStock;
        this.owner=user;
        this.stock=stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldStock() {
        return oldStock;
    }

    public void setOldStock(double oldStock) {
        this.oldStock = oldStock;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public BmobUser getOwner() {
        return owner;
    }

    public void setOwner(BmobUser owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return name+" "+price+" "+oldStock+" "+stock+" ";
    }
}
