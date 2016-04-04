package com.xwysun.account;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.barTitle)
    TextView barTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setToolbar();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,getFragment())
                .commit();

    }
    public void setToolbar(){
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.btn_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }
    abstract public Fragment getFragment();

}
