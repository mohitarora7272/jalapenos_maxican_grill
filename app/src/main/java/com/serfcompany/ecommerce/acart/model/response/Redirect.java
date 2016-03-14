package com.serfcompany.ecommerce.acart.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Redirect {

    @SerializedName("Status")
    @Expose
    private String Status;
    @SerializedName("Reason")
    @Expose
    private String Reason;
    @SerializedName("redirect")
    @Expose
    private String redirect;

    /**
     *
     * @return
     * The Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     *
     * @param Status
     * The Status
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     *
     * @return
     * The Reason
     */
    public String getReason() {
        return Reason;
    }

    /**
     *
     * @param Reason
     * The Reason
     */
    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    /**
     *
     * @return
     * The redirect
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     *
     * @param redirect
     * The redirect
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

}
