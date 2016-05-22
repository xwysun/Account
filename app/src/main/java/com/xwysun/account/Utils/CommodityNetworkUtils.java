package com.xwysun.account.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;

import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.Bean.RequestBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
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

}
