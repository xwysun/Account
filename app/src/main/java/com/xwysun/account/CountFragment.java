package com.xwysun.account;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.squareup.okhttp.internal.Util;
import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.Bean.RequestBean;
import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.Bean.SellCount;
import com.xwysun.account.Bean.SellRecords;
import com.xwysun.account.Bean.SimpleCount;
import com.xwysun.account.OpenSrc.DividerItemDecoration;
import com.xwysun.account.OpenSrc.SaveExcelHelper;
import com.xwysun.account.Utils.CommodityNetworkUtils;
import com.xwysun.account.Utils.Utils;
import com.xwysun.account.adapter.AcountListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.PushListener;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xwysun on 2016/5/23.
 */
public class CountFragment extends Fragment {

    @Bind(R.id.sale_sum)
    TextView saleSum;
    @Bind(R.id.count_list)
    RecyclerView countList;
    @Bind(R.id.sell_action_down)
    FloatingActionButton sellActionDown;
    @Bind(R.id.sell_actions)
    FloatingActionsMenu sellActions;


    public AcountListAdapter adapter;
    public ArrayList<SimpleCount> simpleCounts = new ArrayList<>();
    public ArrayList<SellRecords> recordses=new ArrayList<>();
    public ArrayList<SellCount> sellCounts=new ArrayList<>();
//    public ArrayList<SellCount> dataCount=new  ArrayList<>();
    public HashMap<Commodity,Double> simpleMap=new HashMap<>();
    public RequestBean requestBean=new RequestBean();
    public double sum=0;
    Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    recordses= (ArrayList<SellRecords>)msg.getData().getSerializable(CommodityNetworkUtils.DATA);
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            DataCensus(recordses);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyCounts(simpleCounts);
                                    for (SimpleCount s:simpleCounts){
                                        sum+=s.getCountSale()*s.getCommodity().getPrice();
                                    }
                                    saleSum.setText(String.format("%.2f", sum));
                                }
                            });
                        }
                    }.start();
                    break;
                case 0:
                    Utils.toast(getActivity(),"请求失败");
            }
        }
    };

    public static CountFragment newInstance(Bundle args) {
        CountFragment fragment = new CountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestBean.setContext(getActivity());
        requestBean.setDate(new Date(Utils.getTimeOfMonthStart()));
        requestBean.setLastMonth(new Date(Utils.getTimeOfLastMonthStart()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_count_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new AcountListAdapter(getActivity(), simpleCounts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        countList.setLayoutManager(layoutManager);
        countList.setAdapter(adapter);
        countList.setHasFixedSize(true);
        countList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        CommodityNetworkUtils.getAllSells(requestBean,mhandler);
        sellActionDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownLoadExcel();
            }
        });
    }
    public void DownLoadExcel(){
        SaveExcelHelper helper=new SaveExcelHelper(getActivity());
        helper.setDataSrc(simpleCounts,sellCounts,sum);
        helper.CreateExcel();
    }
    public void DataCensus(ArrayList<SellRecords> sellRecordses){
        Log.d("DataCensus","DataCensus start");
        SellRecords sellRecords;
        SellBean sellBean;
        SellCount sellCount;
        int containFlag=-1;
        double mathResult;
        for (int i=0;i<sellRecordses.size();i++){
            sellRecords=sellRecordses.get(i);
            if ((containFlag=ListContainsUser(sellCounts,sellRecords.getUser()))!=-1){
                sellCount=sellCounts.get(containFlag);
            }else {
                sellCount=new SellCount(sellRecords.getUser());
            }
            for (int j=0;j<sellRecords.getSellBeen().size();j++){
                sellBean=sellRecords.getSellBeen().get(j);
                if (sellCount.getSellMap().containsKey(sellBean.getCommodity())){
                    mathResult=sellCount.getSellMap().get(sellBean.getCommodity())+sellBean.getSales();
                    sellCount.getSellMap().remove(sellBean.getCommodity());
                    sellCount.getSellMap().put(sellBean.getCommodity(),
                            mathResult);
                }else {
                    sellCount.getSellMap().put(sellBean.getCommodity(),sellBean.getSales());
                }
                if (simpleMap.containsKey(sellBean.getCommodity())){
                    Log.d("simpleMap","repeat");
                    mathResult=simpleMap.get(sellBean.getCommodity())+sellBean.getSales();
                    simpleMap.remove(sellBean.getCommodity());
                    simpleMap.put(sellBean.getCommodity(),
                            mathResult);
                }else {
                    simpleMap.put(sellBean.getCommodity(),sellBean.getSales());
                }
            }
            if (containFlag!=-1){
                sellCounts.set(containFlag,sellCount);
            }else {
                sellCounts.add(sellCount);
            }
        }
        Log.d("sellCount",""+sellCounts.size());
        for (Commodity commodity:simpleMap.keySet()){
            simpleCounts.add(new SimpleCount(commodity,simpleMap.get(commodity)));
        }
    }
    public int  ListContainsUser(ArrayList<SellCount> sellCounts,BmobUser user){
        for (int i=0;i<sellCounts.size();i++){
            if (sellCounts.get(i).getUser().getObjectId().equals(user.getObjectId())){
                return i;
            }
        }
        return -1;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
