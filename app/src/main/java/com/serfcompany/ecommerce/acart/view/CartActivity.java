package com.serfcompany.ecommerce.acart.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.R;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 07.03.16.
 */
public class CartActivity extends AbstractActivity{

    private static final int LAYOUT = R.layout.activity_cart;
    Toolbar toolbar;
    TextView total, subtotal, discount, tax;
    EditText couponCode;
    FrameLayout noItemsLayout;
    FrameLayout couponStatusLayout;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState, persistentState);
        setContentView(LAYOUT);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }


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
}
