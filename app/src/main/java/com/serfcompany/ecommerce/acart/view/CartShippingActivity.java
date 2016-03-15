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
import com.serfcompany.ecommerce.acart.model.user.ShippingAddress;
import com.serfcompany.ecommerce.acart.presenter.SignInActivityPresenter;
import com.serfcompany.ecommerce.acart.presenter.profile.UpdateProfilePresenter;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 11.03.16.
 */
public class CartShippingActivity extends AbstractActivity{
    private static int LAYOUT = R.layout.activity_cartshipping;
    private SignInActivityPresenter signInPresenter;
    private UpdateProfilePresenter updateProfilePresenter;
    private Toolbar toolbar;

    private String username;
    private String password;

    private Profile myProfile;

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
            signInPresenter = new SignInActivityPresenter(this);
            signInPresenter.signIn(username, password);
        }

        orderNotes = (EditText) findViewById(R.id.orderNoteText);

        shippingFirstName = (EditText) findViewById(R.id.shippingFirstName);
        shippingLastName = (EditText) findViewById(R.id.shippingLastName);
        shippingCompany = (EditText) findViewById(R.id.shippingCompanyName);
        shippingAddress1 = (EditText) findViewById(R.id.shippingAddress1Text);
        shippingAddress2 = (EditText) findViewById(R.id.shippingAddress2Text);
        shippingPostCode = (EditText) findViewById(R.id.shippingPostCodeText);
        shippingCity = (EditText) findViewById(R.id.shippingCityText);
        shippingCountry = (EditText) findViewById(R.id.shippingCountryText);
        shippingState = (EditText) findViewById(R.id.shippingStateText);
        loadingFrame = (FrameLayout) findViewById(R.id.progressBarFrame);

        Button sameAsBilling = (Button) findViewById(R.id.shippingSameAsBillingButton);
        sameAsBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillSameAsBilling(myProfile);
            }
        });

        Button nextButton = (Button) findViewById(R.id.shippingNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateShipping();

                Intent intent = new Intent();
                intent.setClass(getBaseContext(), CartCheckoutActivity.class);
                intent.putExtra(Constants.ORDER_NOTES, getFromEditable(orderNotes.getText()));
                startActivity(intent);
            }
        });

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.cartShippingDrawerLayout);
        initNavigationView(drawerLayout, null);
        initToolbar();
    }

    public void onEvent(SignInSuccessEvent event){
        if (event != null && event.getProfile()!=null){
            loadingFrame.setVisibility(View.GONE);
            fillFields(event.getProfile());
            myProfile = event.getProfile();
        }
    }

    public void onEvent(ProfileUpdateEvent event){
        if (event != null && event.getResponse().getStatus()!=null){
            if (event.getResponse().getStatus().equals("0")){
            } else {
                Toast.makeText(this, "Try again later. Something went wrong", Toast.LENGTH_SHORT).show();
            }
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
        }
    }

    public void fillSameAsBilling(Profile profile){
        ShippingAddress shipping = profile.getUser().getShippingAddress();
        BillingAddress billing = profile.getUser().getBillingAddress();
        if (shipping != null){
            shippingFirstName.setText(billing.getBillingFirstName());
            shippingLastName.setText(billing.getBillingLastName());
            shippingCompany.setText(billing.getBillingCompany());
            shippingAddress1.setText(billing.getBillingAddress1());
            shippingAddress2.setText(billing.getBillingAddress2());
            shippingPostCode.setText(billing.getBillingPostcode());
            shippingCity.setText(billing.getBillingCity());
            shippingCountry.setText(billing.getBillingCountry());
            shippingState.setText(billing.getBillingState());
        }
    }

    public void updateShipping(){
        String firstName = getFromEditable(shippingFirstName.getText());
        String lastName = getFromEditable(shippingLastName.getText());
        String companyName = getFromEditable(shippingCompany.getText());
        String address1 = getFromEditable(shippingAddress1.getText());
        String address2 = getFromEditable(shippingAddress2.getText());
        String cityName = getFromEditable(shippingCity.getText());
        String postcode = getFromEditable(shippingPostCode.getText());
        String stateName = getFromEditable(shippingState.getText());
        String country = getFromEditable(shippingCountry.getText());

        UpdateProfilePresenter updateProfilePresenter = new UpdateProfilePresenter(this);
        updateProfilePresenter.updateShipping(username, password, firstName, lastName, companyName, address1,
                address2, cityName, postcode, stateName, country);
    }

    private String getFromEditable(Editable editable){
        if (editable == null){
            return "";
        } else{
            return String.valueOf(editable);
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
