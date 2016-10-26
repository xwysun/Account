package com.xwysun.account.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.Bean.SimpleCount;
import com.xwysun.account.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xwysun on 2016/5/23.
 */
public class AcountListAdapter extends RecyclerView.Adapter<AcountListAdapter.ViewHolder> {
    public ArrayList<SimpleCount> simpleCounts = new ArrayList<>();
    public Context context;
    private LayoutInflater inflater;

    public AcountListAdapter(Context c, ArrayList<SimpleCount> list) {
        context = c;
        simpleCounts = list;
        inflater = LayoutInflater.from(context);
    }
    public void notifyCounts(ArrayList<SimpleCount> list){
        simpleCounts=list;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_countlist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SimpleCount simpleCount = simpleCounts.get(holder.getAdapterPosition());
        holder.comName.setText(simpleCount.getCommodity().getName());
        holder.comSale.setText(String.format("%.2f", simpleCount.getCountSale()));
        holder.comMoney.setText(String.format("%.2f", simpleCount.getCountSale()*simpleCount.getCommodity().getPrice()));
    }

    @Override
    public int getItemCount() {
        return simpleCounts == null ? 0 : simpleCounts.size();
    }


//    static class ViewHolder extends RecyclerView.ViewHolder  {
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
//    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.com_name)
        TextView comName;
        @Bind(R.id.com_sale)
        TextView comSale;
        @Bind(R.id.com_money)
        TextView comMoney;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
