package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.cart.Cart;

public class CheckCartEvent {
    private Cart cart;

    public CheckCartEvent(Cart cart){
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }
}
