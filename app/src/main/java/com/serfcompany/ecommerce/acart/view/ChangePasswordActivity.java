package com.serfcompany.ecommerce.acart.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.ChangePasswordEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.presenter.ChangePasswordPresenter;

import de.greenrobot.event.EventBus;

public class ChangePasswordActivity extends AbstractActivity{
    private int LAYOUT = R.layout.activity_change_password;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private EditText loginField;
    private EditText currentPassword;
    private EditText newPassword;
    FrameLayout internetProblemLayout;
    private ChangePasswordPresenter changePasswordPresenter;
    private TextInputLayout usernameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }



        loginField = (EditText) findViewById(R.id.loginEditTextField);
        currentPassword = (EditText) findViewById(R.id.passwordEditTextField);
        newPassword = (EditText) findViewById(R.id.newPasswordEditTextField);
        usernameLayout = (TextInputLayout) findViewById(R.id.loginTextInputLayout);
        internetProblemLayout = (FrameLayout) findViewById(R.id.connectionProblemLayout);
        Button changePasswordButton = (Button) findViewById(R.id.changePasswordButton);

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginField.getText().toString();
                String password = currentPassword.getText().toString();
                String newPass = newPassword.getText().toString();
                changePasswordPresenter = new ChangePasswordPresenter(getBaseContext());
                changePasswordPresenter.changePassword(username, password, newPass);
            }
        });

        initToolbar();
        drawerLayout = (DrawerLayout) findViewById(R.id.sign_in_drawer_layout);
        initNavigationView(drawerLayout, null);
    }

    public void onEvent(ChangePasswordEvent event){
        if (event != null && event.getResponse() != null){
            internetProblemLayout.setVisibility(View.GONE);
            if (event.getResponse().getStatus().equals("0")){
                usernameLayout.setErrorEnabled(false);
                SharedPreferences loginPrefs = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor loginPrefsEditor = loginPrefs.edit();
                loginPrefsEditor.putString(Constants.PASSWORD, newPassword.getText().toString());
                loginPrefsEditor.apply();
                Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Password wasn't changed", Toast.LENGTH_SHORT).show();
                usernameLayout.setErrorEnabled(true);
                usernameLayout.setError(event.getResponse().getReason());
            }
        }
    }

    public void onEvent(NetworkConnectionProblemEvent event){
        if (event != null){
            internetProblemLayout.setVisibility(View.VISIBLE);
        }
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.sign_in_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
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
