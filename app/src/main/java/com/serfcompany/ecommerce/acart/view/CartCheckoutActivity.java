package com.serfcompany.ecommerce.acart.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.CheckCartEvent;
import com.serfcompany.ecommerce.acart.event.PaymentRedirectEvent;
import com.serfcompany.ecommerce.acart.event.PlaceAnOrderEvent;
import com.serfcompany.ecommerce.acart.model.cart.Cart;
import com.serfcompany.ecommerce.acart.model.cart.Item;
import com.serfcompany.ecommerce.acart.model.cart.PaymentMethod;
import com.serfcompany.ecommerce.acart.presenter.CartActivityPresenter;
import com.serfcompany.ecommerce.acart.presenter.CheckoutActivityPresenter;
import com.serfcompany.ecommerce.acart.view.adapter.CartItemListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 14.03.16.
 */
public class CartCheckoutActivity extends AbstractActivity{
    private static int LAYOUT = R.layout.activity_checkout;
    private CartActivityPresenter cartPresenter;
    private CheckoutActivityPresenter checkoutPresenter;
    private SharedPreferences loginPrefs;
    private SharedPreferences cartPrefs;
    private Toolbar toolbar;
    private String username;
    private String password;
    private RecyclerView recView;
    CartItemListAdapter adapter;
    private TextView total, subtotal, discount, tax, shippingCost;
    Map<String, String> coupons;
    Map<String, String> products;
    Spinner paymentSpinner;
    List<PaymentMethod> paymentMethods;
    String paymentMethodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        SharedPreferences loginPrefs = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
        username = loginPrefs.getString(Constants.USERNAME, null);
        password = loginPrefs.getString(Constants.PASSWORD, null);
        cartPrefs = getSharedPreferences(Constants.CART_PREFS, MODE_PRIVATE);

        final String orderNote = getIntent().getStringExtra(Constants.ORDER_NOTES);

        products = (Map<String, String>) cartPrefs.getAll();
        products.remove(Constants.CURRENCY);

        SharedPreferences couponPrefs = getSharedPreferences(Constants.COUPON_PREFS, MODE_PRIVATE);
        coupons = new HashMap<>();
        coupons.put("coupon", couponPrefs.getString("coupon", null));
        cartPresenter = new CartActivityPresenter(this);
        cartPresenter.checkCart(username, password, products, coupons);

        Button buttonCheckout = (Button) findViewById(R.id.checkoutButton);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethodID = paymentMethods.get((int) paymentSpinner.getSelectedItemId()).getId();
                checkoutPresenter = new CheckoutActivityPresenter(getBaseContext());
                checkoutPresenter.placeAnOrder(username, password, products, coupons, paymentMethodID, orderNote);
            }
        });
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.checkoutDrawerLayout);
        initNavigationView(drawerLayout, null);
        initToolbar();
    }

    public void onEvent(PlaceAnOrderEvent event){
        if (event != null && event.getOrder() != null){
            checkoutPresenter.processPayment(event.getOrder().getOrderKey(), String.valueOf(event.getOrder().getOrderID()), event.getOrder().getPaymentMethodId());
        }
    }

    public void onEvent(PaymentRedirectEvent event){
        if (event != null && event.getRedirect() != null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("REDIRECT", event.getRedirect().getRedirect());
            startActivity(intent);
            SharedPreferences.Editor cartPrefsEditor = cartPrefs.edit();
            cartPrefsEditor.clear();
            cartPrefsEditor.apply();
            this.finish();
        }
    }

    public void onEvent(CheckCartEvent checkCartEvent){
        if (checkCartEvent != null && checkCartEvent.getCart()!=null){
            fillFields(checkCartEvent.getCart());
        }
    }

    public void fillFields(Cart cart){

        List<Item> cartItems = cart.getCart();
        recView = (RecyclerView) findViewById(R.id.checkoutItemRecyclerView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.getLayoutParams().height = (cartItems.size()+1)*80;
        adapter = new CartItemListAdapter(this, cart.getCart());
        adapter.notifyDataSetChanged();
        recView.setAdapter(adapter);
        total = (TextView) findViewById(R.id.checkoutTotal);
        subtotal = (TextView) findViewById(R.id.checkoutSubtotal);
        discount = (TextView) findViewById(R.id.checkoutDiscount);
        shippingCost = (TextView) findViewById(R.id.checkoutShipping);
//        tax = (TextView) findViewById(R.id.cartTotalTax);

        String currency = cartPrefs.getString(Constants.CURRENCY, "");
        total.setText(Html.fromHtml(currency + " " + cart.getGrandTotal()));
        subtotal.setText(Html.fromHtml(currency + " " + cart.getCartSubtotal()));
        discount.setText(Html.fromHtml(currency+" "+cart.getDiscount()));
        shippingCost.setText(Html.fromHtml(currency + " " + cart.getShippingCost()));

        if (cart.getPaymentMethod()!=null){

            ArrayList<Map<String, String>> paymentsList = new ArrayList<>();
            Map<String, String> payments;
            paymentMethods = new ArrayList<>();
            for (PaymentMethod method : cart.getPaymentMethod()){
                paymentMethods.add(method);
                payments = new HashMap<>();
                payments.put("Title", method.getTitle());
                int i = String.valueOf(method.getDescription()).length() > 100 ? 100 : method.getDescription().length();
                payments.put("Description", method.getDescription().substring(0, i)+"...");
                payments.put("ID", method.getId());
                paymentsList.add(payments);
            }

            SimpleAdapter adapter = new SimpleAdapter(this, paymentsList, android.R.layout.simple_list_item_2,
                            new String[] {"Title", "Description"},
                            new int[] {android.R.id.text1,android.R.id.text2 });
            paymentSpinner = (Spinner) findViewById(R.id.paymentSpinner);
            paymentSpinner.setAdapter(adapter);
        }
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.cartCheckoutToolbar);
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
