package com.serfcompany.ecommerce.acart.view.profile.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.MyNotificationsGetDataEvent;
import com.serfcompany.ecommerce.acart.presenter.profile.MyNotificationFragmentPresenter;
import com.serfcompany.ecommerce.acart.view.AbstractTabFragment;
import com.serfcompany.ecommerce.acart.view.main.fragment.IFragmentView;
import com.serfcompany.ecommerce.acart.view.profile.adapter.NotificationListAdapter;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 02.03.16.
 */
public class MyNotificationsFragment extends AbstractTabFragment implements IFragmentView {

    private final int LAYOUT = R.layout.fragment_notifications;

    private SharedPreferences loginPrefs;
    private NotificationListAdapter mAdapter;
    private MyNotificationFragmentPresenter fragmentPresenter;
    private RecyclerView rView;

    public static MyNotificationsFragment getInstance(Context context){
        Bundle args = new Bundle();
        MyNotificationsFragment fragment = new MyNotificationsFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle("Notifications");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        rView = (RecyclerView) view.findViewById(R.id.notificationsRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        loginPrefs = getContext().getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE);
        String username = loginPrefs.getString(Constants.USERNAME, "");
        String password = loginPrefs.getString(Constants.PASSWORD, "");

        rView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (mAdapter == null){
            fragmentPresenter = new MyNotificationFragmentPresenter(this);
            fragmentPresenter.loadDatas(1, 20, username, password);
            mAdapter = new NotificationListAdapter(getContext(), fragmentPresenter);
        }
        rView.setAdapter(mAdapter);
    }

    public void onEvent(MyNotificationsGetDataEvent dataEvent){
        if (dataEvent != null && dataEvent.getMyNotifications() != null){
            mAdapter.setNotificationList(dataEvent.getMyNotifications());
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setContext(Context context) {
        this.context = context;
    }

}
