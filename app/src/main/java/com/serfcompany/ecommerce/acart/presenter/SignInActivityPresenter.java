package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.HTTPHolders.SignInRequestHTTP;
import com.serfcompany.ecommerce.acart.event.SignInFailureEvent;
import com.serfcompany.ecommerce.acart.event.SignInSuccessEvent;
import com.serfcompany.ecommerce.acart.model.user.Profile;
import com.serfcompany.ecommerce.acart.parser.ProfileParser;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 01.03.16.
 */
public class SignInActivityPresenter {
    SignInSuccessEvent successEvent;
    SignInFailureEvent failureEvent;
    Profile profile;
    Context context;

    public SignInActivityPresenter(Context context){
        this.context = context;
    }


    private void loadProfile(String username, final String password, String deviceID){
        clearDatas();
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                SignInRequestHTTP con = new SignInRequestHTTP();
                ProfileParser parser = new ProfileParser();
                Log.i("LOG", params[0]+" "+params[1]);
                setProfile(parser.parseProfile(con.userLogin(params[0], params[1], params[2])));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (getProfile() == null){
                    failureEvent = new SignInFailureEvent("Invalid login/email/password");
                    EventBus.getDefault().post(failureEvent);
                    Log.i("LOG", "SignIn failure");
                } else {
                    successEvent = new SignInSuccessEvent(getProfile(), password);
                    EventBus.getDefault().post(successEvent);
                    Log.i("LOG", "SignIn success");
                }
            }
        }.execute(username, password, deviceID);
    }

    public void signIn(String username, String password){
        loadProfile(username, password, Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
    }

    public Profile getProfile() {
        return profile;
    }

    public void clearDatas(){
        if (profile!=null) {
            profile = null;
            successEvent.clearDatas();
        }
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
