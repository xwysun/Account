package com.xwysun.account;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.xwysun.account.Utils.Utils;

/**
 * Created by xwysun on 2016/4/4.
 */
public class RecordsActivity extends BaseActivity {

    @Override
    public Fragment getFragment() {
        super.barTitle.setText(R.string.recordsList);
        return new Fragment();
    }
        @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_records, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionChoose:
                Utils.toast(this,"筛选");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
