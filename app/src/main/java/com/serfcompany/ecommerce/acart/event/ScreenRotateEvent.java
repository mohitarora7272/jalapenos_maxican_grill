package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.List;

public class ScreenRotateEvent {
    List<Product> datas;

    public ScreenRotateEvent(List<Product> data){
        this.datas = data;
    }

    public List<Product> getDatas() {
        return datas;
    }
}
