package com.serfcompany.ecommerce.acart.event;

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
