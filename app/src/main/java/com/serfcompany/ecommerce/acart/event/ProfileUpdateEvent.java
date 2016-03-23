package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.response.Response;
import com.serfcompany.ecommerce.acart.model.user.Profile;

public class ProfileUpdateEvent {
    private Response response;
    private Profile profile;

    public ProfileUpdateEvent(Response response, Profile profile){
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
