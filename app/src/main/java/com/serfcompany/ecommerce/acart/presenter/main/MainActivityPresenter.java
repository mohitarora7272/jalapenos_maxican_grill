package com.serfcompany.ecommerce.acart.presenter.main;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetRecentItemsHTTP;
import com.serfcompany.ecommerce.acart.event.ExploreFragmentGetDatasEvent;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.parser.ProductsParser;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 29.02.16.
 */
public class MainActivityPresenter{
    Context context;

    public MainActivityPresenter(Context context){
        this.context = context;
    }

//    @Override
//    public void loadDatas() {
//        new AsyncTask<Void, Void, Void>() {
//            List<Product> datas = new ArrayList<Product>();
//            ExploreFragmentGetDatasEvent getDatasEvent;
//            @Override
//            protected Void doInBackground(Void... params) {
//                GetRecentItemsHTTP con = new GetRecentItemsHTTP();
//                ProductsParser parser = new ProductsParser();
//                datas = parser.parseProducts(con.loadProducts());
//                getDatasEvent= new ExploreFragmentGetDatasEvent(datas);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                EventBus.getDefault().post(getDatasEvent);
//            }
//        }.execute();
//    }
}
