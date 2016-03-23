package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.orders.MyOrder;

public class OrderByIdGetDataEvent {
    private MyOrder order;

    public OrderByIdGetDataEvent(MyOrder order){
        this.order = order;
    }

    public MyOrder getOrder() {
        return order;
    }
}
