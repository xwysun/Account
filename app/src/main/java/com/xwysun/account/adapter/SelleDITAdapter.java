package com.xwysun.account.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.xwysun.account.Bean.SellBean;
import com.xwysun.account.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xwysun on 2016/5/23.
 */
public class SellEditAdapter extends RecyclerView.Adapter<SellEditAdapter.ViewHolder> {
    public ArrayList<SellBean> sellBeen = new ArrayList<>();
    public Context context;
    private LayoutInflater inflater;
    double sales;
    public interface OnFocusLostListener{
        void Onchange();
    }
    private OnFocusLostListener onFocusLostListener;
    public void setOnFocusLostListener(OnFocusLostListener listener){
        this.onFocusLostListener=listener;
    }
    public SellEditAdapter(Context c, ArrayList<SellBean> list) {
        context = c;
        sellBeen = list;
        inflater = LayoutInflater.from(context);
    }
    public void notifySellbeen(ArrayList<SellBean> list){
        sellBeen=list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_detail_edit, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SellBean sellBean = sellBeen.get(holder.getAdapterPosition());
        sales=0;
        holder.detailNameEdit.setText(sellBean.getCommodity().getName());
        holder.detailSaleEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    holder.detailSaleEdit.setText("");
                } else {
                    if (!holder.detailSaleEdit.getText().toString().trim().equals("")){
                        sales=Integer.parseInt(holder.detailSaleEdit.getText().toString().trim());
                        sellBean.setSales(sales);
                        sellBean.setSaleMoney(sales*sellBean.getCommodity().getPrice());
                    }else {
                        sellBean.setSales(0);
                    }
                    onFocusLostListener.Onchange();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sellBeen == null ? 0 : sellBeen.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.detail_name_edit)
        TextView detailNameEdit;
        @Bind(R.id.detail_sale_edit)
        EditText detailSaleEdit;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
