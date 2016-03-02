package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.user.Profile;

/**
 * Created by serfcompany on 01.03.16.
 */
public class SignInFailureEvent {
    String reason;

    public SignInFailureEvent(String reason){
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
