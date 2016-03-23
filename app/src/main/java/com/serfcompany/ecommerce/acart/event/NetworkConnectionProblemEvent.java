package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.Collections;
import java.util.List;

public class NetworkConnectionProblemEvent {
    List<Product> datas;

    public NetworkConnectionProblemEvent(){
        this.datas = Collections.emptyList();
    }

    public List<Product> getDatas() {
        return datas;
    }
}
