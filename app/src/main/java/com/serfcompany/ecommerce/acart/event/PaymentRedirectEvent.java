package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.response.Redirect;

public class PaymentRedirectEvent {
    Redirect redirect;

    public PaymentRedirectEvent(Redirect redirect){this.redirect = redirect;}

    public Redirect getRedirect() {
        return redirect;
    }
}
