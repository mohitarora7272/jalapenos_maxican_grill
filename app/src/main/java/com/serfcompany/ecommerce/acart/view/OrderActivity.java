package com.serfcompany.ecommerce.acart.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.OrderByIdGetDataEvent;
import com.serfcompany.ecommerce.acart.model.orders.Item;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;
import com.serfcompany.ecommerce.acart.presenter.OrderActivityPresenter;
import com.serfcompany.ecommerce.acart.view.adapter.OrderItemListAdapter;

import java.text.DecimalFormat;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 02.03.16.
 */
public class OrderActivity extends AbstractActivity{

    private final int LAYOUT = R.layout.activity_order_details;
    SharedPreferences loginPrefs;
    TextView orderDate, orderPaymentMethod, orderID, orderStatus, orderPhone;
    TextView orderEmail, orderBillingAddress, orderDiscount, orderShippingAddress;
    TextView orderSubtotal, orderShipping, orderTotal;
    RecyclerView orderRecView;
    OrderActivityPresenter activityPresenter;
    Toolbar toolbar;
    FrameLayout loadingFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Intent intent = getIntent();
        String orderId = intent.getStringExtra(Constants.ORDER_ID);
        loginPrefs = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
        String username = loginPrefs.getString(Constants.USERNAME, null);
        String password = loginPrefs.getString(Constants.PASSWORD, null);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        loadingFrame = (FrameLayout) findViewById(R.id.progressBarFrame);
        orderDate = (TextView) findViewById(R.id.order_detailsDate);
        orderPaymentMethod = (TextView) findViewById(R.id.order_detailsPaymentMethod);
        orderID = (TextView) findViewById(R.id.order_detailsID);
        orderStatus = (TextView) findViewById(R.id.order_detailsStatus);
        orderEmail = (TextView) findViewById(R.id.order_detailsEmail);
        orderPhone = (TextView) findViewById(R.id.order_detailsPhone);
        orderBillingAddress = (TextView) findViewById(R.id.order_detailsBillingAddress);
        orderDiscount = (TextView) findViewById(R.id.order_detailsDiscount);
        orderShippingAddress = (TextView) findViewById(R.id.order_detailsShippingAddress);
        orderRecView = (RecyclerView) findViewById(R.id.order_detailRecyclerView);
        orderSubtotal = (TextView) findViewById(R.id.order_detailsSubtotal);
        orderShipping = (TextView) findViewById(R.id.order_detailsShippingCost);
        orderTotal = (TextView) findViewById(R.id.order_detailsTotalCost);

        if (orderId != null && username !=null && password != null){
            activityPresenter = new OrderActivityPresenter(this);
            activityPresenter.loadOrder(username, password, orderId);
        }

        initToolbar();
    }

    public void onEvent(OrderByIdGetDataEvent getDataEvent){
        if (getDataEvent != null && getDataEvent.getOrder() != null){
            loadingFrame.setVisibility(View.GONE);
            fillFields(getDataEvent.getOrder());
        }
    }

    private void fillFields(MyOrder order){
        String orderCurrency = order.getCurrency();

        setTitle("ORDER #"+String.valueOf(order.getOrderID()));

        orderDate.setText(order.getOrderDate());
        orderPaymentMethod.setText(order.getPaymentMethodTitle());
        orderID.setText("# "+String.valueOf(order.getOrderID()));
        orderStatus.setText(order.getStatus());
        orderEmail.setText(order.getBillingEmail());
        orderPhone.setText(order.getBillingPhone());
        orderBillingAddress.setText(order.getBillingAddress());
        orderShippingAddress.setText(order.getShippingAddress());

        List<Item> orderItems = order.getItems();
        orderRecView.setLayoutManager(new LinearLayoutManager(this));

        OrderItemListAdapter adapter = new OrderItemListAdapter(this, orderItems);
//        orderRecView.getLayoutParams().height = (orderItems.size()+1)*80;
        orderRecView.setAdapter(adapter);
        orderSubtotal.setText(Html.fromHtml(orderCurrency + String.valueOf(new DecimalFormat("0.00").format(order.getSubtotalExTax()+order.getDiscountTotal()))));
        orderShipping.setText(Html.fromHtml(orderCurrency + String.valueOf(new DecimalFormat("0.00").format(order.getShippingCost()))));
        orderDiscount.setText(Html.fromHtml(orderCurrency + String.valueOf(new DecimalFormat("0.00").format(order.getDiscountTotal()))));
        orderTotal.setText(Html.fromHtml(orderCurrency + String.valueOf(new DecimalFormat("0.00").format(order.getOrderTotal()))));
    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.orderToolbar);
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
