package com.xwysun.account.Utils;

import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;

import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.Bean.RequestBean;
import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.Bean.SellRecords;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xwysun on 2016/5/21.
 */
public class CommodityNetworkUtils {
    /**
     * 工具类，需要实现各种请求，后续编写
     */

    public static final int DEFAULT_REQUEST=0;
    public static final int CHOOSED_REQUEST=1;
    public static final String DATA="data";
    public static final String TAG="NetWorkUtils";
    public static void getCommodities(RequestBean requestBean, final Handler handler){
        final ArrayList<Commodity> commodities=new ArrayList<Commodity>();
        BmobQuery<Commodity> query = new BmobQuery<Commodity>();
        final Bundle bundle=new Bundle();
        final Message message=new Message();
        query.addWhereEqualTo("owner",requestBean.getUser());
        query.setLimit(50);
        query.findObjects(requestBean.getContext(), new FindListener<Commodity>() {
            @Override
            public void onSuccess(List<Commodity> list) {
                commodities.addAll(list);
                message.what=1;
                bundle.putSerializable(DATA,commodities);
                message.setData(bundle);
                handler.sendMessage(message);
                Log.d(TAG,"Count:"+commodities.size());
            }

            @Override
            public void onError(int i, String s) {
                Log.e(TAG,s);
                message.what=0;
                handler.sendMessage(message);
            }
        }
        );
    }
    public static void getSellRecords(RequestBean requestBean, final Handler handler){
        final ArrayList<SellRecords> sellRecords=new ArrayList<>();
        BmobQuery<SellRecords> query = new BmobQuery<SellRecords>();
        final Bundle bundle=new Bundle();
        final Message message=new Message();
        query.addWhereEqualTo("user",requestBean.getUser());
        query.setLimit(50);
        query.order("-createdAt");
        Date start=requestBean.getDate();
        if (start!=null){
            query.addWhereGreaterThan("createdAt",new BmobDate(start));
        }
        query.setSkip(requestBean.getSkipNum());
        query.findObjects(requestBean.getContext(), new FindListener<SellRecords>() {
                    @Override
                    public void onSuccess(List<SellRecords> list) {
                            Log.d(TAG,"listsize"+list.size());
                        sellRecords.addAll(list);
                        message.what=1;
                        bundle.putSerializable(DATA,sellRecords);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.e(TAG,s);
                        message.what=0;
                        handler.sendMessage(message);
                    }
                }
        );
    }
    public static void getUsers(RequestBean requestBean, final Handler handler){
        final Bundle bundle=new Bundle();
        final Message message=new Message();
        final ArrayList<BmobUser> bmobUsers=new ArrayList<>();
        BmobQuery<BmobUser> query = new BmobQuery<BmobUser>();
        query.findObjects(requestBean.getContext(),new FindListener<BmobUser>() {
            @Override
            public void onSuccess(List<BmobUser> list) {
                Log.d(TAG,"users"+list.size());
                bmobUsers.addAll(list);
                message.what=1;
                bundle.putSerializable(DATA,bmobUsers);
                message.setData(bundle);
                handler.sendMessage(message);
            }
            @Override
            public void onError(int i, String s) {
                Log.e(TAG,s);
                message.what=0;
                handler.sendMessage(message);
            }
        });
    }
    public static void getAllSells(RequestBean requestBean, final Handler handler){
        BmobQuery<SellRecords> query = new BmobQuery<SellRecords>();
        List<BmobQuery<SellRecords>> and=new ArrayList<BmobQuery<SellRecords>>();
        final ArrayList<SellRecords> sellRecords=new ArrayList<>();
        BmobQuery<SellRecords> q1 = new BmobQuery<SellRecords>();
        final Bundle bundle=new Bundle();
        final Message message=new Message();
        Date end=requestBean.getDate();
        if (end!=null){
            q1.addWhereLessThanOrEqualTo("createdAt",new BmobDate(end));
        }
        BmobQuery<SellRecords> q2= new BmobQuery<SellRecords>();
        Date start=requestBean.getLastMonth();
        if (start!=null){
            q2.addWhereGreaterThanOrEqualTo("createdAt",new BmobDate(start));
        }
        and.add(q1);
        and.add(q2);
        query.and(and);
        query.setLimit(1000);
        query.findObjects(requestBean.getContext(), new FindListener<SellRecords>() {
                    @Override
                    public void onSuccess(List<SellRecords> list) {
                        Log.d(TAG,"listsize"+list.size());
                        sellRecords.addAll(list);
                        message.what=1;
                        bundle.putSerializable(DATA,sellRecords);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.e(TAG,s);
                        message.what=0;
                        handler.sendMessage(message);
                    }
                }
        );
    }
}
