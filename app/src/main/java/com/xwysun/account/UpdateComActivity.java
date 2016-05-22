package com.xwysun.account;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by xwysun on 2016/5/21.
 */
public class UpdateComActivity extends BaseActivity {
    @Override
    public Fragment getContainFragment() {
        return UpdateComFragment.newInstance(getIntent().getExtras());
    }

    @Override
    public Fragment getRightFragment() {
        return null;
    }

    @Override
    public void setToolbarTitle(Toolbar toolbar, TextView centerTitle) {

    }

    @Override
    public void setDrawerLayout(DrawerLayout drawerLayout) {

    }
}
