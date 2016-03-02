package com.serfcompany.ecommerce.acart.presenter.profile;

import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetNotificationsHTTP;
import com.serfcompany.ecommerce.acart.event.MyNotificationsGetDataEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.notification.NotificationData;
import com.serfcompany.ecommerce.acart.parser.NotificationParser;
import com.serfcompany.ecommerce.acart.presenter.AbstractPresenter;
import com.serfcompany.ecommerce.acart.view.profile.fragment.MyNotificationsFragment;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 02.03.16.
 */
public class MyNotificationFragmentPresenter extends AbstractPresenter{

    private MyNotificationsFragment myNotificationsFragment;
    private MyNotificationsGetDataEvent getDataEvent;
    private List<NotificationData> datas;

    public MyNotificationFragmentPresenter(MyNotificationsFragment myNotificationsFragment){
        this.myNotificationsFragment = myNotificationsFragment;
    }

    public void loadDatas(int page, int postPerPage, String username, String password){
        clearDatas();
        if (this.isNetworkAvailable(myNotificationsFragment.getActivity().getBaseContext())){
            new AsyncTask<String, Void, Void>() {
                @Override
                protected Void doInBackground(String... params) {
                    GetNotificationsHTTP con = new GetNotificationsHTTP();
                    NotificationParser parser = new NotificationParser();
                    setDatas(parser.parse(con.loadNotifications(params[0], params[1],params[2],params[3])));
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (getDatas() != null){
                        getDataEvent = new MyNotificationsGetDataEvent(getDatas());
                        EventBus.getDefault().post(getDataEvent);
                    }
                }
            }.execute(String.valueOf(page), String.valueOf(postPerPage), username, password);
        } else {
            NetworkConnectionProblemEvent networkEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkEvent);
        }
    }

    public List<NotificationData> getDatas() {
        return datas;
    }

    public void clearDatas() {
        if (datas!=null) {
            datas.clear();
            getDataEvent.clearDatas();
        }
    }

    public void setDatas(List<NotificationData> datas) {
        this.datas = datas;
    }
}
