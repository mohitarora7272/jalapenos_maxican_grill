package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.orders.MyOrder;

import java.util.List;

public class MyOrdersFragmentGetDataEvent {

    private List<MyOrder> myOrders;

    public MyOrdersFragmentGetDataEvent(List<MyOrder> myOrders){
        this.myOrders = myOrders;
    }

    public List<MyOrder> getMyOrders() {
        return myOrders;
    }

    public void clearDatas(){
        myOrders.clear();
    }
}
