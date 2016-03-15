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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.CategoryExploreEvent;
import com.serfcompany.ecommerce.acart.event.CategoryFragmentGetDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.category.Category;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.presenter.main.CategoryFragmentPresenter;
import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.main.adapter.CategoriesListAdapter;
import com.serfcompany.ecommerce.acart.view.main.adapter.ProductsListAdapter;

import java.util.Collections;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 29.02.16.
 */
public class CategoryFragment extends AbstractTabFragment implements IFragmentView{

    private static final int LAYOUT = R.layout.fragment_categories;
    private RecyclerView rView;
    private RecyclerView prodRecView;
    private CategoryFragmentPresenter presenter;
    private CategoriesListAdapter mAdapter;
    private ProductsListAdapter prodAdapter;
    FrameLayout loadingFrame;
    TextView divider1, divider2;

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
        prodRecView = (RecyclerView) view.findViewById(R.id.recyclerProductsInCategory);
        loadingFrame = (FrameLayout) view.findViewById(R.id.progressBarFrame);
        divider1 = (TextView) view.findViewById(R.id.productDivider);
        divider2 = (TextView) view.findViewById(R.id.categoryDivider);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        rView.setLayoutManager(new LinearLayoutManager(getActivity()));
        prodRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter = new CategoryFragmentPresenter(this);
        presenter.loadDatas();
        mAdapter = new CategoriesListAdapter(getContext(), presenter);
        prodAdapter = new ProductsListAdapter(getContext());
        if (prodAdapter.getDatas() != null){
            prodRecView.setAdapter(prodAdapter);
        }
        rView.setAdapter(mAdapter);

    }

    public void onEvent(CategoryFragmentGetDataEvent getDataEvent){
        try {
            if (getDataEvent != null && getDataEvent.getDatas() != null && getDataEvent.getDatas().size() > 0) {
                rView.setVisibility(View.VISIBLE);
                divider1.setVisibility(View.GONE);
                divider2.setVisibility(View.GONE);
                prodRecView.setVisibility(View.GONE);
                mAdapter.setDatas(getDataEvent.getDatas());
                mAdapter.notifyDataSetChanged();
                loadingFrame.setVisibility(View.GONE);
            }
        }catch (Exception e){

            mAdapter.setDatas(Collections.<Category>emptyList());
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.categoryConnectionError);
            loadingFrame.setVisibility(View.GONE);
            fl.setVisibility(View.VISIBLE);
        }
    }

    public void onEvent(CategoryExploreEvent event){
        if (event != null && event.getCategories()!=null && event.getCategoriesProducts()!=null){
            loadingFrame.setVisibility(View.GONE);
            divider1.setVisibility(View.VISIBLE);
            if (event.getCategories().size() > 0) {
                divider2.setVisibility(View.VISIBLE);
                mAdapter.setDatas(event.getCategories());
                mAdapter.notifyDataSetChanged();
            } else {
                divider2.setVisibility(View.GONE);
                rView.setVisibility(View.GONE);
            }
            prodRecView.setVisibility(View.VISIBLE);
            prodAdapter.setDatas(event.getCategoriesProducts());
            prodAdapter.notifyDataSetChanged();
        }
    }

    public void onEvent(NetworkConnectionProblemEvent networkEvent){
        if (networkEvent != null && networkEvent.getDatas() != null) {
            loadingFrame.setVisibility(View.GONE);
            mAdapter.setDatas(Collections.<Category>emptyList());
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.categoryConnectionError);
            fl.setVisibility(View.VISIBLE);
        }
    }

    public void setContext(Context context){
        this.context = context;
    }
}
