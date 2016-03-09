package com.serfcompany.ecommerce.acart.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.SignInSuccessEvent;
import com.serfcompany.ecommerce.acart.model.user.BillingAddress;
import com.serfcompany.ecommerce.acart.model.user.Profile;
import com.serfcompany.ecommerce.acart.presenter.CartBillingActivityPresenter;
import com.serfcompany.ecommerce.acart.presenter.SignInActivityPresenter;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 09.03.16.
 */
public class CartBillingActivity extends AbstractActivity{
    private static int LAYOUT = R.layout.test_cartbilling;
    SignInActivityPresenter presenter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        SharedPreferences loginPrefs = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
        String username = loginPrefs.getString(Constants.USERNAME, null);
        String password = loginPrefs.getString(Constants.PASSWORD, null);
        if (username != null && password!= null){
            presenter = new SignInActivityPresenter(this);
            presenter.signIn(username, password);
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.cartBillingDrawerLayout);
        initNavigationView(drawerLayout, null);
        initToolbar();
    }

    public void onEvent(SignInSuccessEvent signInEvent){
        if (signInEvent!=null && signInEvent.getProfile()!=null){
            fillFields(signInEvent.getProfile());
        }
    }

    private void fillFields(Profile profile){
        if (profile != null && profile.getUser().getBillingAddress() != null) {
            BillingAddress billing = profile.getUser().getBillingAddress();
            EditText billingFirstName = (EditText) findViewById(R.id.billingFirstName);
            EditText billingLastName = (EditText) findViewById(R.id.billingLastName);
            EditText billingCompanyName = (EditText) findViewById(R.id.billingCompanyName);
            EditText billingAddress1 = (EditText) findViewById(R.id.billingAddress1Text);
            EditText billingAddress2 = (EditText) findViewById(R.id.billingAddress2Text);
            EditText billingPostCode = (EditText) findViewById(R.id.billingPostCodeText);
            EditText billingCity = (EditText) findViewById(R.id.billingCityText);
            EditText billingCountry = (EditText) findViewById(R.id.billingCountryText);
            EditText billingState = (EditText) findViewById(R.id.billingStateText);
            EditText billingPhone = (EditText) findViewById(R.id.billingPhoneText);
            EditText billingEmail = (EditText) findViewById(R.id.billingEmailText);

            billingFirstName.setText(billing.getBillingFirstName());
            billingLastName.setText(billing.getBillingLastName());
            billingCompanyName.setText(billing.getBillingCompany());
            billingAddress1.setText(billing.getBillingAddress1());
            billingAddress2.setText(billing.getBillingAddress2());
            billingPostCode.setText(billing.getBillingPostcode());
            billingCity.setText(billing.getBillingCity());
            billingCountry.setText(billing.getBillingCountry());
            billingState.setText(Html.fromHtml("(" + billing.getBillingStateCode() + ") " + billing.getBillingState()));
            billingPhone.setText(billing.getBillingPhone());
            billingEmail.setText(billing.getBillingEmail());
        }
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.cartBillingToolbar);
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
