package com.serfcompany.ecommerce.acart.presenter.main;

import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.List;

/**
 * Created by serfcompany on 29.02.16.
 */
public interface IExploreFragmentPresenter {
    void loadDatas();
    List<Product> getData();
    void loadSearchData(String keyword);
    void clearDatas();
}
