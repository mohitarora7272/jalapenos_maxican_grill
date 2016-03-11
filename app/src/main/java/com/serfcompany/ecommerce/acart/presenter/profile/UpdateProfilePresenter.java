package com.serfcompany.ecommerce.acart.presenter.profile;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.SignInRequestHTTP;
import com.serfcompany.ecommerce.acart.HTTPHolders.UpdateBillingHTTP;
import com.serfcompany.ecommerce.acart.event.BillingUpdateEvent;
import com.serfcompany.ecommerce.acart.event.NetworkConnectionProblemEvent;
import com.serfcompany.ecommerce.acart.model.response.Response;
import com.serfcompany.ecommerce.acart.model.user.Profile;
import com.serfcompany.ecommerce.acart.parser.ProfileParser;
import com.serfcompany.ecommerce.acart.parser.ResponseParser;
import com.serfcompany.ecommerce.acart.presenter.AbstractPresenter;

import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 11.03.16.
 */
public class UpdateProfilePresenter extends AbstractPresenter{

    Context context;
    Profile profile;
    Response response;
    NetworkConnectionProblemEvent connectionProblemEvent;
    BillingUpdateEvent updateEvent;

    public UpdateProfilePresenter(Context context){
        this.context = context;
    }

    public void updateBilling(final String username, final String password, final String firstName,
                              final String lastName, final String companyName, final String address1,
                              final String address2, final String cityName, final String postcode,
                              final String stateName, final String country, final String phone, final String email){
        if (!isNetworkAvailable(context)){
            connectionProblemEvent = new NetworkConnectionProblemEvent();
            EventBus.getDefault().post(connectionProblemEvent);
        } else {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    UpdateBillingHTTP con = new UpdateBillingHTTP();
                    ResponseParser parser = new ResponseParser();
                    try {
                        setResponse(parser.parse(con.updateBilling(username, password, firstName, lastName,
                                companyName, address1, address2, cityName, postcode,
                                stateName, country, phone, email)));
                        SignInRequestHTTP signInCon = new SignInRequestHTTP();
                        ProfileParser profileParser = new ProfileParser();
                        setProfile(profileParser.parseProfile(
                                signInCon.userLogin(username, password, null)));

                    } catch (IOException e) {
                        connectionProblemEvent = new NetworkConnectionProblemEvent();
                        EventBus.getDefault().post(connectionProblemEvent);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (getResponse() != null && getProfile() != null){
                        updateEvent = new BillingUpdateEvent(getResponse(), getProfile());
                        EventBus.getDefault().post(updateEvent);
                    }
                }
            }.execute();
        }
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
