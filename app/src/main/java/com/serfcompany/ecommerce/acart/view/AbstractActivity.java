package com.serfcompany.ecommerce.acart.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;

/**
 * Created by serfcompany on 01.03.16.
 */
public abstract class AbstractActivity extends AppCompatActivity {



    public void initNavigationView(final DrawerLayout drawerLayout, final ViewPager viewPager) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        SharedPreferences cartPrefs = getSharedPreferences(Constants.CART_PREFS, MODE_PRIVATE);
        if (!cartPrefs.getAll().isEmpty()){
            MenuItem cart = (MenuItem) navigationView.getMenu().findItem(R.id.actionCartItem);
            cart.setTitle("Cart +");
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        drawerLayout.closeDrawers();
                        switch (menuItem.getItemId()) {
                            case R.id.actionExploreItem:
                                showMainActivity(viewPager);
                                break;
                            case R.id.actionProfileItem:
                                showProfileActivity(viewPager);
                                break;
                            case R.id.actionCartItem:
//                                Intent intent = new Intent();
//                                intent.setClass(getBaseContext(), CartActivity.class);
//                                startActivity(intent);
                        }
                        return false;
                    }
                });
    }


    public void showMainActivity(ViewPager viewPager){

        if (viewPager == null){
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), MainActivity.class);
            startActivity(intent);
        } else {
            switch (viewPager.getId()) {
                case R.id.viewPager:
                    viewPager.setCurrentItem(0);
                    return;
                case R.id.profile_viewPager:
                    Intent intent = new Intent();
                    intent.setClass(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    return;

                default:
                    Intent defaultIntent = new Intent();
                    defaultIntent.setClass(getBaseContext(), MainActivity.class);
                    startActivity(defaultIntent);
                    return;
            }
        }
    }

    public void showProfileActivity(ViewPager viewPager){
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
        boolean authorized = sharedPreferences.getBoolean(Constants.AUTHORISED, false);
        boolean rememberMe = sharedPreferences.getBoolean(Constants.REMEMBER, false);
        String username = sharedPreferences.getString(Constants.USERNAME, "");
        String password = sharedPreferences.getString(Constants.PASSWORD, "");



        if (rememberMe || authorized){
            if (viewPager == null){
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), ProfileActivity.class);
                intent.putExtra(Constants.USERNAME, username);
                intent.putExtra(Constants.PASSWORD, password);
                startActivity(intent);
            } else {
                switch (viewPager.getId()){
                    case R.id.profile_viewPager :
                        viewPager.setCurrentItem(0);
                        return;
                    default:
                        Intent intent = new Intent();
                        intent.setClass(getBaseContext(), ProfileActivity.class);
                        intent.putExtra(Constants.USERNAME, username);
                        intent.putExtra(Constants.PASSWORD, password);
                        startActivity(intent);
                        break;
                }
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), SignInActivity.class);
            startActivity(intent);
        }
    }



    public void showCartActivity(){}
}
