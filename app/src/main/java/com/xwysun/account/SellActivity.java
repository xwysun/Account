package com.xwysun.account;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.xwysun.account.Utils.Utils;

/**
 * Created by xwysun on 2016/4/4.
 */
public class SellActivity extends BaseActivity {

        @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_records, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public Fragment getContainFragment() {
        return new Fragment();
    }

    @Override
    public Fragment getRightFragment() {
        return null;
    }

    @Override
    public void setToolbarTitle(Toolbar toolbar, TextView centerTitle) {
            centerTitle.setText(R.string.sell_list);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionChoose:
                Utils.toast(this,"筛选");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDrawerLayout(DrawerLayout drawerLayout) {

    }
}
