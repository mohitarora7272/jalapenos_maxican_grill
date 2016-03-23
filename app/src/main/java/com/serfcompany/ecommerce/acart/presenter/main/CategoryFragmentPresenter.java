package com.serfcompany.ecommerce.acart.presenter.main;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetCategoriesHTTP;
import com.serfcompany.ecommerce.acart.HTTPHolders.GetChildCategories;
import com.serfcompany.ecommerce.acart.HTTPHolders.GetProductsByCategoryID;
import com.serfcompany.ecommerce.acart.event.CategoryExploreEvent;
import com.serfcompany.ecommerce.acart.event.CategoryFragmentGetDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.category.Category;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.parser.CategoriesParser;
import com.serfcompany.ecommerce.acart.parser.ProductsParser;
import com.serfcompany.ecommerce.acart.presenter.AbstractPresenter;
import com.serfcompany.ecommerce.acart.view.main.fragment.IFragmentView;

import java.io.IOException;
import java.util.List;

import de.greenrobot.event.EventBus;

public class CategoryFragmentPresenter extends AbstractPresenter {
    List<Category> categories;
    List<Product> products;
    CategoryFragmentGetDataEvent getDataEvent;
    IFragmentView iFragmentView;
    Context context;

    public CategoryFragmentPresenter(IFragmentView iFragmentView){
        this.context = iFragmentView.getActivity();
    }
    public CategoryFragmentPresenter(Context context){
        this.context = context;
    }

    public void loadDatas(){
        if (this.isNetworkAvailable(context)) {
            new AsyncTask<Void, Void, Void>() {


                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        GetCategoriesHTTP con = new GetCategoriesHTTP();
                        CategoriesParser parser = new CategoriesParser();
                        setCategories(parser.parseCategories(con.loadCategories()));
                        getDataEvent = new CategoryFragmentGetDataEvent(getCategories());
                    } catch (Exception e){
                        EventBus.getDefault().post(new NetworkConnectionProblemEvent());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (getDataEvent != null) {
                        EventBus.getDefault().post(getDataEvent);
                    }
                }
            }.execute();
        } else {
            NetworkConnectionProblemEvent networkEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkEvent);
        }
    }

    public void clearDatas(){
        if (this.categories != null){
            categories.clear();
            getDataEvent.clearDatas();
        }
    }

    public void loadSubCategories(final String parentID){
        clearDatas();
        if (this.isNetworkAvailable(context)) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    GetChildCategories con = new GetChildCategories();
                    CategoriesParser parser = new CategoriesParser();
                    GetProductsByCategoryID con2 = new GetProductsByCategoryID();
                    ProductsParser prodParser = new ProductsParser();
                    try {
                        String categoriesJSON = con.loadCategoriesForParent(parentID);
                        if (categoriesJSON != null){
                            setCategories(parser.parseCategories(categoriesJSON));
                            setProducts(prodParser.parseProducts(con2.loadProductByCategoryID(parentID)));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    CategoryExploreEvent event = new CategoryExploreEvent(getCategories(), getProducts());
                    EventBus.getDefault().post(event);
                }
            }.execute();
        } else {
            NetworkConnectionProblemEvent networkEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkEvent);
        }
    }



    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
