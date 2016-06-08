package com.xwysun.account.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.Bean.SellRecords;
import com.xwysun.account.R;

import java.util.ArrayList;

/**
 * Created by xwysun on 2016/5/22.
 */
public class SellListAdapter extends RecyclerView.Adapter<SellListAdapter.ViewHolder> {
    public Context context;
    public ArrayList<SellRecords> sellRecordses;
    public LayoutInflater inflater;
    public SellListAdapter(Context c, ArrayList<SellRecords> list){
        context=c;
        sellRecordses=list;
        inflater=LayoutInflater.from(c);
    }
    public void notifySell(ArrayList<SellRecords> list){
        sellRecordses=list;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_sellrecords,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SellRecords temp=sellRecordses.get(holder.getAdapterPosition());
//        holder.sellName.setText("商品："+sellRecordses.get().getName());
        holder.sellTime.setText("上传时间："+temp.getUpdatedAt());
        holder.sellUser.setText("上传者："+temp.getUser().getUsername());
        holder.sellMoney.setText("总销售额："+temp.getSaleSum());
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    onItemClickListener.OnItemClick(holder.itemView,pos);
                }
            });
        }
}

    @Override
    public int getItemCount() {
        return sellRecordses==null?0:sellRecordses.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
//        TextView sellName;
        LinearLayout layout;
        TextView sellUser;
        TextView sellMoney;
        TextView sellTime;
        public ViewHolder(View itemView) {
            super(itemView);
//            sellName=(TextView)itemView.findViewById(R.id.sell_name);
            layout=(LinearLayout)itemView.findViewById(R.id.sell_item);
            sellUser=(TextView)itemView.findViewById(R.id.sell_user);
            sellMoney=(TextView)itemView.findViewById(R.id.sell_saleMoney);
            sellTime=(TextView)itemView.findViewById(R.id.sell_time);
        }
    }
}
