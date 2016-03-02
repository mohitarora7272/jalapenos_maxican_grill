package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.List;

/**
 * Created by serfcompany on 29.02.16.
 */
public class ExploreFragmentGetDatasEvent {
    List<Product> datas;

    public ExploreFragmentGetDatasEvent(List<Product> data){
        this.datas = data;
    }

    public List<Product> getDatas() {
        return datas;
    }

    public void clearDatas(){
        if (datas!=null) {
            this.datas.clear();
        }
    }
}
