package com.serfcompany.ecommerce.acart.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.view.main.adapter.TabMainAdapter;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 29.02.16.
 */
public class MainActivity extends AbstractActivity{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        super.initNavigationView(drawerLayout, viewPager);
        initToolbar();
        initTabs();
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBarDrawerToggle toggle
                = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        return true;
    }

    private void initTabs() {
        TabMainAdapter adapter
                = new TabMainAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout
                = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }




//    @Override
//    protected void onDestroy() {
//        SharedPreferences cartPrefs = getSharedPreferences(Constants.CART_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor cartEditor = cartPrefs.edit();
//        cartEditor.clear();
//        cartEditor.apply();
//
//        SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        if (!sharedPreferences.getBoolean(Constants.REMEMBER, false)){
//            editor.clear();
//            editor.apply();
//            Log.i("LOG", "Login preferences deleted");
//        }
//        Log.i("LOG", "Cart preferences deleted");
//
//        getBaseContext().getResources().flushLayoutCache();
//        super.onDestroy();
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
