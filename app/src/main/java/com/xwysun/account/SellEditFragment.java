package com.xwysun.account;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.Bean.RequestBean;
import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.Bean.SellRecords;
import com.xwysun.account.OpenSrc.DividerItemDecoration;
import com.xwysun.account.Utils.CommodityNetworkUtils;
import com.xwysun.account.Utils.Utils;
import com.xwysun.account.adapter.SellEditAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by xwysun on 2016/5/25.
 */
public class SellEditFragment extends Fragment {
    SellEditAdapter adapter;
    BmobUser user;
    SellRecords record = new SellRecords();
    int updateNum=0;
    ArrayList<SellBean> sellBeen = new ArrayList<>();
    ArrayList<Commodity> commodities = new ArrayList<>();
    public RequestBean request = new RequestBean();
    public static final String TAG = "editSellFrg";
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    commodities = (ArrayList<Commodity>) msg.getData().getSerializable(CommodityNetworkUtils.DATA);
//                    Log.d(TAG,"SIZE"+commodities.size());
                    for (Commodity c : commodities) {
                        sellBeen.add(new SellBean(c));
                    }
                    adapter.notifySellbeen(sellBeen);
                    break;
                case 0:
                    Utils.toast(getActivity(), "请求失败");
            }
        }
    };
    @Bind(R.id.detail_edit_sum)
    EditText detailEditSum;
    @Bind(R.id.detail_edit_sum_expect)
    TextView detailEditSumExpect;
    @Bind(R.id.detail_list_edit)
    RecyclerView detailListEdit;

    public static SellEditFragment newInstance(Bundle args) {
        SellEditFragment fragment = new SellEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sell_editdetail, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = BmobUser.getCurrentUser(getActivity().getApplicationContext());
        record.setUserName(user.getUsername());
        record.setUser(user);
        record.setSellBeen(sellBeen);
        request.setUser(user);
        request.setContext(getActivity());
        adapter = new SellEditAdapter(getActivity(), sellBeen);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        detailListEdit.setLayoutManager(layoutManager);
        detailListEdit.setAdapter(adapter);
        detailListEdit.setHasFixedSize(true);
        detailListEdit.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        CommodityNetworkUtils.getCommodities(request, mhandler);
        detailEditSum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    detailEditSum.setText("");
                } else if (detailEditSum != null) {
                    if (!detailEditSum.getText().toString().trim().equals("")) {
                        record.setSaleSum(Integer.parseInt(detailEditSum.getText().toString().trim()));
                        Log.d(TAG, detailEditSum.getText().toString());
                    } else {
                        record.setSaleSum(0);
                    }
                }
            }
        });
        adapter.setOnFocusLostListener(new SellEditAdapter.OnFocusLostListener() {
            @Override
            public void Onchange() {
                detailEditSumExpect.setText(String.format("%.2f", mathSum()));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public double mathSum() {
        double sum = 0;
        for (SellBean s : sellBeen
                ) {
            sum += s.getSaleMoney();
        }
        return sum;
    }

    public void commitDetail() {
        Log.d(TAG, record.toString());
        record.setSaleSum_expect(mathSum());
        if (detailEditSum != null) {
            if (!detailEditSum.getText().toString().trim().equals("")) {
                record.setSaleSum(Double.parseDouble(detailEditSum.getText().toString().trim()));
                Log.d(TAG, detailEditSum.getText().toString());
            } else {
                record.setSaleSum(0);
            }
        }
        if (updateCommodity()){
            record.save(getActivity(), new SaveListener() {
                @Override
                public void onSuccess() {
                    Utils.toast(getActivity(), "成功");
                    getActivity().finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    Utils.toast(getActivity(), s);
                }
            });
        }
    }
    public boolean updateCommodity(){
        updateNum=0;
        ArrayList<SellBean> updatedData=record.getSellBeen();
        ArrayList<Commodity> commodities=new ArrayList<>();
        for (int i=0;i<updatedData.size();i++){
            Commodity commodity=updatedData.get(i).getCommodity();
            double stock=commodity.getStock()-updatedData.get(i).getSales();
            if (stock<0){
                Utils.toast(getActivity(),commodity.getName()+"库存不足");
                return false;
            }
            commodity.setStock(stock);
            commodities.add(commodity);
        }
        for (int i=0;i<commodities.size();i++){
            commodities.get(i).update(getActivity(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    updateNum++;
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.e("update",s);
                    Utils.toast(getActivity(),"数据上传发生意外，请检查服务器相关数据");
                }
            });
        }
//        if (updateNum!=commodities.size()-1){
//            Utils.toast(getActivity(),"数据上传发生意外，请检查服务器相关数据");
//            return false;
//        }
        return true;
    }


}
