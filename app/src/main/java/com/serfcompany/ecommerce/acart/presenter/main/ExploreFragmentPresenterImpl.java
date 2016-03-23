package com.serfcompany.ecommerce.acart.presenter.main;

import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetProductByKeywordHTTP;
import com.serfcompany.ecommerce.acart.HTTPHolders.GetRecentItemsHTTP;
import com.serfcompany.ecommerce.acart.event.ExploreFragmentGetDatasEvent;
import com.serfcompany.ecommerce.acart.event.ExploreFragmentGetMoreDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.event.ScreenRotateEvent;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.parser.ProductsParser;
import com.serfcompany.ecommerce.acart.presenter.AbstractPresenter;
import com.serfcompany.ecommerce.acart.view.main.fragment.IFragmentView;

import java.util.List;

import de.greenrobot.event.EventBus;

public class ExploreFragmentPresenterImpl extends AbstractPresenter implements IExploreFragmentPresenter{
    IFragmentView iFragmentView;
    ExploreFragmentGetDatasEvent getDatasEvent;
    ExploreFragmentGetMoreDataEvent getMoreDataEvent;
    List<Product> datas;

    public ExploreFragmentPresenterImpl(IFragmentView iFragmentView) {
        this.iFragmentView = iFragmentView;
    }

    @Override
    public void clearDatas() {
        if (datas!=null && getDatasEvent != null) {
            datas.clear();
            getDatasEvent.clearDatas();
        }
    }

    @Override
    public void loadDatas() {
        if (this.isNetworkAvailable(iFragmentView.getActivity().getBaseContext())) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        GetRecentItemsHTTP con = new GetRecentItemsHTTP();
                        ProductsParser parser = new ProductsParser();
                        setDatas(parser.parseProducts(con.loadProducts(1)));

                    } catch (Exception e){
                        EventBus.getDefault().post(new NetworkConnectionProblemEvent());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    getDatasEvent = new ExploreFragmentGetDatasEvent(getDatas());
                    EventBus.getDefault().post(getDatasEvent);
                }
            }.execute();
        } else {
            NetworkConnectionProblemEvent networkEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkEvent);
        }
    }

    @Override
    public void loadMoreDatas(int page) {

        if (this.isNetworkAvailable(iFragmentView.getActivity().getBaseContext())) {
            new AsyncTask<Integer, Void, Void>() {

                @Override
                protected Void doInBackground(Integer... params) {
                    try {
                        GetRecentItemsHTTP con = new GetRecentItemsHTTP();
                        ProductsParser parser = new ProductsParser();
                        setDatas(parser.parseProducts(con.loadProducts(params[0])));
                        getMoreDataEvent = new ExploreFragmentGetMoreDataEvent(getDatas());
                    } catch (Exception e){
                        EventBus.getDefault().post(new NetworkConnectionProblemEvent());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (getMoreDataEvent != null) {
                        EventBus.getDefault().post(getMoreDataEvent);
                    }
                }
            }.execute(page);
        } else {
            NetworkConnectionProblemEvent networkEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkEvent);
        }
    }

    @Override
    public void loadSearchData(final String keyword){
        if (this.isNetworkAvailable(iFragmentView.getActivity().getBaseContext())) {
            new AsyncTask<Void, Void, Void>() {
                ExploreFragmentGetDatasEvent getDatasEvent;

                @Override
                protected Void doInBackground(Void... params) {
                    GetProductByKeywordHTTP con = new GetProductByKeywordHTTP();
                    ProductsParser parser = new ProductsParser();
                    setDatas(parser.parseProducts(con.loadProductByKeyword(keyword)));

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    getDatasEvent = new ExploreFragmentGetDatasEvent(getDatas());
                    EventBus.getDefault().post(getDatasEvent);
                }
            }.execute();
        } else {
            NetworkConnectionProblemEvent networkEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkEvent);
        }
    }

    @Override
    public void returnSavedDatas(){
        ScreenRotateEvent screenRotateEvent = new ScreenRotateEvent(getDatas());
        EventBus.getDefault().post(screenRotateEvent);
    }

    @Override
    public List<Product> getData() {
        return datas;
    }

    public List<Product> getDatas() {
        return datas;
    }

    public void setDatas(List<Product> datas) {
        this.datas = datas;
    }


}
