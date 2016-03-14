package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.PaymentRedirectHTTP;
import com.serfcompany.ecommerce.acart.HTTPHolders.PlaceAnOrderHTTP;
import com.serfcompany.ecommerce.acart.Utils;
import com.serfcompany.ecommerce.acart.event.CheckCartEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.event.PaymentRedirectEvent;
import com.serfcompany.ecommerce.acart.event.PlaceAnOrderEvent;
import com.serfcompany.ecommerce.acart.model.cart.Cart;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;
import com.serfcompany.ecommerce.acart.model.response.Redirect;
import com.serfcompany.ecommerce.acart.parser.OrderParser;
import com.serfcompany.ecommerce.acart.parser.RedirectParser;

import java.io.IOException;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 14.03.16.
 */
public class CheckoutActivityPresenter extends AbstractPresenter{
    Context context;
    MyOrder order;
    Redirect redirect;
    PlaceAnOrderEvent placeOrderEvent;
    PaymentRedirectEvent paymentRedirectEvent;

    public CheckoutActivityPresenter(Context context){this.context = context;}

    public void placeAnOrder(final String username, final String password, final Map<String, String> products,
                             final Map<String, String> coupons, final String paymentID, final String orderNotes){
        if (!isNetworkAvailable(context)){
            NetworkConnectionProblemEvent networkConnectionProblemEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkConnectionProblemEvent);
        } else {

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try{
                        PlaceAnOrderHTTP con = new PlaceAnOrderHTTP();
                        OrderParser parser = new OrderParser();
                        setOrder(parser.parse(con.placeOrder(username, password, products, coupons, paymentID, orderNotes)));
                        } catch (IOException e){
                            NetworkConnectionProblemEvent networkConnectionProblemEvent = new NetworkConnectionProblemEvent();
                            EventBus.getDefault().post(networkConnectionProblemEvent);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        placeOrderEvent = new PlaceAnOrderEvent(getOrder());
                        EventBus.getDefault().post(placeOrderEvent);
                    }
                }.execute();
        }
    }

    public void processPayment(final String orderKey, final String orderID, final String paymentID){
        if (!isNetworkAvailable(context)){
            NetworkConnectionProblemEvent networkConnectionProblemEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkConnectionProblemEvent);
        } else {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    PaymentRedirectHTTP con = new PaymentRedirectHTTP();
                    RedirectParser parser = new RedirectParser();
                    try {
                        setRedirect(parser.parse(con.proceedPayment(orderKey, orderID, paymentID)));
                    } catch (IOException e) {
                        e.printStackTrace();
                        NetworkConnectionProblemEvent networkConnectionProblemEvent = new NetworkConnectionProblemEvent();
                        EventBus.getDefault().post(networkConnectionProblemEvent);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    paymentRedirectEvent = new PaymentRedirectEvent(getRedirect());
                    EventBus.getDefault().post(paymentRedirectEvent);
                }
            }.execute();
        }
    }

    public PlaceAnOrderEvent getPlaceOrderEvent() {
        return placeOrderEvent;
    }

    public void setOrder(MyOrder order) {
        this.order = order;
    }

    public Redirect getRedirect() {
        return redirect;
    }

    public void setRedirect(Redirect redirect) {
        this.redirect = redirect;
    }

    public MyOrder getOrder() {

        return order;
    }
}
