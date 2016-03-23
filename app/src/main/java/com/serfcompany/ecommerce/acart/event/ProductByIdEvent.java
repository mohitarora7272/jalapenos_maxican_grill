package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.product.Product;

public class ProductByIdEvent {
    Product product;

    public ProductByIdEvent(Product product){
        this.product = product;
    }

    public Product getProduct(){
        return product;
    }
}
