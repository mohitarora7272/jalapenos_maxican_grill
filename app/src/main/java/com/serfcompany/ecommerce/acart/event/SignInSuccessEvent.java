package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.user.Profile;

public class SignInSuccessEvent {
    Profile profile;
    String password;

    public SignInSuccessEvent(Profile profile, String password){
        this.profile = profile;
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getPassword() {
        return password;
    }

    public void clearDatas(){
        profile = null;
        password = null;
    }
}
