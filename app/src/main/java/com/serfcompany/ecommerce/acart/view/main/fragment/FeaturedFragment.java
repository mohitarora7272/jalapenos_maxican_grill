package com.serfcompany.ecommerce.acart.view.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.FeaturedFragmentGetDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.presenter.main.FeaturedFragmentPresenter;
import com.serfcompany.ecommerce.acart.presenter.main.IExploreFragmentPresenter;
import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.main.adapter.ProductsListAdapter;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 01.03.16.
 */
public class FeaturedFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_explore;
    private RecyclerView rView;
    private IExploreFragmentPresenter fragmentPresenter;
    private ProductsListAdapter mAdapter;
    FrameLayout loadingFrame;

    public static FeaturedFragment getInstance(Context context){
        Bundle args = new Bundle();
        FeaturedFragment fragment = new FeaturedFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_featured));
        return fragment;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        rView = (RecyclerView) view.findViewById(R.id.recyclerView);
        loadingFrame = (FrameLayout) view.findViewById(R.id.progressBarFrame);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setHasOptionsMenu(true);
        //register
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        //init
        rView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (mAdapter == null){
            fragmentPresenter = new FeaturedFragmentPresenter(this);
            fragmentPresenter.loadDatas();
            mAdapter = new ProductsListAdapter(getContext(), fragmentPresenter);

        } else {
            loadingFrame.setVisibility(View.GONE);
            Log.i("LOG", "Loading old data to featured");
        }

        rView.setAdapter(mAdapter);

    }

    // EventBus Subscribe
    public void onEvent(FeaturedFragmentGetDataEvent getDatasEvent){
        try {
            if (getDatasEvent != null && getDatasEvent.getDatas() != null && getDatasEvent.getDatas().size() > 0) {
                mAdapter.setDatas(getDatasEvent.getDatas());
                mAdapter.notifyDataSetChanged();
                loadingFrame.setVisibility(View.GONE);
            }
        }catch (Exception e){
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.explorConnectionError);
            fl.setVisibility(View.VISIBLE);
        }
    }
    public void onEvent(NetworkConnectionProblemEvent networkEvent){
        if (networkEvent != null && networkEvent.getDatas() != null) {
            mAdapter.setDatas(networkEvent.getDatas());
            loadingFrame.setVisibility(View.GONE);
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.explorConnectionError);
            fl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        Log.i("LOG", "Featured data was cleared");
        fragmentPresenter.clearDatas();
        getContext().getResources().flushLayoutCache();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
}
