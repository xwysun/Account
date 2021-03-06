package com.xwysun.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.Bean.SellRecords;
import com.xwysun.account.OpenSrc.EndlessRecyclerOnScrollListener;
import com.xwysun.account.Utils.CommodityNetworkUtils;
import com.xwysun.account.Utils.Utils;
import com.xwysun.account.adapter.SellListAdapter;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

/**
 * Created by xwysun on 2016/5/22.
 */
public class SellFragment extends Fragment {

    SellListAdapter adapter;
    ArrayList<SellRecords> sellRecordses = new ArrayList<>();
    @Bind(R.id.sell_list)
    RecyclerView sellList;
    @Bind(R.id.sell_action_add)
    FloatingActionButton sellActionAdd;
    @Bind(R.id.sell_actions)
    FloatingActionsMenu sellActions;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    public RequestBean request=new RequestBean();
    public  static final String TAG="SellFragment";
    public static final String PUTEXTRA_NAME="records";
    Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    sellRecordses= (ArrayList<SellRecords>)msg.getData().getSerializable(CommodityNetworkUtils.DATA);
                    Log.d(TAG,"size"+sellRecordses.size());
                    adapter.notifySell(sellRecordses);
                    swipeRefresh.setRefreshing(false);
                    break;
                case 0:
                    Utils.toast(getActivity(),"请求失败");
            }
        }
    };
    Handler loadMorehandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    sellRecordses.addAll((ArrayList<SellRecords>)msg.getData().getSerializable(CommodityNetworkUtils.DATA));
                    Log.d(TAG,"size"+sellRecordses.size());
                    adapter.notifySell(sellRecordses);
                    break;
                case 0:
                    Utils.toast(getActivity(),"请求失败");
            }
        }
    };

    public static SellFragment newInstance(Bundle args) {
        SellFragment fragment = new SellFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sell, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BmobUser user=BmobUser.getCurrentUser(getActivity().getApplicationContext());
        Log.d(TAG,user.getUsername());
        request.setUser(user);
        request.setContext(getActivity());
        request.setSkipNum(0);
        adapter = new SellListAdapter(getActivity(), sellRecordses);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sellList.setLayoutManager(layoutManager);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request.setSkipNum(0);
                CommodityNetworkUtils.getSellRecords(request,mhandler);
            }
        });
        sellList.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                request.setSkipNum(sellRecordses.size());
                CommodityNetworkUtils.getSellRecords(request,loadMorehandler);
            }
        });
        adapter.setOnItemClickListener(new SellListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),SellDetailActivity.class);
                intent.putExtra(PUTEXTRA_NAME,sellRecordses.get(position));
                startActivity(intent);
            }
        });
        sellList.setAdapter(adapter);
        Log.i(TAG,"onCreate"+request.toString());
        CommodityNetworkUtils.getSellRecords(request,mhandler);
        sellActionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SellEditActivity.class);
                startActivity(intent);
            }
        });

    }
    public void setRequestChoose(long date,BmobUser user){
        if (date!=0){
            request.setDate(new Date(date));
        }else {
            request.setDate(null);
        }
        if (user!=null){
            request.setUser(user);
        }
        request.setSkipNum(0);
        CommodityNetworkUtils.getSellRecords(request,mhandler);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume"+request.toString());
        request.setSkipNum(0);
        CommodityNetworkUtils.getSellRecords(request,mhandler);
    }
}
