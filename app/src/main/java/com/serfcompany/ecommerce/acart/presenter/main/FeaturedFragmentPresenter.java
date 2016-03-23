package com.serfcompany.ecommerce.acart.presenter.main;

import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetFeaturedProductsHTTP;
import com.serfcompany.ecommerce.acart.event.FeaturedFragmentGetDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.parser.ProductsParser;
import com.serfcompany.ecommerce.acart.presenter.AbstractPresenter;
import com.serfcompany.ecommerce.acart.view.main.fragment.FeaturedFragment;

import java.util.List;

import de.greenrobot.event.EventBus;

public class FeaturedFragmentPresenter extends AbstractPresenter implements IExploreFragmentPresenter{
    FeaturedFragment fragmentView;
    List<Product> datas;
    FeaturedFragmentGetDataEvent getDataEvent;

    public FeaturedFragmentPresenter(FeaturedFragment fragmentView){
        this.fragmentView = fragmentView;
    }

    public void clearDatas() {
        if (datas!=null) {
            datas.clear();
            getDataEvent.clearDatas();
        }
    }

    @Override
    public void returnSavedDatas() {

    }

    @Override
    public void loadDatas(){
        clearDatas();
        if (this.isNetworkAvailable(fragmentView.getActivity().getBaseContext())) {
            new AsyncTask<Void, Void, Void>() {


                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        GetFeaturedProductsHTTP con = new GetFeaturedProductsHTTP();
                        ProductsParser parser = new ProductsParser();
                        setDatas(parser.parseProducts(con.loadFeatured()));
                        getDataEvent = new FeaturedFragmentGetDataEvent(getDatas());
                    } catch (Exception e){

                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (getDataEvent!= null) {
                        EventBus.getDefault().post(getDataEvent);
                    }
                }
            }.execute();
        } else {
            NetworkConnectionProblemEvent networkEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkEvent);
        }
    }

    @Override
    public void loadMoreDatas(int page) {

    }

    @Override
    public List<Product> getData() {
        return datas;
    }

    @Override
    public void loadSearchData(String keyword) {

    }


    public List<Product> getDatas() {
        return datas;
    }

    public void setDatas(List<Product> datas) {
        this.datas = datas;
    }
}
