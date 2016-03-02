package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.category.Category;
import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.List;

/**
 * Created by serfcompany on 01.03.16.
 */
public class FeaturedFragmentGetDataEvent {
    List<Product> datas;

    public FeaturedFragmentGetDataEvent(List<Product> datas){
        this.datas = datas;
    }

    public List<Product> getDatas() {
        return datas;
    }

    public void clearDatas(){
        this.datas.clear();
    }
}
