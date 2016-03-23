package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.serfcompany.ecommerce.acart.HTTPHolders.SignUpRequestHTTP;
import com.serfcompany.ecommerce.acart.event.SignUpEvent;
import com.serfcompany.ecommerce.acart.parser.SignUpCodeParser;

import de.greenrobot.event.EventBus;

public class SignUpActivityPresenter {

    SignUpEvent signUpEvent;
    int statusCode;
    Context context;

    public SignUpActivityPresenter(Context context){
        this.context = context;
    }

    public void signUp(String username, String email, String firstname,
                       String lastname, String password, String deviceID){
        new AsyncTask<String, Void, Void>(){

            @Override
            protected Void doInBackground(String... params) {
                SignUpRequestHTTP con = new SignUpRequestHTTP();
                SignUpCodeParser parser = new SignUpCodeParser();
                setStatusCode(parser.parseStatus(con
                        .userSignUp(params[0],params[1],params[2],params[3],params[4],params[5])));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (getStatusCode() != 4){
                    signUpEvent = new SignUpEvent(getStatusCode());
                    EventBus.getDefault().post(signUpEvent);
                    Log.i("LOG", "SignUp code : " + getStatusCode());
                }
            }
        }.execute(username, email, firstname, lastname, password, deviceID);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
