package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.ChangePasswordHTTP;
import com.serfcompany.ecommerce.acart.event.ChangePasswordEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.response.Response;
import com.serfcompany.ecommerce.acart.parser.ResponseParser;

import java.io.IOException;

import de.greenrobot.event.EventBus;

public class ChangePasswordPresenter extends AbstractPresenter{
    private Response response;
    private Context context;
    private NetworkConnectionProblemEvent networkProblemEvent;
    private ChangePasswordEvent changePasswordEvent;

    public ChangePasswordPresenter(Context context){
        this.context = context;
    }

    public void changePassword(final String username, final String currentPassword, final String newPassword){
        if (!isNetworkAvailable(context)){
            networkProblemEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(networkProblemEvent);
        } else {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    ChangePasswordHTTP con = new ChangePasswordHTTP();
                    ResponseParser parser = new ResponseParser();
                    try {
                        setResponse(parser.parse(con.changePassword(username, currentPassword, newPassword)));
                    } catch (IOException e) {
                        e.printStackTrace();
                        networkProblemEvent = new NetworkConnectionProblemEvent();
                        EventBus.getDefault().post(networkProblemEvent);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    changePasswordEvent = new ChangePasswordEvent(getResponse());
                    EventBus.getDefault().post(changePasswordEvent);
                }
            }.execute();


        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
