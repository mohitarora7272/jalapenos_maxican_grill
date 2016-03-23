package com.serfcompany.ecommerce.acart.view.profile.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.ProfileUpdateEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.event.SignInSuccessEvent;
import com.serfcompany.ecommerce.acart.model.user.BillingAddress;
import com.serfcompany.ecommerce.acart.model.user.Profile;
import com.serfcompany.ecommerce.acart.model.user.User;
import com.serfcompany.ecommerce.acart.presenter.SignInActivityPresenter;
import com.serfcompany.ecommerce.acart.presenter.profile.UpdateProfilePresenter;
import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.ChangePasswordActivity;
import com.serfcompany.ecommerce.acart.view.SignInActivity;
import com.squareup.picasso.Picasso;

import java.util.Map;

import de.greenrobot.event.EventBus;

public class MyProfileFragment extends AbstractTabFragment implements OnClickListener{
    private static final int LAYOUT = R.layout.fragment_my_profile;
    private Profile profile;
    private SharedPreferences loginPrefs;
    private SharedPreferences.Editor loginPrefsEditor;
    private SharedPreferences cartPrefs;
    private SharedPreferences.Editor cartPrefsEditor;
    EditText billingFirstName;
    EditText billingLastName;
    EditText billingCompany;
    EditText billingAddress1;
    EditText billingAddress2;
    EditText billingPostCode;
    EditText billingCity;
    EditText billingCountry;
    EditText billingState;
    EditText billingPhone;
    EditText billingEmail;
    AppCompatEditText profileFirstName;
    AppCompatEditText profileLastName;
    EditText profileNickname;
    EditText profileEmail;
    String username, password;
    FrameLayout loadingFrame;


    public static MyProfileFragment getInstance(Context context){
        Bundle args = new Bundle();
        MyProfileFragment fragment = new MyProfileFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle("My Profile");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //register
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        view = inflater.inflate(LAYOUT, container, false);
        loginPrefs = inflater.getContext()
                .getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE);
        loginPrefsEditor = loginPrefs.edit();
        cartPrefs = inflater.getContext()
                .getSharedPreferences(Constants.CART_PREFS, Context.MODE_PRIVATE);
        cartPrefsEditor = cartPrefs.edit();
        loadingFrame = (FrameLayout) view.findViewById(R.id.progressBarFrame);

        username = loginPrefs.getString(Constants.USERNAME, "");
        password = loginPrefs.getString(Constants.PASSWORD, "");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        billingFirstName = (EditText) view.findViewById(R.id.billingFirstName);
        billingLastName = (EditText) view.findViewById(R.id.billingLastName);
        billingCompany = (EditText) view.findViewById(R.id.billingCompanyName);
        billingAddress1 = (EditText) view.findViewById(R.id.billingAddressLine1);
        billingAddress2 = (EditText) view.findViewById(R.id.billingAddressLine2);
        billingPostCode = (EditText) view.findViewById(R.id.billingPostCode);
        billingCity = (EditText) view.findViewById(R.id.billingCity);
        billingCountry = (EditText) view.findViewById(R.id.billingCountry);
        billingState = (EditText) view.findViewById(R.id.billingState);
        billingPhone = (EditText) view.findViewById(R.id.billingPhone);
        billingEmail = (EditText) view.findViewById(R.id.billingEmail);

        if (profile == null) {
            Log.i("LOG", "Loading new profile data");
            SignInActivityPresenter presenter = new SignInActivityPresenter(getContext());
            presenter.signIn(username, password);
        } else {
            Log.i("LOG", "Displaying old profile data");
            loadingFrame.setVisibility(View.GONE);
            fillData(profile);
        }

    }

    @Override
    public void onDestroy() {
        getContext().getResources().flushLayoutCache();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout :
                cartPrefsEditor.clear();
                cartPrefsEditor.apply();
                loginPrefsEditor.clear();
                loginPrefsEditor.apply();
                Intent intent = new Intent();
                intent.setClass(getContext(), SignInActivity.class);
                getActivity().finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onEvent(SignInSuccessEvent event){
        if (event != null && event.getProfile()!=null){
            loadingFrame.setVisibility(View.GONE);
            profile = event.getProfile();
            fillData(event.getProfile());
        }
    }

    public void onEvent(ProfileUpdateEvent event){
        if (event != null && event.getResponse()!=null){
//            String toToast = event.getResponse().getReason();
//            Toast.makeText(getContext(), toToast, Toast.LENGTH_SHORT).show();
        }
    }

    public void onEvent(NetworkConnectionProblemEvent event){
        if (event != null){
            Toast.makeText(getContext(), "Connection problem. Try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);

    }

    public void setContext(Context context){
        this.context = context;
    }



    public void fillData(Profile profile){
        User user = profile.getUser();
        if (user != null) {

            ImageView profileAvatar = (ImageView) view.findViewById(R.id.profileAvatar);

            profileFirstName = (AppCompatEditText) view.findViewById(R.id.profileFirstName);
            profileLastName = (AppCompatEditText) view.findViewById(R.id.profileLastName);
            profileNickname = (EditText) view.findViewById(R.id.profileNickname);
            profileEmail = (EditText) view.findViewById(R.id.profileEmail);

            profileFirstName.setText(user.getFirstName());
            profileLastName.setText(user.getLastName());
            profileNickname.setText(user.getUserNickname());
            profileEmail.setText(user.getEmail());

            Button changePassword = (Button) view.findViewById(R.id.profileChangePassword);
            changePassword.setOnClickListener(this);
            Button updateProfile = (Button) view.findViewById(R.id.profileUpdateButton);
            updateProfile.setOnClickListener(this);
            Button updateBilling = (Button) view.findViewById(R.id.billingUpdateButton);
            updateBilling.setOnClickListener(this);
            BillingAddress billing = user.getBillingAddress();

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

            Picasso.with(context)
                    .load(user.getAvatar())
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.empty_photo)
                    .error(R.drawable.account)
                    .into(profileAvatar);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profileChangePassword :
                Intent intent = new Intent(context, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.profileUpdateButton :
                updateProfile();
                Toast.makeText(context, "Profile was updated", Toast.LENGTH_SHORT).show();
                break;
            case R.id.billingUpdateButton :
                updateBilling();
                Toast.makeText(context, "Billing was updated", Toast.LENGTH_SHORT).show();
            default: break;
        }
    }

    private void changePassword(){

    }

    private void updateProfile(Map<String, String> map){

    }

    private void updateProfile(){
        String firstName = getFromEditable(profileFirstName.getText());
        String lastName = getFromEditable(profileLastName.getText());
        String profileNickName = getFromEditable(profileNickname.getText());
        String profEmail = getFromEditable(profileEmail.getText());

        UpdateProfilePresenter updateProfilePresenter = new UpdateProfilePresenter(getContext());
        updateProfilePresenter.updateProfile(username, password, firstName, lastName, profileNickName, profEmail);
    }

    private void updateBilling(){
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

        UpdateProfilePresenter updateProfilePresenter = new UpdateProfilePresenter(getContext());
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
}
