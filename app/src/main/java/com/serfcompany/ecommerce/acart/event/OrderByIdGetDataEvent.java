package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.orders.MyOrder;

/**
 * Created by serfcompany on 02.03.16.
 */
public class OrderByIdGetDataEvent {
    private MyOrder order;

    public OrderByIdGetDataEvent(MyOrder order){
        this.order = order;
    }

    public MyOrder getOrder() {
        return order;
    }
}
