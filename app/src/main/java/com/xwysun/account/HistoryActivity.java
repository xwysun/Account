package com.xwysun.account;

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by xwysun on 2016/4/4.
 */
public class HistoryActivity extends BaseActivity {
    @Override
    public Fragment getFragment() {
        super.barTitle.setText(R.string.recordHistory);
        return new Fragment();
    }
        @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionAdd:
                Intent intent=new Intent(HistoryActivity.this,AddRecordActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
