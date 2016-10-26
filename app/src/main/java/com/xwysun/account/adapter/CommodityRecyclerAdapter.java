package com.xwysun.account.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.Bean.RequestBean;
import com.xwysun.account.R;
import com.xwysun.account.Utils.CommodityNetworkUtils;
import com.xwysun.account.Utils.Utils;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

/**
 * Created by xwysun on 2016/5/21.
 */
public class CommodityRecyclerAdapter extends RecyclerView.Adapter<CommodityRecyclerAdapter.ViewHolder> {
    public ArrayList<Commodity> commodities=new ArrayList<>();
    public Context context;
    public LayoutInflater inflater;
    private RequestBean defaultRequest=new RequestBean();
    public int FLAG= CommodityNetworkUtils.DEFAULT_REQUEST;
    public static final String TAG="CommodityAdapter";

    public CommodityRecyclerAdapter(Context c,ArrayList<Commodity> list){
        context=c;
        inflater=LayoutInflater.from(context);
        commodities=list;
    }
    public void notifyCommodity(ArrayList<Commodity> list){
        commodities=list;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_commodity,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Commodity itemCommodity=commodities.get(holder.getAdapterPosition());
        Log.d(TAG,itemCommodity.toString());
        holder.commodityName.setText("商品名称 ："+itemCommodity.getName());
        holder.commodityStock.setText("剩余库存（kg）："+itemCommodity.getStock());
        holder.commodityOldStock.setText("基础库存（kg）："+itemCommodity.getOldStock());
        holder.commodityPrice.setText("单价(元/kg）："+itemCommodity.getPrice());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"Count:"+commodities.size());
        return commodities==null?0:commodities.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView commodityItem;
        TextView commodityName;
        TextView commodityPrice;
        TextView commodityOldStock;
        TextView commodityStock;

        public ViewHolder(View itemView) {
            super(itemView);
            commodityItem= (CardView) itemView.findViewById(R.id.commodity_item);
            commodityName=(TextView)itemView.findViewById(R.id.commodity_name);
            commodityPrice=(TextView)itemView.findViewById(R.id.commodity_price);
            commodityOldStock=(TextView)itemView.findViewById(R.id.commodity_old_stock);
            commodityStock=(TextView)itemView.findViewById(R.id.commodity_stock);
        }
    }
}
