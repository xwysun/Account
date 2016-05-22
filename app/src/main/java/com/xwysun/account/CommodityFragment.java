package com.xwysun.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.Bean.RequestBean;
import com.xwysun.account.Utils.CommodityNetworkUtils;
import com.xwysun.account.Utils.Utils;
import com.xwysun.account.adapter.CommodityRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.push.a.Hamlet;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xwysun on 2016/5/21.
 */
public class CommodityFragment extends Fragment {
    @Bind(R.id.commodityList)
    RecyclerView commodityList;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    public int Flag = CommodityNetworkUtils.DEFAULT_REQUEST;
    public RequestBean request=new RequestBean();
    public static final String TAG="COMFRAGMENT";
    @Bind(R.id.detailed_action_add)
    FloatingActionButton detailedActionAdd;
    @Bind(R.id.detailed_multiple_actions)
    FloatingActionsMenu detailedMultipleActions;
    ArrayList<Commodity> commodities=new ArrayList<>();
    CommodityRecyclerAdapter adapter;
    BmobQuery<Commodity> query;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    commodities= (ArrayList<Commodity>)msg.getData().getSerializable(CommodityNetworkUtils.DATA);
                    Log.d(TAG,"SIZE"+commodities.size());
                    adapter.notifyCommodity(commodities);
                    swipeRefresh.setRefreshing(false);
                    break;
                case 0:
                    Utils.toast(getActivity(),"请求失败");
            }
        }
    };

    public void initView() {
        adapter=new CommodityRecyclerAdapter(getActivity(),commodities);
        request.setUser(BmobUser.getCurrentUser(getActivity().getApplicationContext()));
        request.setContext(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commodityList.setLayoutManager(layoutManager);
        commodityList.setAdapter(adapter);
        CommodityNetworkUtils.getCommodities(request,mhandler);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /**
                 * 请求网络数据，调用的是一个工具类，注意筛选的问题
                 */
                CommodityNetworkUtils.getCommodities(request,mhandler);
            }
        });
        commodityList.setHasFixedSize(true);
        commodityList.setItemAnimator(new DefaultItemAnimator());
        detailedActionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UpdateComActivity.class);
                startActivity(intent);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_commodity, container, false);
        ButterKnife.bind(this, v);
        initView();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static CommodityFragment newInstance(Bundle args) {
        CommodityFragment fragment = new CommodityFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
