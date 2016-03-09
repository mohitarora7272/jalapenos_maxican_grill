package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;

import com.serfcompany.ecommerce.acart.event.SignInFailureEvent;
import com.serfcompany.ecommerce.acart.event.SignInSuccessEvent;
import com.serfcompany.ecommerce.acart.model.user.Profile;

/**
 * Created by serfcompany on 09.03.16.
 */
public class CartBillingActivityPresenter {
    SignInSuccessEvent successEvent;
    SignInFailureEvent failureEvent;
    Profile profile;
    Context context;

    public CartBillingActivityPresenter(Context context){
        this.context = context;
    }

}
