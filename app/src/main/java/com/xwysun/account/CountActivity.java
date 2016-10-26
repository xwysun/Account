package com.xwysun.account;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by xwysun on 2016/5/23.
 */
public class CountActivity extends BaseActivity {
    @Override
    public Fragment getContainFragment() {
        return CountFragment.newInstance(getIntent().getExtras());
    }

    @Override
    public Fragment getRightFragment() {
        return null;
    }

    @Override
    public void setToolbarTitle(Toolbar toolbar, TextView centerTitle) {
            centerTitle.setText("上月销售总表");
    }

    @Override
    public void setDrawerLayout(DrawerLayout drawerLayout) {

    }
}
