
package com.serfcompany.ecommerce.acart.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponArrayInserted {

    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;

    /**
     * 
     * @return
     *     The couponCode
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     * 
     * @param couponCode
     *     The coupon_code
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    /**
     * 
     * @return
     *     The errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @param errorMessage
     *     The error_message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
