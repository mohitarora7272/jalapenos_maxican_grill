package com.serfcompany.ecommerce.acart.presenter.main;

import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetCategoriesHTTP;
import com.serfcompany.ecommerce.acart.event.CategoryFragmentGetDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.category.Category;
import com.serfcompany.ecommerce.acart.parser.CategoriesParser;
import com.serfcompany.ecommerce.acart.presenter.AbstractPresenter;
import com.serfcompany.ecommerce.acart.view.main.fragment.IFragmentView;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 29.02.16.
 */
public class CategoryFragmentPresenter extends AbstractPresenter {
    List<Category> categories;
    CategoryFragmentGetDataEvent getDataEvent;
    IFragmentView iFragmentView;

    public CategoryFragmentPresenter(IFragmentView iFragmentView){
        this.iFragmentView = iFragmentView;
    }

    public void loadDatas(){
        if (this.isNetworkAvailable(iFragmentView.getActivity().getBaseContext())) {
            new AsyncTask<Void, Void, Void>() {


                @Override
                protected Void doInBackground(Void... params) {
                    GetCategoriesHTTP con = new GetCategoriesHTTP();
                    CategoriesParser parser = new CategoriesParser();
                    setCategories(parser.parseCategories(con.loadCategories()));
                    getDataEvent = new CategoryFragmentGetDataEvent(getCategories());
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    EventBus.getDefault().post(getDataEvent);
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
