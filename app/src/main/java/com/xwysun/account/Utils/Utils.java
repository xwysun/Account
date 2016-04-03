package com.xwysun.account.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xwysun on 2016/4/3.
 */
public class Utils {
    public static void toast(Context context,int resId){
        Toast.makeText(context,resId,Toast.LENGTH_SHORT).show();
    }
    public static void toast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
