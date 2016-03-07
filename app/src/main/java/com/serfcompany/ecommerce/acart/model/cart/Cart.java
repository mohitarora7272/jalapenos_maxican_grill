
package com.serfcompany.ecommerce.acart.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    @SerializedName("cart")
    @Expose
    private List<CartItem> cart = new ArrayList<CartItem>();
    @SerializedName("coupon")
    @Expose
    private Coupon coupon;
    @SerializedName("has_tax")
    @Expose
    private String hasTax;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("display-price-during-cart-checkout")
    @Expose
    private String displayPriceDuringCartCheckout;
    @SerializedName("cart-subtotal")
    @Expose
    private Double cartSubtotal;
    @SerializedName("cart-subtotal-ex-tax")
    @Expose
    private Double cartSubtotalExTax;
    @SerializedName("cart-tax-total")
    @Expose
    private Integer cartTaxTotal;
    @SerializedName("shipping-cost")
    @Expose
    private Integer shippingCost;
    @SerializedName("shipping-method")
    @Expose
    private Object shippingMethod;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("grand-total")
    @Expose
    private Double grandTotal;
    @SerializedName("payment-method")
    @Expose
    private List<PaymentMethod> paymentMethod = new ArrayList<PaymentMethod>();
    @SerializedName("shipping_available")
    @Expose
    private Object shippingAvailable;

    /**
     * 
     * @return
     *     The cart
     */
    public List<CartItem> getCart() {
        return cart;
    }

    /**
     * 
     * @param cart
     *     The cart
     */
    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    /**
     * 
     * @return
     *     The coupon
     */
    public Coupon getCoupon() {
        return coupon;
    }

    /**
     * 
     * @param coupon
     *     The coupon
     */
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    /**
     * 
     * @return
     *     The hasTax
     */
    public String getHasTax() {
        return hasTax;
    }

    /**
     * 
     * @param hasTax
     *     The has_tax
     */
    public void setHasTax(String hasTax) {
        this.hasTax = hasTax;
    }

    /**
     * 
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 
     * @return
     *     The displayPriceDuringCartCheckout
     */
    public String getDisplayPriceDuringCartCheckout() {
        return displayPriceDuringCartCheckout;
    }

    /**
     * 
     * @param displayPriceDuringCartCheckout
     *     The display-price-during-cart-checkout
     */
    public void setDisplayPriceDuringCartCheckout(String displayPriceDuringCartCheckout) {
        this.displayPriceDuringCartCheckout = displayPriceDuringCartCheckout;
    }

    /**
     * 
     * @return
     *     The cartSubtotal
     */
    public Double getCartSubtotal() {
        return cartSubtotal;
    }

    /**
     * 
     * @param cartSubtotal
     *     The cart-subtotal
     */
    public void setCartSubtotal(Double cartSubtotal) {
        this.cartSubtotal = cartSubtotal;
    }

    /**
     * 
     * @return
     *     The cartSubtotalExTax
     */
    public Double getCartSubtotalExTax() {
        return cartSubtotalExTax;
    }

    /**
     * 
     * @param cartSubtotalExTax
     *     The cart-subtotal-ex-tax
     */
    public void setCartSubtotalExTax(Double cartSubtotalExTax) {
        this.cartSubtotalExTax = cartSubtotalExTax;
    }

    /**
     * 
     * @return
     *     The cartTaxTotal
     */
    public Integer getCartTaxTotal() {
        return cartTaxTotal;
    }

    /**
     * 
     * @param cartTaxTotal
     *     The cart-tax-total
     */
    public void setCartTaxTotal(Integer cartTaxTotal) {
        this.cartTaxTotal = cartTaxTotal;
    }

    /**
     * 
     * @return
     *     The shippingCost
     */
    public Integer getShippingCost() {
        return shippingCost;
    }

    /**
     * 
     * @param shippingCost
     *     The shipping-cost
     */
    public void setShippingCost(Integer shippingCost) {
        this.shippingCost = shippingCost;
    }

    /**
     * 
     * @return
     *     The shippingMethod
     */
    public Object getShippingMethod() {
        return shippingMethod;
    }

    /**
     * 
     * @param shippingMethod
     *     The shipping-method
     */
    public void setShippingMethod(Object shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     * 
     * @return
     *     The discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * 
     * @param discount
     *     The discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * 
     * @return
     *     The grandTotal
     */
    public Double getGrandTotal() {
        return grandTotal;
    }

    /**
     * 
     * @param grandTotal
     *     The grand-total
     */
    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * 
     * @return
     *     The paymentMethod
     */
    public List<PaymentMethod> getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 
     * @param paymentMethod
     *     The payment-method
     */
    public void setPaymentMethod(List<PaymentMethod> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 
     * @return
     *     The shippingAvailable
     */
    public Object getShippingAvailable() {
        return shippingAvailable;
    }

    /**
     * 
     * @param shippingAvailable
     *     The shipping_available
     */
    public void setShippingAvailable(Object shippingAvailable) {
        this.shippingAvailable = shippingAvailable;
    }

}
