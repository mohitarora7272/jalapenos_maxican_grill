package com.serfcompany.ecommerce.acart.view;

import android.content.SharedPreferences;
import android.graphics.AvoidXfermode;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.CheckCartEvent;
import com.serfcompany.ecommerce.acart.event.ClearCartEvent;
import com.serfcompany.ecommerce.acart.model.cart.Cart;
import com.serfcompany.ecommerce.acart.model.cart.CartItem;
import com.serfcompany.ecommerce.acart.presenter.CartActivityPresenter;
import com.serfcompany.ecommerce.acart.view.adapter.CartItemListAdapter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 07.03.16.
 */
public class CartActivity extends AbstractActivity{

    private static final int LAYOUT = R.layout.test_cart_preview;
    private Toolbar toolbar;
    private RecyclerView recView;
    private SharedPreferences loginPrefs;
    private SharedPreferences cartPrefs;
    private DrawerLayout drawerLayout;
    private SharedPreferences.Editor cartPrefsEditor;
    private TextView total, subtotal, discount, tax;
    private EditText couponCode;
    private FrameLayout noItemsLayout;
    private FrameLayout couponStatusLayout;
    private CartActivityPresenter presenter;
    CartItemListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }


        drawerLayout = (DrawerLayout) findViewById(R.id.cart_drawer_layout);
        cartPrefs = getSharedPreferences(Constants.CART_PREFS, MODE_PRIVATE);
        loginPrefs = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
        Map<String, String> products = (Map<String, String>) cartPrefs.getAll();
        products.remove(Constants.CURRENCY);

        presenter = new CartActivityPresenter(this);
        presenter.checkCart(loginPrefs.getString(Constants.USERNAME, null),
                loginPrefs.getString(Constants.PASSWORD, null),
                products, null);

        Button clearCart = (Button) findViewById(R.id.cartClearButton);
        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearCart();
            }
        });


        initToolbar();
        initNavigationView(drawerLayout, null);
    }


    public void onEvent(CheckCartEvent checkCartEvent){
        if (checkCartEvent != null && checkCartEvent.getCart() != null){
            fillFields(checkCartEvent.getCart());
            adapter.notifyDataSetChanged();
        }
    }

    public void onEvent(ClearCartEvent clearCartEvent){
        if (clearCartEvent != null){
            fillFields(clearCartEvent.getCart());
        }
    }

    public void fillFields(Cart cart){
        List<CartItem> cartItems = cart.getCart();
        recView = (RecyclerView) findViewById(R.id.cartItemRecyclerView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.getLayoutParams().height = (cartItems.size()+1)*80;
        adapter = new CartItemListAdapter(this, cart.getCart());
        recView.setAdapter(adapter);

        total = (TextView) findViewById(R.id.cartCartTotal);
        subtotal = (TextView) findViewById(R.id.cartSubtotal);
        discount = (TextView) findViewById(R.id.cartDiscount);
        tax = (TextView) findViewById(R.id.cartTotalTax);

        String currency = cartPrefs.getString(Constants.CURRENCY, "");
        total.setText(Html.fromHtml(currency+" "+cart.getGrandTotal()));
        subtotal.setText(Html.fromHtml(currency + " " + cart.getCartSubtotal()));
        discount.setText(Html.fromHtml(currency+" "+cart.getDiscount()));
        tax.setText(Html.fromHtml(currency+" "+cart.getCartTaxTotal()));
    }



    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.cartToolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
