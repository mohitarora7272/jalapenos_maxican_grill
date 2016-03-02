package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.product.Product;

/**
 * Created by serfcompany on 29.02.16.
 */
public class ProductByIdEvent {
    Product product;

    public ProductByIdEvent(Product product){
        this.product = product;
    }

    public Product getProduct(){
        return product;
    }
}
