package com.serfcompany.ecommerce.acart.view.profile.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.SignInSuccessEvent;
import com.serfcompany.ecommerce.acart.model.user.BillingAddress;
import com.serfcompany.ecommerce.acart.model.user.Profile;
import com.serfcompany.ecommerce.acart.model.user.User;
import com.serfcompany.ecommerce.acart.presenter.SignInActivityPresenter;
import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.SignInActivity;
import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 01.03.16.
 */
public class MyProfileFragment extends AbstractTabFragment{
    private static final int LAYOUT = R.layout.fragment_my_profile;
    private Profile profile;
    private SharedPreferences loginPrefs;
    private SharedPreferences.Editor loginPrefsEditor;

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
        view = inflater.inflate(LAYOUT, container, false);
        loginPrefs = inflater.getContext()
                .getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE);
        loginPrefsEditor = loginPrefs.edit();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        //register
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        Button updateProfile = (Button) view.findViewById(R.id.profileUpdateButton);
        Button updateBilling = (Button) view.findViewById(R.id.billingUpdateButton);
        if (profile == null) {
            Log.i("LOG", "Loading new profile data");
            SignInActivityPresenter presenter = new SignInActivityPresenter(getContext());
            presenter.signIn(loginPrefs.getString(Constants.USERNAME, ""), loginPrefs.getString(Constants.PASSWORD, ""));
        } else {
            Log.i("LOG", "Displaying old profile data");
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
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
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
            profile = event.getProfile();
            fillData(event.getProfile());
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

            AppCompatEditText profileFirstName = (AppCompatEditText) view.findViewById(R.id.profileFirstName);
            AppCompatEditText profileLastName = (AppCompatEditText) view.findViewById(R.id.profileLastName);
            EditText profileNickname = (EditText) view.findViewById(R.id.profileNickname);
            EditText profileEmail = (EditText) view.findViewById(R.id.profileEmail);

            EditText billingFirstName = (EditText) view.findViewById(R.id.billingFirstName);
            EditText billingLastName = (EditText) view.findViewById(R.id.billingLastName);
            EditText billingCompany = (EditText) view.findViewById(R.id.billingCompanyName);
            EditText billingAddress1 = (EditText) view.findViewById(R.id.billingAddressLine1);
            EditText billingAddress2 = (EditText) view.findViewById(R.id.billingAddressLine2);
            EditText billingPostCode = (EditText) view.findViewById(R.id.billingPostCode);
            EditText billingCity = (EditText) view.findViewById(R.id.billingCity);
            EditText billingCountry = (EditText) view.findViewById(R.id.billingCountry);
            EditText billingState = (EditText) view.findViewById(R.id.billingState);
            EditText billingPhone = (EditText) view.findViewById(R.id.billingPhone);
            EditText billingEmail = (EditText) view.findViewById(R.id.billingEmail);

            profileFirstName.setText(user.getFirstName());
            profileLastName.setText(user.getLastName());
            profileNickname.setText(user.getUserNickname());
            profileEmail.setText(user.getEmail());

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
}
