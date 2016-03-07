package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.CheckCartHTTP;
import com.serfcompany.ecommerce.acart.event.CheckCartEvent;
import com.serfcompany.ecommerce.acart.model.cart.Cart;
import com.serfcompany.ecommerce.acart.parser.CartParser;

import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 07.03.16.
 */
public class CartActivityPresenter extends AbstractPresenter{

    Context context;
    Cart cart;
    CheckCartEvent cartEvent;

    public CartActivityPresenter(Context context){
        this.context = context;
    }

    public void checkCart(final String username, final String password, final Map<Integer, Integer> idQuant, final Map<String, String> coupons){
        if (isNetworkAvailable(this.context)){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    CheckCartHTTP con = new CheckCartHTTP();
                    CartParser parser = new CartParser();
                    setCart(parser.parse(con.loadCart(username, password, idQuant, coupons)));
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    cartEvent = new CheckCartEvent(getCart());
                    EventBus.getDefault().post(cartEvent);
                }
            }.execute();
        }
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
