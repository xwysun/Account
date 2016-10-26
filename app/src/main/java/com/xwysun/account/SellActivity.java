package com.xwysun.account;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xwysun.account.Utils.Utils;

/**
 * Created by xwysun on 2016/4/4.
 */
public class SellActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private SellFragment sellFragment;
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_records, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public Fragment getContainFragment() {
        sellFragment=SellFragment.newInstance(getIntent().getExtras());
        return sellFragment;
    }

    @Override
    public Fragment getRightFragment() {
        return ChooseFragment.newInstance(this,getIntent().getExtras(),sellFragment,drawerLayout);
    }

    @Override
    public void setToolbarTitle(Toolbar toolbar, TextView centerTitle) {
            centerTitle.setText(R.string.sell_list);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionChoose:
                drawerLayout.openDrawer(Gravity.RIGHT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,Gravity.END);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

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
