package com.serfcompany.ecommerce.acart.presenter.main;

import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetProductByKeywordHTTP;
import com.serfcompany.ecommerce.acart.HTTPHolders.GetRecentItemsHTTP;
import com.serfcompany.ecommerce.acart.event.ExploreFragmentGetDatasEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.parser.ProductsParser;
import com.serfcompany.ecommerce.acart.presenter.AbstractPresenter;
import com.serfcompany.ecommerce.acart.view.main.fragment.IFragmentView;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 29.02.16.
 */
public class ExploreFragmentPresenterImpl extends AbstractPresenter implements IExploreFragmentPresenter{
    IFragmentView iFragmentView;
    ExploreFragmentGetDatasEvent getDatasEvent;
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
        clearDatas();
        if (this.isNetworkAvailable(iFragmentView.getActivity().getBaseContext())) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        GetRecentItemsHTTP con = new GetRecentItemsHTTP();
                        ProductsParser parser = new ProductsParser();
                        setDatas(parser.parseProducts(con.loadProducts()));
                        getDatasEvent = new ExploreFragmentGetDatasEvent(getDatas());
                    } catch (Exception e){
                        EventBus.getDefault().post(new NetworkConnectionProblemEvent());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (getDatasEvent != null) {
                        EventBus.getDefault().post(getDatasEvent);
                    }
                }
            }.execute();
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
