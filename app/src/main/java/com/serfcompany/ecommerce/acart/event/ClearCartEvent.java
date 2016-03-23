package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.cart.Cart;

public class ClearCartEvent {
    private Cart cart;

    public ClearCartEvent(){
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }
}
