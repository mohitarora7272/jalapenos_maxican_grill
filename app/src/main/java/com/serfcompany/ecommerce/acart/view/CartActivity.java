package com.serfcompany.ecommerce.acart.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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
import com.serfcompany.ecommerce.acart.model.cart.Item;
import com.serfcompany.ecommerce.acart.presenter.CartActivityPresenter;
import com.serfcompany.ecommerce.acart.view.adapter.CartItemListAdapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private FrameLayout notAuthorized;
    private FrameLayout cartContentLayout;
    private FrameLayout couponStatusLayout;
    private CartActivityPresenter presenter;
    private SharedPreferences couponPrefs;
    private SharedPreferences.Editor couponPrefsEditor;
    Map<String, String> products;
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
        products = (Map<String, String>) cartPrefs.getAll();
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
        if (checkCartEvent != null){
            fillFields(checkCartEvent.getCart());

        }
    }

    public void onEvent(ClearCartEvent clearCartEvent){
        if (clearCartEvent != null){
            fillFields(clearCartEvent.getCart());
        }
    }

    public void fillFields(Cart cart){

        notAuthorized = (FrameLayout) findViewById(R.id.cartNotAuthorisedFrame);
        cartContentLayout = (FrameLayout) findViewById(R.id.cartContentFrame);
        noItemsLayout = (FrameLayout) findViewById(R.id.cartNoProductsInCart);

        if (!loginPrefs.getBoolean(Constants.AUTHORISED, false)){
            notAuthorized.setVisibility(View.VISIBLE);
            noItemsLayout.setVisibility(View.GONE);
            cartContentLayout.setVisibility(View.GONE);
        } else {
            if (cart.getCart() == null || cart.getCart().size() == 0){
                noItemsLayout.setVisibility(View.VISIBLE);
                cartContentLayout.setVisibility(View.GONE);
            } else {
                notAuthorized.setVisibility(View.GONE);
                noItemsLayout.setVisibility(View.GONE);
                cartContentLayout.setVisibility(View.VISIBLE);
                List<Item> cartItems = cart.getCart();
                recView = (RecyclerView) findViewById(R.id.cartItemRecyclerView);
                recView.setLayoutManager(new LinearLayoutManager(this));
                recView.getLayoutParams().height = (cartItems.size()+1)*80;
                adapter = new CartItemListAdapter(this, cart.getCart());
                adapter.notifyDataSetChanged();
                recView.setAdapter(adapter);
                total = (TextView) findViewById(R.id.cartCartTotal);
                subtotal = (TextView) findViewById(R.id.cartSubtotal);
                discount = (TextView) findViewById(R.id.cartDiscount);
                tax = (TextView) findViewById(R.id.cartTotalTax);

                String currency = cartPrefs.getString(Constants.CURRENCY, "");
                total.setText(Html.fromHtml(currency + " " + cart.getGrandTotal()));
                subtotal.setText(Html.fromHtml(currency + " " + cart.getCartSubtotal()));
                discount.setText(Html.fromHtml(currency+" "+cart.getDiscount()));
                tax.setText(Html.fromHtml(currency + " " + cart.getCartTaxTotal()));

//                if (cart.getCoupon().getAppliedCoupon()!=null){
//                    FrameLayout couponsFrame = (FrameLayout) findViewById(R.id.cartCouponNotificationsFrame);
//                    couponsFrame.setVisibility(View.VISIBLE);
//
//                }
            }
        }

        TextView signIn = (TextView) findViewById(R.id.cartPleaseSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

        Button next = (Button) findViewById(R.id.cartNextToBillingButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), CartBillingActivity.class);
                startActivity(intent);
            }
        });

        final EditText couponText = (EditText) findViewById(R.id.cartCouponCode);
        Button applyCoupon = (Button) findViewById(R.id.cartApplyCouponButton);
        applyCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> coupon = new HashMap<String, String>();
                coupon.put(String.valueOf(couponText.getText().hashCode()), String.valueOf(couponText.getText()));
                presenter.checkCart(loginPrefs.getString(Constants.USERNAME, null),
                        loginPrefs.getString(Constants.PASSWORD, null), products, coupon);
                couponPrefs = getSharedPreferences(Constants.COUPON_PREFS, MODE_PRIVATE);
//                Set<String> couponSet = couponPrefs.getStringSet("coupons", Collections.<String>emptySet());
//                couponSet.add(String.valueOf(couponText.getText()));
                couponPrefsEditor = couponPrefs.edit();
                couponPrefsEditor.clear();
                couponPrefsEditor.putString("coupon", String.valueOf(couponText.getText()));
                couponPrefsEditor.apply();
            }
        });

        Button launchButton = (Button) findViewById(R.id.cartLaunchProducts);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
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

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Override
    protected void onResume() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        super.onResume();
    }
}
