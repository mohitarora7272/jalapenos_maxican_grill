
package com.serfcompany.ecommerce.acart.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Coupon {

    @SerializedName("applied-coupon")
    @Expose
    private List<Object> appliedCoupon = new ArrayList<Object>();
    @SerializedName("discount-ammount")
    @Expose
    private List<List<Object>> discountAmmount = new ArrayList<List<Object>>();
    @SerializedName("coupon-array-inserted")
    @Expose
    private List<Object> couponArrayInserted = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The appliedCoupon
     */
    public List<Object> getAppliedCoupon() {
        return appliedCoupon;
    }

    /**
     * 
     * @param appliedCoupon
     *     The applied-coupon
     */
    public void setAppliedCoupon(List<Object> appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
    }

    /**
     * 
     * @return
     *     The discountAmmount
     */
    public List<List<Object>> getDiscountAmmount() {
        return discountAmmount;
    }

    /**
     * 
     * @param discountAmmount
     *     The discount-ammount
     */
    public void setDiscountAmmount(List<List<Object>> discountAmmount) {
        this.discountAmmount = discountAmmount;
    }

    /**
     * 
     * @return
     *     The couponArrayInserted
     */
    public List<Object> getCouponArrayInserted() {
        return couponArrayInserted;
    }

    /**
     * 
     * @param couponArrayInserted
     *     The coupon-array-inserted
     */
    public void setCouponArrayInserted(List<Object> couponArrayInserted) {
        this.couponArrayInserted = couponArrayInserted;
    }

}
