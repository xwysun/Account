package com.xwysun.account;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by xwysun on 2016/5/25.
 */
public class SellEditActivity extends BaseActivity {
    SellEditFragment fragment;
    @Override
    public Fragment getContainFragment() {
        fragment=SellEditFragment.newInstance(getIntent().getExtras());
        return fragment;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editdetail, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.commit:
                fragment.commitDetail();
                break;
        }
        return super.onOptionsItemSelected(item);
    }}
