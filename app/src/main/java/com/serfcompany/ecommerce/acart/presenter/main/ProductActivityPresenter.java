package com.serfcompany.ecommerce.acart.presenter.main;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetProductByIdHTTP;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.event.ProductByIdEvent;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.parser.ProductsParser;
import com.serfcompany.ecommerce.acart.parser.SingleProductParser;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 29.02.16.
 */
public class ProductActivityPresenter {
    Context context;
    Product product;

    public ProductActivityPresenter(Context context){
        this.context = context;
    }

    public void loadProduct(final int id){
        new AsyncTask<Void, Void, Void>() {
            Product product = new Product();
            ProductByIdEvent productEvent;
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    GetProductByIdHTTP con = new GetProductByIdHTTP();
                    SingleProductParser parser = new SingleProductParser();
                    product = parser.parseSingleProduct(con.loadProductByID(id));
                    productEvent = new ProductByIdEvent(product);
                } catch (Exception e){
                    EventBus.getDefault().post(new NetworkConnectionProblemEvent());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (productEvent != null) {
                    EventBus.getDefault().post(productEvent);
                }
            }
        }.execute();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
