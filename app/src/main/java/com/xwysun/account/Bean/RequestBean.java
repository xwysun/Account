package com.xwysun.account.Bean;

import android.content.Context;

import cn.bmob.v3.BmobUser;

/**
 * Created by xwysun on 2016/5/21.
 */
public class RequestBean {
    /**
     * 用于所有请求的类，需要传递的参数都可以放进来，供所有请求调用
     */
    private BmobUser user;
    private Context context;

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
