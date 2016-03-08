package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.cart.Cart;

/**
 * Created by serfcompany on 08.03.16.
 */
public class ClearCartEvent {
    private Cart cart;

    public ClearCartEvent(){
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }
}
