package com.xwysun.account.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.R;
import com.xwysun.account.UpdateComFragment;
import com.xwysun.account.Utils.Utils;

import java.util.ArrayList;

/**
 * Created by xwysun on 2016/5/22.
 */
public class UpdateComAdapter extends RecyclerView.Adapter<UpdateComAdapter.ViewHolder> {
    public Context context;
    public ArrayList<Commodity> commodities;
    public LayoutInflater inflater;

    public UpdateComAdapter(Context c, ArrayList<Commodity> list){
        context=c;
        commodities=list;
        inflater=LayoutInflater.from(context);
    }
    public void addCommodity(Commodity commodity){
        commodities.add(commodity);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.item_commodity,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Commodity itemCommodity=commodities.get(holder.getAdapterPosition());
        holder.commodityName.setText("商品名称 ："+itemCommodity.getName());
        holder.commodityStock.setText("剩余库存（kg）："+itemCommodity.getStock());
        holder.commodityOldStock.setText("基础库存（kg）："+itemCommodity.getOldStock());
        holder.commodityPrice.setText("单价(元/kg）："+itemCommodity.getPrice());
    }


    @Override
    public int getItemCount() {
        return commodities==null?0:commodities.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
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
