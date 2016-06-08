package com.xwysun.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.xwysun.account.Bean.Commodity;

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
    ArrayList<String> nameStrings=new ArrayList<>();
    ArrayList<String> timeStrings=new ArrayList<>();
    ArrayList<String> userStrings=new ArrayList<>();
    ArrayList<BmobUser> users=new ArrayList<>();
    ArrayList<Commodity> commodities=new ArrayList<>();
    BmobUser targetUser;
    Commodity targetCommodity;
    public Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTimeStrings();

    }
    public static ChooseFragment newInstance(Context c,Bundle args){
        ChooseFragment fragment=new ChooseFragment();
        fragment.setArguments(args);
        fragment.context=c;
        return fragment;
    }
    public void initTimeStrings(){
        timeStrings.add("不限");
        timeStrings.add("今日");
        timeStrings.add("本周");
        timeStrings.add("本月");
    }
    public void initUsers(){

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
        targetUser=BmobUser.getCurrentUser(context.getApplicationContext());
        ArrayAdapter nameAdapter=new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_spinner_item,nameStrings);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter userAdapter=new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_spinner_item,userStrings);
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter timeAdapter=new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_spinner_item,timeStrings);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        chooseName.setAdapter(nameAdapter);
        chooseUser.setAdapter(userAdapter);
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
//        chooseTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        chooseUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
