package com.serfcompany.ecommerce.acart.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.ProfileUpdateEvent;
import com.serfcompany.ecommerce.acart.event.SignInSuccessEvent;
import com.serfcompany.ecommerce.acart.model.user.BillingAddress;
import com.serfcompany.ecommerce.acart.model.user.Profile;
import com.serfcompany.ecommerce.acart.presenter.SignInActivityPresenter;
import com.serfcompany.ecommerce.acart.presenter.profile.UpdateProfilePresenter;

import de.greenrobot.event.EventBus;

public class CartBillingActivity extends AbstractActivity{
    private static int LAYOUT = R.layout.activity_cartbilling;
    private SignInActivityPresenter presenter;
    private Toolbar toolbar;
    private String username;
    private String password;
    private EditText billingFirstName;
    private EditText billingLastName;
    private EditText billingCompany;
    private EditText billingAddress1;
    private EditText billingAddress2;
    private EditText billingPostCode;
    private EditText billingCity;
    private EditText billingCountry;
    private EditText billingState;
    private EditText billingPhone;
    private EditText billingEmail;
    FrameLayout loadingFrame;

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

        Button nextToShipping = (Button) findViewById(R.id.cartBillingNextButton);
        nextToShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBilling();
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), CartShippingActivity.class);
                startActivity(intent);
            }
        });
        loadingFrame = (FrameLayout) findViewById(R.id.progressBarFrame);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.cartBillingDrawerLayout);
        initNavigationView(drawerLayout, null);
        initToolbar();
    }

    public void onEvent(SignInSuccessEvent signInEvent){
        if (signInEvent!=null && signInEvent.getProfile()!=null){
            fillFields(signInEvent.getProfile());
        }
    }

    public void onEvent(ProfileUpdateEvent event){
        if (event != null && event.getResponse().getStatus()!= null){
            if (!event.getResponse().getStatus().equals("0")){
                Toast.makeText(this, "Try again later. Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fillFields(Profile profile){
        if (profile != null && profile.getUser().getBillingAddress() != null) {
            loadingFrame.setVisibility(View.GONE);
            BillingAddress billing = profile.getUser().getBillingAddress();
            billingFirstName = (EditText) findViewById(R.id.billingFirstName);
            billingLastName = (EditText) findViewById(R.id.billingLastName);
            billingCompany = (EditText) findViewById(R.id.billingCompanyName);
            billingAddress1 = (EditText) findViewById(R.id.billingAddress1Text);
            billingAddress2 = (EditText) findViewById(R.id.billingAddress2Text);
            billingPostCode = (EditText) findViewById(R.id.billingPostCodeText);
            billingCity = (EditText) findViewById(R.id.billingCityText);
            billingCountry = (EditText) findViewById(R.id.billingCountryText);
            billingState = (EditText) findViewById(R.id.billingStateText);
            billingPhone = (EditText) findViewById(R.id.billingPhoneText);
            billingEmail = (EditText) findViewById(R.id.billingEmailText);

            billingFirstName.setText(billing.getBillingFirstName());
            billingLastName.setText(billing.getBillingLastName());
            billingCompany.setText(billing.getBillingCompany());
            billingAddress1.setText(billing.getBillingAddress1());
            billingAddress2.setText(billing.getBillingAddress2());
            billingPostCode.setText(billing.getBillingPostcode());
            billingCity.setText(billing.getBillingCity());
            billingCountry.setText(billing.getBillingCountry());
            billingState.setText(billing.getBillingState());
            billingPhone.setText(billing.getBillingPhone());
            billingEmail.setText(billing.getBillingEmail());
        }
    }

    public void updateBilling(){
        String firstName = getFromEditable(billingFirstName.getText());
        String lastName = getFromEditable(billingLastName.getText());
        String companyName = getFromEditable(billingCompany.getText());
        String address1 = getFromEditable(billingAddress1.getText());
        String address2 = getFromEditable(billingAddress2.getText());
        String cityName = getFromEditable(billingCity.getText());
        String postcode = getFromEditable(billingPostCode.getText());
        String stateName = getFromEditable(billingState.getText());
        String country = getFromEditable(billingCountry.getText());
        String phone = getFromEditable(billingPhone.getText());
        String email = getFromEditable(billingEmail.getText());

        UpdateProfilePresenter updateProfilePresenter = new UpdateProfilePresenter(this);
        updateProfilePresenter.updateBilling(username, password, firstName, lastName, companyName, address1,
                address2, cityName, postcode, stateName, country, phone, email);
    }

    private String getFromEditable(Editable editable){
        if (editable == null){
            return "";
        } else{
            return String.valueOf(editable);
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
