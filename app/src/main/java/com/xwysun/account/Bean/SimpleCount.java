package com.xwysun.account.Bean;

/**
 * Created by xwysu on 2016/8/8.
 */
public class SimpleCount {
    private Commodity commodity;
    private double countSale;

    public SimpleCount(Commodity commodity, double countSale) {
        this.commodity = commodity;
        this.countSale = countSale;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public double getCountSale() {
        return countSale;
    }

    public void setCountSale(double countSale) {
        this.countSale = countSale;
    }
}
