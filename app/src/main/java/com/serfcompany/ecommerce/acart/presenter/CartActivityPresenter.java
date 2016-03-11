package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.HTTPHolders.CheckCartHTTP;
import com.serfcompany.ecommerce.acart.event.CheckCartEvent;
import com.serfcompany.ecommerce.acart.event.ClearCartEvent;
import com.serfcompany.ecommerce.acart.model.cart.Cart;
import com.serfcompany.ecommerce.acart.parser.CartParser;

import java.util.HashMap;
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

    public void checkCart(final String username, final String password, final Map<String, String> idQuant, final Map<String, String> coupons){
        if (isNetworkAvailable(this.context)){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    CheckCartHTTP con = new CheckCartHTTP();
                    CartParser parser = new CartParser();
//                    Map<String, String> qou = new HashMap<String, String>();
//                    qou.put("2016map", "2016map");
//                    qou.put("2016foo", "2016foo");
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

    public void clearCart(){
        SharedPreferences cartPreferences = context.getSharedPreferences(Constants.CART_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cartPreferences.edit();
        editor.clear();
        editor.apply();
        ClearCartEvent cartEvent = new ClearCartEvent();
        EventBus.getDefault().post(cartEvent);
    }

    public void loadProduct(){

    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
