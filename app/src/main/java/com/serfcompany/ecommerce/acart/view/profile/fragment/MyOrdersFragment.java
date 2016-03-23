package com.serfcompany.ecommerce.acart.view.profile.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.MyOrdersFragmentGetDataEvent;
import com.serfcompany.ecommerce.acart.presenter.profile.MyOrdersFragmentPresenter;
import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.profile.adapter.OrdersListAdapter;

import de.greenrobot.event.EventBus;

public class MyOrdersFragment extends AbstractTabFragment{

    public static final int LAYOUT = R.layout.fragment_orders;
    private SharedPreferences loginPrefs;
    private OrdersListAdapter mAdapter;
    private MyOrdersFragmentPresenter fragmentPresenter;
    private RecyclerView rView;


    public static MyOrdersFragment getInstance(Context context){
        Bundle args = new Bundle();
        MyOrdersFragment fragment = new MyOrdersFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle("My Orders");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        rView = (RecyclerView) view.findViewById(R.id.my_ordersRecyclerView);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        loginPrefs = getContext().getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE);
        String username = loginPrefs.getString(Constants.USERNAME, "");
        String password = loginPrefs.getString(Constants.PASSWORD, "");



        rView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentPresenter = new MyOrdersFragmentPresenter(this);
        fragmentPresenter.loadDatas(username, password, "All");
        mAdapter = new OrdersListAdapter(getContext(), fragmentPresenter);
        rView.setAdapter(mAdapter);
    }

    public void onEvent(MyOrdersFragmentGetDataEvent getDataEvent){
        if (getDataEvent != null && getDataEvent.getMyOrders() != null){
            mAdapter.setDatas(getDataEvent.getMyOrders());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        fragmentPresenter.clearDatas();
        getContext().getResources().flushLayoutCache();
        super.onDestroy();
    }

    private void setContext(Context context){
        this.context = context;
    }
}
