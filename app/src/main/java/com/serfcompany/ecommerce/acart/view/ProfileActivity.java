package com.serfcompany.ecommerce.acart.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.view.profile.adapter.TabProfileAdapter;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 01.03.16.
 */
public class ProfileActivity extends AbstractActivity{

    private static final int LAYOUT = R.layout.activity_profile;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);


        drawerLayout = (DrawerLayout) findViewById(R.id.profile_drawer_layout);
        initNavigationView(drawerLayout, viewPager);
        initToolbar();
        initTabs();
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBarDrawerToggle toggle
                = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.profile_viewPager);
        TabProfileAdapter adapter
                = new TabProfileAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout
                = (TabLayout) findViewById(R.id.profile_tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
