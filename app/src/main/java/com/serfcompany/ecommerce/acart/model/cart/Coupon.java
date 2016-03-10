
package com.serfcompany.ecommerce.acart.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Coupon {

    @SerializedName("applied-coupon")
    @Expose
    private List<String> appliedCoupon = new ArrayList<String>();
//    @SerializedName("discount-ammount")
//    @Expose
//    private List<DiscountAmmount> discountAmmount = new ArrayList<DiscountAmmount>();
    @SerializedName("coupon-array-inserted")
    @Expose
    private List<CouponArrayInserted> couponArrayInserted = new ArrayList<CouponArrayInserted>();

    /**
     * 
     * @return
     *     The appliedCoupon
     */
    public List<String> getAppliedCoupon() {
        return appliedCoupon;
    }

    /**
     * 
     * @param appliedCoupon
     *     The applied-coupon
     */
    public void setAppliedCoupon(List<String> appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
    }

//    /**
//     *
//     * @return
//     *     The discountAmmount
//     */
//    public List<DiscountAmmount> getDiscountAmmount() {
//        return discountAmmount;
//    }
//
//    /**
//     *
//     * @param discountAmmount
//     *     The discount-ammount
//     */
//    public void setDiscountAmmount(List<DiscountAmmount> discountAmmount) {
//        this.discountAmmount = discountAmmount;
//    }

    /**
     * 
     * @return
     *     The couponArrayInserted
     */
    public List<CouponArrayInserted> getCouponArrayInserted() {
        return couponArrayInserted;
    }

    /**
     * 
     * @param couponArrayInserted
     *     The coupon-array-inserted
     */
    public void setCouponArrayInserted(List<CouponArrayInserted> couponArrayInserted) {
        this.couponArrayInserted = couponArrayInserted;
    }

}
