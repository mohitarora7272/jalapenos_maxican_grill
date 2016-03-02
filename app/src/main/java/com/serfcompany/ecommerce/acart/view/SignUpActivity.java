package com.serfcompany.ecommerce.acart.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.Validators;
import com.serfcompany.ecommerce.acart.event.SignUpEvent;
import com.serfcompany.ecommerce.acart.presenter.SignUpActivityPresenter;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 02.03.16.
 */
public class SignUpActivity extends AbstractActivity{
    private final static int LAYOUT = R.layout.activity_sign_up;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private EditText loginEditText;
    private EditText emailEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText passwordEditText;
    private EditText passwordRetypeEditText;
    private Button signUpButton;
    private TextInputLayout passwordLayout;
    private TextInputLayout emailLayout;
    private TextInputLayout loginLayout;
    private TextInputLayout firstNameLayout;
    private TextInputLayout lastNameLayout;
    private String login, firstName, lastName, email, password;

    private SignUpActivityPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.sign_up_drawer_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.signUpProgressBarFrame);
        loginEditText = (EditText) findViewById(R.id.signUpLoginEditTextField);
        emailEditText = (EditText) findViewById(R.id.signUpEmailEditTextField);
        firstNameEditText = (EditText) findViewById(R.id.signUpFirstNameEditTextField);
        lastNameEditText = (EditText) findViewById(R.id.signUpLastNameEditTextField);
        passwordEditText = (EditText) findViewById(R.id.signUpPasswordEditTextField);
        passwordRetypeEditText = (EditText) findViewById(R.id.signUpRetypePasswordEditTextField);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        passwordLayout = (TextInputLayout) findViewById(R.id.signUpPasswordTextInputLayout);
        emailLayout = (TextInputLayout) findViewById(R.id.signUpEmailTextInputLayout);
        loginLayout = (TextInputLayout) findViewById(R.id.signUpLoginTextInputLayout);
        firstNameLayout = (TextInputLayout) findViewById(R.id.signUpFirstNameTextInputLayout);
        lastNameLayout = (TextInputLayout) findViewById(R.id.signUpLastNameTextInputLayout);

        signUpButton.setOnClickListener(new OnSignUpClick());
        initToolbar();
        initNavigationView(drawerLayout, null);
    }

    private boolean isFirstNameValid(){
        String firstName = firstNameEditText.getText().toString();
        if (firstName.isEmpty()){
            firstNameEditText.requestFocus();
            firstNameLayout.setErrorEnabled(true);
            firstNameLayout.setError("Please enter your first name");
            return false;
        } if (!Validators.isValidNames(firstName)){
            firstNameEditText.requestFocus();
            firstNameLayout.setErrorEnabled(true);
            firstNameLayout.setError("First name cannot contain special characters");
            return false;
        }
        else {
            firstNameEditText.clearFocus();
            firstNameLayout.setErrorEnabled(false);
            return true;
        }
    }
    private boolean isLastNameValid(){
        String lastName = lastNameEditText.getText().toString();
        if (lastName.isEmpty()){
            lastNameEditText.requestFocus();
            lastNameLayout.setErrorEnabled(true);
            lastNameLayout.setError("Please enter your last name");
            return false;
        } if (!Validators.isValidNames(lastName)){
            lastNameEditText.requestFocus();
            lastNameLayout.setErrorEnabled(true);
            lastNameLayout.setError("Last name cannot contain special characters");
            return false;
        }
        else {
            lastNameEditText.clearFocus();
            lastNameLayout.setErrorEnabled(false);
            return true;
        }
    }
    private boolean isLoginValid(){
        String login = loginEditText.getText().toString();
        if (login.isEmpty()){
            loginEditText.requestFocus();
            loginLayout.setErrorEnabled(true);
            loginLayout.setError("Please enter your login");
            return false;
        } if (!Validators.isValidLoginName(login)){
            loginEditText.requestFocus();
            loginLayout.setErrorEnabled(true);
            loginLayout.setError("Login must be from 3 to 15 characters. And cannot contain special characters");
            return false;
        } else {
            loginLayout.setErrorEnabled(false);
            loginEditText.clearFocus();
            return true;
        }
    }
    private boolean isEmailValid(){
        String email = emailEditText.getText().toString();
        if (email.isEmpty()){
            emailEditText.requestFocus();
            emailLayout.setErrorEnabled(true);
            emailLayout.setError("Please enter your email");
            return false;
        } else{
            if (!Validators.isValidEmailAddress(email)){
                emailEditText.requestFocus();
                emailLayout.setErrorEnabled(true);
                emailLayout.setError("E-mail invalid. We recommend to use gmail");
                return false;
            }else{
                emailLayout.setErrorEnabled(false);
                emailEditText.clearFocus();
                return true;
            }
        }

    }
    private boolean isPasswordValid(){
        String password = passwordEditText.getText().toString();
        String retypePassword = passwordRetypeEditText.getText().toString();
        if (!password.equals(retypePassword)){
            passwordEditText.requestFocus();
            passwordEditText.setText(null);
            passwordRetypeEditText.setText(null);
            passwordLayout.setErrorEnabled(true);
            passwordLayout.setError("Passwords don't match");
            return false;
        } else{
            if (password.length() < 5){
                passwordEditText.requestFocus();
                passwordEditText.setText(null);
                passwordRetypeEditText.setText(null);
                passwordLayout.setErrorEnabled(true);
                passwordLayout.setError("Password must be more than 5 characters");
                return false;
            }else {
                passwordLayout.setErrorEnabled(false);
                passwordEditText.clearFocus();
                return true;
            }
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

    public void onEvent(SignUpEvent signUpEvent){
        if (signUpEvent != null && signUpEvent.getStatusCode() != 4){
            int statusCode = signUpEvent.getStatusCode();
            if (statusCode == 2){
                emailEditText.requestFocus();
                emailLayout.setErrorEnabled(true);
                emailLayout.setError("This email already in use");
            } if (statusCode == 1){
                loginEditText.requestFocus();
                loginLayout.setErrorEnabled(true);
                loginLayout.setError("Such login already exists. Choose another one");
            } if (statusCode == 0) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), SignInActivity.class);
                intent.putExtra(Constants.SIGN_UP_SUCCESS, true);
                startActivity(intent);
            }
        }
    }

    private class OnSignUpClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            login  = loginEditText.getText().toString();
            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();
            firstName = firstNameEditText.getText().toString();
            lastName = lastNameEditText.getText().toString();
            String deviceId = String.valueOf(Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID));

            if (isLoginValid() && isEmailValid() && isFirstNameValid()
                    && isLastNameValid() && isPasswordValid()) {
                signUpPresenter = new SignUpActivityPresenter(getBaseContext());
                signUpPresenter.signUp(login, email, firstName, lastName, password, deviceId);
            }
        }
    }
}
