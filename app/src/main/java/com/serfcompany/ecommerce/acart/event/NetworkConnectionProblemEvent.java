package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.Collections;
import java.util.List;

/**
 * Created by serfcompany on 01.03.16.
 */
public class NetworkConnectionProblemEvent {
    List<Product> datas;

    public NetworkConnectionProblemEvent(){
        this.datas = Collections.emptyList();
    }

    public List<Product> getDatas() {
        return datas;
    }
}
