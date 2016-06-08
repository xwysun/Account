package com.xwysun.account.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by xwysun on 2016/5/22.
 * 未完工（或弃用）的adapter
 */
public class ChooseSpinnerAdapter extends BaseAdapter {
    public ArrayList<String> arrayList=new ArrayList<>();
    public Context context;
    public LayoutInflater inflater;
    public ChooseSpinnerAdapter(Context c,ArrayList<String> list){
        context=c;
        arrayList=list;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return arrayList==null?0:arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View v=inflater.inflate()
        return null;
    }
}
