package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.List;

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
