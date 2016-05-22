package com.xwysun.account.OpenSrc;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by xwysun on 2016/5/14.
 */
public class RecMoveHelperCallback extends ItemTouchHelper.SimpleCallback{
    private RecyclerView.Adapter mAdapter;
    private ArrayList datas;
    public RecMoveHelperCallback(int dragDirs, int swipeDirs, RecyclerView.Adapter adapter, ArrayList list) {
        super(dragDirs, swipeDirs);
        mAdapter=adapter;
        datas=list;
    }
    public void adddata(Object object){
        datas.add(object);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition=viewHolder.getAdapterPosition();
        int toPosition=target.getAdapterPosition();
        if (fromPosition < toPosition) {
            //分别把中间所有的item的位置重新交换
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(datas, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(datas, i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromPosition, toPosition);
        //返回true表示执行拖动
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        datas.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState== ItemTouchHelper.ACTION_STATE_SWIPE){
            float alpha=1-Math.abs(dX)/(float)viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        }
    }
}
