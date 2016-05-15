package com.xwysun.account;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Bind(R.id.layout_toolbar)
    Toolbar layoutToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.base_container)
    FrameLayout baseContainer;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.right_container)
    FrameLayout rightContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setBaseToolbar(layoutToolbar);
        setSupportActionBar(layoutToolbar);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.START);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.END);
        setDrawerLayout(drawerLayout);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base_container, getContainFragment())
                .commit();
        Fragment rightFragment=getRightFragment();
        if (rightFragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.right_container, rightFragment)
                    .commit();
        }
    }

    /**
     * 抽象方法：
     * 1、获得Fragment
     * 2、设置ToolBar的title：
     * 二选一：使用ToolBar默认的位置（靠左）或者使用TextView（居中）
     */
    abstract public Fragment getContainFragment();
    abstract public Fragment getRightFragment();

    abstract public void setToolbarTitle(Toolbar toolbar, TextView centerTitle);


    /**
     * 设置toolbar
     *
     * @param toolbar
     */
    private void setBaseToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        setToolbarTitle(toolbar, toolbarTitle);
    }

    /**
     * 监听左上角的返回按钮
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    abstract public void setDrawerLayout(DrawerLayout drawerLayout);
}
