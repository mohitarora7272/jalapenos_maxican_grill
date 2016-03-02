package com.serfcompany.ecommerce.acart.event;

/**
 * Created by serfcompany on 02.03.16.
 */
public class SignUpEvent {
    private int statusCode;

    public SignUpEvent(int statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
