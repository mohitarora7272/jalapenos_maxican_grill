package com.serfcompany.ecommerce.acart.event;

public class SignInFailureEvent {
    String reason;

    public SignInFailureEvent(String reason){
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
