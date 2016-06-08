package com.xwysun.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.Bean.SellRecords;
import com.xwysun.account.OpenSrc.DividerItemDecoration;
import com.xwysun.account.adapter.SellDetailAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xwysun on 2016/5/23.
 */
public class SellDetailFragment extends Fragment {
    @Bind(R.id.detail_list)
    RecyclerView detailList;
    ArrayList<SellBean> sellBeen=new ArrayList<>();
    SellDetailAdapter adapter;
    SellRecords records;

    public static SellDetailFragment newInstance(Bundle args) {
        SellDetailFragment fragment = new SellDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        records= (SellRecords) getArguments().getSerializable(SellFragment.PUTEXTRA_NAME);
        sellBeen=records.getSellBeen();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_selldetail, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new SellDetailAdapter(getActivity(),sellBeen);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);;
        detailList.setLayoutManager(layoutManager);
        detailList.setAdapter(adapter);
        detailList.setHasFixedSize(true);
        detailList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
