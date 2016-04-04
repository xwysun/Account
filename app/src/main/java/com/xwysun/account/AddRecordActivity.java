package com.xwysun.account;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by xwysun on 2016/4/4.
 */
public class AddRecordActivity extends BaseActivity {
    @Override
    public Fragment getFragment() {
        super.barTitle.setText(R.string.addRecord);
        return new Fragment();
    }
//    @Override public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_message, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
