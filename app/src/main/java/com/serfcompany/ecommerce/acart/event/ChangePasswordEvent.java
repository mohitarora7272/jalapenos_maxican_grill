package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.response.Response;

public class ChangePasswordEvent {
    private Response response;

    public ChangePasswordEvent(Response response){
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
