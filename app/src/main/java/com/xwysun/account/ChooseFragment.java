package com.xwysun.account;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.Bean.RequestBean;
import com.xwysun.account.Utils.CommodityNetworkUtils;
import com.xwysun.account.Utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

/**
 * Created by xwysun on 2016/5/15.
 */
public class ChooseFragment extends Fragment {

    //    @Bind(R.id.chooseName)
//    Spinner chooseName;
    @Bind(R.id.chooseTime)
    Spinner chooseTime;
    @Bind(R.id.chooseUser)
    Spinner chooseUser;
    @Bind(R.id.confirm)
    Button confirm;
    @Bind(R.id.userLayout)
    LinearLayout userLayout;
    ArrayList<String> nameStrings = new ArrayList<>();
    ArrayList<String> timeStrings = new ArrayList<>();
    ArrayList<String> userStrings = new ArrayList<>();
    ArrayList<BmobUser> bmobUsers = new ArrayList<>();
    ArrayList<Commodity> commodities = new ArrayList<>();
    ArrayAdapter userAdapter;
    BmobUser targetUser;
    Commodity targetCommodity;
    DrawerLayout drawerLayout;
    long targetDate;
    SellFragment sellFragment;
    public Context context;
    RequestBean requestBean = new RequestBean();
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    bmobUsers = (ArrayList<BmobUser>) msg.getData().getSerializable(CommodityNetworkUtils.DATA);
                    for (int i = 0; i < bmobUsers.size(); i++) {
                        userStrings.add(bmobUsers.get(i).getUsername());
                    }
                    Log.d("User-size:", "" + bmobUsers.get(0).getUsername());
                    userAdapter.notifyDataSetChanged();
                    break;
                case 0:
                    Utils.toast(getActivity(), "请求失败");
            }
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTimeStrings();
        if (BmobUser.getCurrentUser(getActivity().getApplicationContext()).getUsername().equals("admin")) {
            initUsers();
        }
    }

    public static ChooseFragment newInstance(Context c, Bundle args, SellFragment sellFragment, DrawerLayout drawerLayout) {
        ChooseFragment fragment = new ChooseFragment();
        fragment.setArguments(args);
        fragment.context = c;
        fragment.sellFragment = sellFragment;
        fragment.drawerLayout = drawerLayout;
        return fragment;
    }

    public void initTimeStrings() {
        timeStrings.add("不限");
        timeStrings.add("今日");
        timeStrings.add("本周");
        timeStrings.add("本月");
    }

    public void initUsers() {
        requestBean.setContext(getActivity());
        CommodityNetworkUtils.getUsers(requestBean, mhandler);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        targetUser = BmobUser.getCurrentUser(context.getApplicationContext());
//        ArrayAdapter nameAdapter=new ArrayAdapter<String>(
//                getActivity(),android.R.layout.simple_spinner_item,nameStrings);
//        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (BmobUser.getCurrentUser(getActivity().getApplicationContext()).getUsername().equals("admin")) {

            userAdapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_spinner_item, userStrings);
            userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            chooseUser.setAdapter(userAdapter);
        } else {
            userLayout.setVisibility(View.GONE);
        }

        final ArrayAdapter timeAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, timeStrings);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        chooseName.setAdapter(nameAdapter);
        chooseTime.setAdapter(timeAdapter);
//        chooseName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        chooseTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        targetDate = 0;
                        break;
                    case 1:
                        targetDate = Utils.getTimeOfDayStart();
                        break;
                    case 2:
                        targetDate = Utils.getTimeOfWeekStart();
                        break;
                    case 3:
                        targetDate = Utils.getTimeOfMonthStart();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                targetDate = 0;
            }
        });
        chooseUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                targetUser = bmobUsers.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellFragment.setRequestChoose(targetDate, targetUser);
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
