package com.serfcompany.ecommerce.acart.view.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.CategoryFragmentGetDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.category.Category;
import com.serfcompany.ecommerce.acart.presenter.main.CategoryFragmentPresenter;
import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.main.adapter.CategoriesListAdapter;

import java.util.Collections;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 29.02.16.
 */
public class CategoryFragment extends AbstractTabFragment implements IFragmentView{

    private static final int LAYOUT = R.layout.fragment_categories;
    private RecyclerView rView;
    private CategoryFragmentPresenter presenter;
    private CategoriesListAdapter mAdapter;

    public static CategoryFragment getInstance(Context context){
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_categories));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        rView = (RecyclerView) view.findViewById(R.id.recyclerCategoriesView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        rView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter = new CategoryFragmentPresenter(this);
        presenter.loadDatas();
        mAdapter = new CategoriesListAdapter(getContext(), presenter);
        rView.setAdapter(mAdapter);

    }

    public void onEvent(CategoryFragmentGetDataEvent getDataEvent){
        if (getDataEvent!=null && getDataEvent.getDatas()!=null && getDataEvent.getDatas().size()>0){
            mAdapter.setDatas(getDataEvent.getDatas());
        }
    }

    public void onEvent(NetworkConnectionProblemEvent networkEvent){
        if (networkEvent != null && networkEvent.getDatas() != null) {
            mAdapter.setDatas(Collections.<Category>emptyList());
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.categoryConnectionError);
            fl.setVisibility(View.VISIBLE);
        }
    }

    public void setContext(Context context){
        this.context = context;
    }
}
