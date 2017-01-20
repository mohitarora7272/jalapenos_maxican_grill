package com.serfcompany.ecommerce.acart.view.main.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetCommentByPostID;
import com.serfcompany.ecommerce.acart.HTTPHolders.GetNotificationsHTTP;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.ExploreFragmentGetDatasEvent;
import com.serfcompany.ecommerce.acart.event.ExploreFragmentGetMoreDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.event.ScreenRotateEvent;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.presenter.EndlessRecyclerViewScrollListener;
import com.serfcompany.ecommerce.acart.presenter.main.ExploreFragmentPresenterImpl;
import com.serfcompany.ecommerce.acart.presenter.main.IExploreFragmentPresenter;
import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.main.adapter.ProductsListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.SubscriberExceptionEvent;

public class ExploreFragment extends AbstractTabFragment implements IFragmentView, android.support.v7.widget.SearchView.OnQueryTextListener{

    private static final int LAYOUT = R.layout.fragment_explore;
    private RecyclerView rView;
    private IExploreFragmentPresenter iFragmentPresenter;
    private ProductsListAdapter mAdapter;
    FrameLayout loadingFrame;
    private List<Product> modelForFilter;
    private List<Product> model;
    EndlessRecyclerViewScrollListener listener;

    public static ExploreFragment getInstance(Context context){
        Bundle args = new Bundle();
        ExploreFragment fragment = new ExploreFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_explore));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        rView = (RecyclerView) view.findViewById(R.id.recyclerView);
        loadingFrame = (FrameLayout) view.findViewById(R.id.progressBarFrame);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        Log.i("LOG", "ExploreLOG data was cleared");
        iFragmentPresenter.clearDatas();
        getContext().getResources().flushLayoutCache();
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        iFragmentPresenter = new ExploreFragmentPresenterImpl(this);
        modelForFilter = new ArrayList<>();
        iFragmentPresenter.loadDatas();
        mAdapter = new ProductsListAdapter(getContext());
        mAdapter.setDatas(model);
        rView.setAdapter(mAdapter);
        rView.setLayoutManager(linearLayoutManager);

        listener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public synchronized void onLoadMore(final int page, int totalItemsCount) {
                iFragmentPresenter.loadMoreDatas(page);
                return;
            }
        };

        TextView tryAgain = (TextView) view.findViewById(R.id.exploreTryAgain);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFragmentPresenter.loadDatas();
            }
        });

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
//                GetCommentByPostID con = new GetCommentByPostID();
//                try {
//                    con.getComments("127", null, "administrator", "admin-woo123!$");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                return null;
            }
        }.execute();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu, menu);

        final MenuItem item = menu.findItem(R.id.search);
        final android.support.v7.widget.SearchView searchView =
                (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                iFragmentPresenter.loadDatas();
                return false;
            }
        });
    }

    public void onEvent(SubscriberExceptionEvent exceptionEvent){
        mAdapter.setDatas(Collections.<Product>emptyList());
        loadingFrame.setVisibility(View.GONE);
        FrameLayout fl = (FrameLayout) view.findViewById(R.id.explorConnectionError);
        fl.setVisibility(View.VISIBLE);
    }

    // EventBus Subscribe
    public void onEvent(ExploreFragmentGetDatasEvent getDatasEvent){
        Log.i("LOG", "INITIAL GET DATA EVENT");
        try {
            if (getDatasEvent != null && getDatasEvent.getDatas() != null && getDatasEvent.getDatas().size() > 0) {
                model = getDatasEvent.getDatas();
                mAdapter.setDatas(model);
                mAdapter.notifyDataSetChanged();
                rView.addOnScrollListener(listener);
                loadingFrame.setVisibility(View.GONE);
                FrameLayout fl = (FrameLayout) view.findViewById(R.id.explorConnectionError);
                fl.setVisibility(View.GONE);

                modelForFilter.clear();
                modelForFilter.addAll(getDatasEvent.getDatas());
            }
        } catch (Exception e){
            mAdapter.setDatas(Collections.<Product>emptyList());
            loadingFrame.setVisibility(View.GONE);
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.explorConnectionError);
            fl.setVisibility(View.VISIBLE);
        }
    }

    //on load more products
    public synchronized void onEvent(final ExploreFragmentGetMoreDataEvent event){
        Log.i("LOG", "INITIAL GET MORE DATA EVENT");
        if (event != null && event.getDatas()!=null) {
            int curSize = mAdapter.getItemCount();
            model.addAll(event.getDatas());
            modelForFilter = model;
            Log.i("LOG", "Expecting " + model.size() + " displaying");
            mAdapter.setDatas(model);
            mAdapter.notifyDataSetChanged();
        }
    }

    //EventBus Subscribe
    public void onEvent(NetworkConnectionProblemEvent networkEvent){
        if (networkEvent != null && networkEvent.getDatas() != null) {
            mAdapter.setDatas(networkEvent.getDatas());
            loadingFrame.setVisibility(View.GONE);
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.explorConnectionError);
            fl.setVisibility(View.VISIBLE);
        }
    }

    public void setContext(Context context){
        this.context = context;
    }

    private List<Product> filter(List<Product> models, String query) {
        query = query.toLowerCase();

        final List<Product> filteredModelList = new ArrayList<>();
        for (Product model : models) {
            final String text = model.getGeneral().getTitle().toLowerCase();
            final String description = model.getGeneral().getContent().getExcepts().toLowerCase();
            String category =" ";
            if (model.getCategories().size() > 0){
                category = model.getCategories().get(0).getName().toLowerCase();
            }
            if (text.contains(query) || description.contains(query) || category.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query == null){
            iFragmentPresenter.loadDatas();
        }else {
            iFragmentPresenter.loadSearchData(query);
        }
        InputMethodManager inputManager =
                (InputMethodManager) getActivity().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        View focus = getActivity().getCurrentFocus();
        if (inputManager != null && focus != null) {
            inputManager.hideSoftInputFromWindow(
                    focus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Product> filteredModelList = filter(modelForFilter, newText);
        mAdapter.animateTo(filteredModelList);
        rView.removeOnScrollListener(listener);
        rView.scrollToPosition(0);
        return true;

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