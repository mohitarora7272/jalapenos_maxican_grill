package com.serfcompany.ecommerce.acart.view.profile.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.main.fragment.CategoryFragment;
import com.serfcompany.ecommerce.acart.view.main.fragment.ExploreFragment;
import com.serfcompany.ecommerce.acart.view.main.fragment.FeaturedFragment;
import com.serfcompany.ecommerce.acart.view.profile.fragment.MyNotificationsFragment;
import com.serfcompany.ecommerce.acart.view.profile.fragment.MyOrdersFragment;
import com.serfcompany.ecommerce.acart.view.profile.fragment.MyProfileFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by serfcompany on 01.03.16.
 */
public class TabProfileAdapter extends FragmentPagerAdapter {
    private Map<Integer, AbstractTabFragment> tabs;
    Context context;

    public TabProfileAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap(context);
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, MyProfileFragment.getInstance(context));
        tabs.put(1, MyOrdersFragment.getInstance(context));
        tabs.put(2, MyNotificationsFragment.getInstance(context));
    }
}
