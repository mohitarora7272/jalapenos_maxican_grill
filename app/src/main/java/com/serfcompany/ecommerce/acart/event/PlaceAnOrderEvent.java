package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.orders.MyOrder;

/**
 * Created by serfcompany on 14.03.16.
 */
public class PlaceAnOrderEvent {
    private MyOrder order;

    public PlaceAnOrderEvent(MyOrder order){
        this.order = order;
    }

    public MyOrder getOrder() {
        return order;
    }
}
