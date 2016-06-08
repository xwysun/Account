package com.xwysun.account.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xwysun on 2016/5/23.
 */
public class SellDetailAdapter extends RecyclerView.Adapter<SellDetailAdapter.ViewHolder> {
    public ArrayList<SellBean> sellBeen = new ArrayList<>();
    public Context context;
    private LayoutInflater inflater;

    public SellDetailAdapter(Context c, ArrayList<SellBean> list) {
        context = c;
        sellBeen = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SellBean sellBean = sellBeen.get(holder.getAdapterPosition());
        holder.detailName.setText(sellBean.getCommodity().getName());
        holder.detailSale.setText("" + sellBean.getSales());
    }

    @Override
    public int getItemCount() {
        return sellBeen == null ? 0 : sellBeen.size();
    }


//    static class ViewHolder extends RecyclerView.ViewHolder  {
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
//    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.detail_name)
        TextView detailName;
        @Bind(R.id.detail_sale)
        TextView detailSale;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
