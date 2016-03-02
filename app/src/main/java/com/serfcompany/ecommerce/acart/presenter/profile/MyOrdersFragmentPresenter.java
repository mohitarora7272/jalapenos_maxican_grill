package com.serfcompany.ecommerce.acart.presenter.profile;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetMyOrdersHTTP;
import com.serfcompany.ecommerce.acart.event.MyOrdersFragmentGetDataEvent;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;
import com.serfcompany.ecommerce.acart.parser.OrdersParser;
import com.serfcompany.ecommerce.acart.view.profile.fragment.MyOrdersFragment;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 02.03.16.
 */
public class MyOrdersFragmentPresenter {

    private MyOrdersFragment fragmentView;
    MyOrdersFragmentGetDataEvent getDataEvent;
    private List<MyOrder> myOrders;

    public MyOrdersFragmentPresenter(MyOrdersFragment fragmentView){
        this.fragmentView = fragmentView;
    }

    public void loadDatas(String username, String password, String filter){
        clearDatas();
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                GetMyOrdersHTTP con = new GetMyOrdersHTTP();
                OrdersParser parser = new OrdersParser();

                setMyOrders(parser.parseOrders(con.getMyOrders(params[0], params[1], params[2])));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (getMyOrders()!=null){
                    getDataEvent = new MyOrdersFragmentGetDataEvent(getMyOrders());
                    EventBus.getDefault().post(getDataEvent);
                }
            }
        }.execute(username, password, filter);
    }

    public void clearDatas() {
        if (myOrders!=null) {
            myOrders.clear();
            getDataEvent.clearDatas();
        }
    }

    public List<MyOrder> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<MyOrder> myOrders) {
        this.myOrders = myOrders;
    }
}
