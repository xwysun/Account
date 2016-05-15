package com.xwysun.account;

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xwysun on 2016/4/4.
 */
public class HistoryActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public Fragment getContainFragment() {
        return new Fragment();
    }

    @Override
    public Fragment getRightFragment() {
        return new ChooseFragment();
    }

    @Override
    public void setToolbarTitle(Toolbar toolbar, TextView centerTitle) {
        centerTitle.setText(R.string.recordHistory);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionAdd:
                drawerLayout.openDrawer(Gravity.RIGHT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,Gravity.END);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *设置drawerLayout，使其仅能够被筛选按钮触发
     * 上锁->解锁->开启
     * @param drawerLayout
     */
    @Override
    public void setDrawerLayout(final DrawerLayout drawerLayout) {
        mDrawerLayout=drawerLayout;
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.END);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }
}
