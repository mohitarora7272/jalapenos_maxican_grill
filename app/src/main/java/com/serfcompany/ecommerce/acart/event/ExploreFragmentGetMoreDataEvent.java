package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.List;

public class ExploreFragmentGetMoreDataEvent {
    List<Product> datas;

    public ExploreFragmentGetMoreDataEvent(List<Product> data){
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
