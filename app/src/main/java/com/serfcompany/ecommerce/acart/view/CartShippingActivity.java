package com.serfcompany.ecommerce.acart.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.SignInSuccessEvent;
import com.serfcompany.ecommerce.acart.model.user.Profile;
import com.serfcompany.ecommerce.acart.model.user.ShippingAddress;
import com.serfcompany.ecommerce.acart.presenter.SignInActivityPresenter;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 11.03.16.
 */
public class CartShippingActivity extends AbstractActivity{
    private static int LAYOUT = R.layout.test_cartshipping;
    private SignInActivityPresenter presenter;
    private Toolbar toolbar;

    private String username;
    private String password;

    private EditText orderNotes;

    private EditText shippingFirstName;
    private EditText shippingLastName;
    private EditText shippingCompany;
    private EditText shippingAddress1;
    private EditText shippingAddress2;
    private EditText shippingPostCode;
    private EditText shippingCity;
    private EditText shippingCountry;
    private EditText shippingState;
    private EditText shippingPhone;
    private EditText shippingEmail;

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
        if (username != null && password!= null){
            presenter = new SignInActivityPresenter(this);
            presenter.signIn(username, password);
        }

        shippingFirstName = (EditText) findViewById(R.id.shippingFirstName);
        shippingLastName = (EditText) findViewById(R.id.shippingLastName);
        shippingCompany = (EditText) findViewById(R.id.shippingCompanyName);
        shippingAddress1 = (EditText) findViewById(R.id.shippingAddress1Text);
        shippingAddress2 = (EditText) findViewById(R.id.shippingAddress2Text);
        shippingPostCode = (EditText) findViewById(R.id.shippingPostCodeText);
        shippingCity = (EditText) findViewById(R.id.shippingCityText);
        shippingCountry = (EditText) findViewById(R.id.shippingCountryText);
        shippingState = (EditText) findViewById(R.id.shippingStateText);
        shippingPhone = (EditText) findViewById(R.id.shippingPhoneText);
        shippingEmail = (EditText) findViewById(R.id.shippingEmailText);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.cartShippingDrawerLayout);
        initNavigationView(drawerLayout, null);
        initToolbar();
    }

    public void onEvent(SignInSuccessEvent event){
        if (event != null && event.getProfile()!=null){
            fillFields(event.getProfile());
        }
    }

    public void fillFields(Profile profile){
        ShippingAddress shipping = profile.getUser().getShippingAddress();
        if (shipping != null){
            shippingFirstName.setText(shipping.getShippingFirstName());
            shippingLastName.setText(shipping.getShippingLastName());
            shippingCompany.setText(shipping.getShippingCompany());
            shippingAddress1.setText(shipping.getShippingAddress1());
            shippingAddress2.setText(shipping.getShippingAddress2());
            shippingPostCode.setText(shipping.getShippingPostcode());
            shippingCity.setText(shipping.getShippingCity());
            shippingCountry.setText(shipping.getShippingCountry());
            shippingState.setText(shipping.getShippingState());

            shippingPhone.setText(profile.getUser().getBillingAddress().getBillingPhone());
            shippingEmail.setText(profile.getUser().getBillingAddress().getBillingEmail());

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

    private void initToolbar() {
                toolbar = (Toolbar) findViewById(R.id.cartShippingToolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}
