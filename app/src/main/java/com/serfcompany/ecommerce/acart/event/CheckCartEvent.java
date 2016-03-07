package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.cart.Cart;

/**
 * Created by serfcompany on 07.03.16.
 */
public class CheckCartEvent {
    private Cart cart;

    public CheckCartEvent(Cart cart){
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }
}
