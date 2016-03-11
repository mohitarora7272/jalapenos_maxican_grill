package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.response.Response;
import com.serfcompany.ecommerce.acart.model.user.Profile;

/**
 * Created by serfcompany on 11.03.16.
 */
public class BillingUpdateEvent {
    private Response response;
    private Profile profile;

    public BillingUpdateEvent(Response response, Profile profile){
        this.response = response;
        this.profile = profile;
    }

    public Response getResponse() {
        return response;
    }

    public Profile getProfile() {
        return profile;
    }
}
