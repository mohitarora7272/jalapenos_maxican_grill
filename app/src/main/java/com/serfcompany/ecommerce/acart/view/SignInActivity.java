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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.SignInFailureEvent;
import com.serfcompany.ecommerce.acart.event.SignInSuccessEvent;
import com.serfcompany.ecommerce.acart.presenter.SignInActivityPresenter;

import de.greenrobot.event.EventBus;

public class SignInActivity extends AbstractActivity{

    private final static int LAYOUT = R.layout.activity_sign_in;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SignInActivityPresenter presenter;

    private EditText loginField;
    private EditText passwordField;
    private TextInputLayout loginInputLayout;
    private CheckBox rememberMeCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        loginField = (EditText) findViewById(R.id.loginEditTextField);
        passwordField = (EditText) findViewById(R.id.passwordEditTextField);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.rememberMeCheckBox);
        loginInputLayout = (TextInputLayout) findViewById(R.id.loginTextInputLayout);
        TextView signUp = (TextView) findViewById(R.id.signUpTextLink);
        Button signInButton = (Button) findViewById(R.id.signInButton);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginField.getText().toString();
                String password = passwordField.getText().toString();
                presenter = new SignInActivityPresenter(getApplication().getBaseContext());
                presenter.signIn(login, password);
            }
        });

        Intent intent = getIntent();
        if (intent.getBooleanExtra(Constants.SIGN_UP_SUCCESS, false)){
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.signInFrameLayout);
            frameLayout.setVisibility(View.VISIBLE);
        }

        initToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.sign_in_drawer_layout);
        initNavigationView(drawerLayout, null);
    }

    public void onEvent(SignInSuccessEvent successEvent){
        if (successEvent != null && successEvent.getProfile() != null){
            SharedPreferences preferences = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Constants.USERNAME, successEvent.getProfile().getUser().getUserLogin());
            editor.putString(Constants.PASSWORD, successEvent.getPassword());
            editor.putBoolean(Constants.AUTHORISED, true);
            if (rememberMeCheckBox.isChecked()){
                editor.putBoolean(Constants.REMEMBER, true);
            }
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), ProfileActivity.class);
            startActivity(intent);
        }
    }

    public void onEvent(SignInFailureEvent failureEvent){
        if (failureEvent != null && !failureEvent.getReason().equals(null)){
            loginInputLayout.setErrorEnabled(true);
            loginInputLayout.setError(failureEvent.getReason());
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
