package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetOrderByIdHTTP;
import com.serfcompany.ecommerce.acart.event.OrderByIdGetDataEvent;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;
import com.serfcompany.ecommerce.acart.parser.SingleOrderParser;

import java.util.List;

import de.greenrobot.event.EventBus;

public class OrderActivityPresenter extends AbstractPresenter{
    OrderByIdGetDataEvent getDataEvent;
    Context context;
    MyOrder datas;

    public OrderActivityPresenter(Context context){
        this.context = context;
    }

    public void loadOrder(String username, String password, String orderID){
        if (this.isNetworkAvailable(context)){
            new AsyncTask<String, Void, Void>() {
                @Override
                protected Void doInBackground(String... params) {
                    GetOrderByIdHTTP con = new GetOrderByIdHTTP();
                    SingleOrderParser parser = new SingleOrderParser();
                    setDatas(parser.parse(con.getOrderById(params[0], params[1], params[2])));
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    getDataEvent = new OrderByIdGetDataEvent(getDatas());
                    EventBus.getDefault().post(getDataEvent);
                }
            }.execute(username, password, orderID);
        }

    }

    public MyOrder getDatas() {
        return datas;
    }

    public void setDatas(MyOrder datas) {
        this.datas = datas;
    }
}
