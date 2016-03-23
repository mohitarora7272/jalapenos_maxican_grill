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
import android.widget.FrameLayout;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

public class CartCheckoutActivity extends AbstractActivity{
    private static int LAYOUT = R.layout.activity_checkout;
    private CheckoutActivityPresenter checkoutPresenter;
    private SharedPreferences cartPrefs;
    private String username;
    private String password;
    private Map<String, String> coupons;
    private Map<String, String> products;
    private Spinner paymentSpinner;
    private List<PaymentMethod> paymentMethods;
    private String paymentMethodID;
    private FrameLayout loadingFrame;

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

        loadingFrame = (FrameLayout) findViewById(R.id.progressBarFrame);

        SharedPreferences couponPrefs = getSharedPreferences(Constants.COUPON_PREFS, MODE_PRIVATE);
        coupons = new HashMap<>();
        coupons.put("coupon", couponPrefs.getString("coupon", null));
        CartActivityPresenter cartPresenter = new CartActivityPresenter(this);
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

    /**
     * Processing place-an-order response and calling payment-redirect event
     * @param event
     */
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
            loadingFrame.setVisibility(View.GONE);
            fillFields(checkCartEvent.getCart());
        }
    }

    public void fillFields(Cart cart){

        RecyclerView recView = (RecyclerView) findViewById(R.id.checkoutItemRecyclerView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        CartItemListAdapter adapter1 = new CartItemListAdapter(this, cart.getCart());
        adapter1.notifyDataSetChanged();
        recView.setAdapter(adapter1);
        TextView total = (TextView) findViewById(R.id.checkoutTotal);
        TextView subtotal = (TextView) findViewById(R.id.checkoutSubtotal);
        TextView discount = (TextView) findViewById(R.id.checkoutDiscount);
        TextView shippingCost = (TextView) findViewById(R.id.checkoutShipping);

        String currency = cartPrefs.getString(Constants.CURRENCY, "");
        total.setText(Html.fromHtml(currency + " " + String.valueOf(new DecimalFormat("0.00").format(cart.getGrandTotal()))));
        subtotal.setText(Html.fromHtml(currency + " " + String.valueOf(new DecimalFormat("0.00").format(cart.getCartSubtotal()))));
        discount.setText(Html.fromHtml(currency + " " + String.valueOf(new DecimalFormat("0.00").format(cart.getDiscount()))));
        shippingCost.setText(Html.fromHtml(currency + " " + String.valueOf(new DecimalFormat("0.00").format(cart.getShippingCost()))));

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.cartCheckoutToolbar);
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
