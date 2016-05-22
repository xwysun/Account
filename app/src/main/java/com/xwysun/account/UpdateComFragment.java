package com.xwysun.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.OpenSrc.RecMoveHelperCallback;
import com.xwysun.account.Utils.Utils;
import com.xwysun.account.adapter.UpdateComAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xwysun on 2016/5/21.
 */
public class UpdateComFragment extends Fragment {

    ArrayList<Commodity> commitList = new ArrayList<>();
    RecMoveHelperCallback mCallback;
    BmobUser user;
    @Bind(R.id.name_edit)
    EditText nameEdit;
    @Bind(R.id.price_edit)
    EditText priceEdit;
    @Bind(R.id.oldstock_edit)
    EditText oldstockEdit;
    @Bind(R.id.stock_edit)
    EditText stockEdit;
    @Bind(R.id.addButton)
    Button addButton;
    @Bind(R.id.commitButton)
    Button commitButton;
    @Bind(R.id.waitCommitList)
    RecyclerView waitCommitList;

    public static UpdateComFragment newInstance(Bundle args) {
        UpdateComFragment fragment = new UpdateComFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = BmobUser.getCurrentUser(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_updatecom, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final UpdateComAdapter adapter = new UpdateComAdapter(getActivity(), commitList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        waitCommitList.setLayoutManager(layoutManager);
        waitCommitList.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commodity temp = getEdit();
                commitList.add(temp);
//                adapter.addCommodity(temp);
//                mCallback.adddata(temp);
                clearEdit();
            }
        });
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BmobObject> list=new ArrayList<BmobObject>();
                list.addAll(adapter.commodities);
                new BmobObject().insertBatch(getContext(),list, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                    @Override
                    public void onFailure(int code, String msg) {
                        // TODO Auto-generated method stub
                        Utils.toast(getActivity(),"批量添加失败:"+msg);
                    }
                });
            }
        });
        mCallback = new RecMoveHelperCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT, adapter, commitList);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(waitCommitList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public Commodity getEdit() {
        String name = nameEdit.getText().toString().trim();
        double price = Double.valueOf(priceEdit.getText().toString().trim());
        double oldStock = Double.valueOf(oldstockEdit.getText().toString().trim());
        double stock = Double.valueOf(stockEdit.getText().toString().trim());
        return new Commodity(name, price, oldStock, stock, user);
    }

    public void clearEdit() {
        nameEdit.setText("");
        priceEdit.setText("");
        oldstockEdit.setText("");
        stockEdit.setText("");
    }
}
