package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.orders.MyOrder;

public class PlaceAnOrderEvent {
    private MyOrder order;

    public PlaceAnOrderEvent(MyOrder order){
        this.order = order;
    }

    public MyOrder getOrder() {
        return order;
    }
}
