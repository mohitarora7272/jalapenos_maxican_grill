
package com.serfcompany.ecommerce.acart.model.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MyOrder {

    @SerializedName("orderID")
    @Expose
    private Integer orderID;
    @SerializedName("order_key")
    @Expose
    private String orderKey;
    @SerializedName("display-price-during-cart-checkout")
    @Expose
    private String displayPriceDuringCartCheckout;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("paymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("billing_email")
    @Expose
    private String billingEmail;
    @SerializedName("billing_phone")
    @Expose
    private String billingPhone;
    @SerializedName("billing_address")
    @Expose
    private String billingAddress;
    @SerializedName("shipping_address")
    @Expose
    private String shippingAddress;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @SerializedName("used_coupon")
    @Expose
    private List<Object> usedCoupon = new ArrayList<Object>();
    @SerializedName("subtotalWithTax")
    @Expose
    private Double subtotalWithTax;
    @SerializedName("subtotalExTax")
    @Expose
    private Double subtotalExTax;
    @SerializedName("shipping_method")
    @Expose
    private Object shippingMethod;
    @SerializedName("shipping_cost")
    @Expose
    private Double shippingCost;
    @SerializedName("shipping_tax")
    @Expose
    private Double shippingTax;
    @SerializedName("tax_total")
    @Expose
    private Double taxTotal;
    @SerializedName("discount_total")
    @Expose
    private Double discountTotal;
    @SerializedName("order_total")
    @Expose
    private Double orderTotal;
    @SerializedName("order_note")
    @Expose
    private String orderNote;
    @SerializedName("payment_method_id")
    @Expose
    private String paymentMethodId;
    @SerializedName("payment_method_title")
    @Expose
    private String paymentMethodTitle;
    @SerializedName("payment_desc")
    @Expose
    private String paymentDesc;
    @SerializedName("order_notes")
    @Expose
    private List<Object> orderNotes = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The orderID
     */
    public Integer getOrderID() {
        return orderID;
    }

    /**
     * 
     * @param orderID
     *     The orderID
     */
    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    /**
     * 
     * @return
     *     The orderKey
     */
    public String getOrderKey() {
        return orderKey;
    }

    /**
     * 
     * @param orderKey
     *     The order_key
     */
    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
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
     *     The orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * 
     * @param orderDate
     *     The orderDate
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 
     * @return
     *     The paymentDate
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * 
     * @param paymentDate
     *     The paymentDate
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
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
     *     The billingEmail
     */
    public String getBillingEmail() {
        return billingEmail;
    }

    /**
     * 
     * @param billingEmail
     *     The billing_email
     */
    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    /**
     * 
     * @return
     *     The billingPhone
     */
    public String getBillingPhone() {
        return billingPhone;
    }

    /**
     * 
     * @param billingPhone
     *     The billing_phone
     */
    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    /**
     * 
     * @return
     *     The billingAddress
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * 
     * @param billingAddress
     *     The billing_address
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * 
     * @return
     *     The shippingAddress
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * 
     * @param shippingAddress
     *     The shipping_address
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The usedCoupon
     */
    public List<Object> getUsedCoupon() {
        return usedCoupon;
    }

    /**
     * 
     * @param usedCoupon
     *     The used_coupon
     */
    public void setUsedCoupon(List<Object> usedCoupon) {
        this.usedCoupon = usedCoupon;
    }

    /**
     * 
     * @return
     *     The subtotalWithTax
     */
    public Double getSubtotalWithTax() {
        return subtotalWithTax;
    }

    /**
     * 
     * @param subtotalWithTax
     *     The subtotalWithTax
     */
    public void setSubtotalWithTax(Double subtotalWithTax) {
        this.subtotalWithTax = subtotalWithTax;
    }

    /**
     * 
     * @return
     *     The subtotalExTax
     */
    public Double getSubtotalExTax() {
        return subtotalExTax;
    }

    /**
     * 
     * @param subtotalExTax
     *     The subtotalExTax
     */
    public void setSubtotalExTax(Double subtotalExTax) {
        this.subtotalExTax = subtotalExTax;
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
     *     The shipping_method
     */
    public void setShippingMethod(Object shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     * 
     * @return
     *     The shippingCost
     */
    public Double getShippingCost() {
        return shippingCost;
    }

    /**
     * 
     * @param shippingCost
     *     The shipping_cost
     */
    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    /**
     * 
     * @return
     *     The shippingTax
     */
    public Double getShippingTax() {
        return shippingTax;
    }

    /**
     * 
     * @param shippingTax
     *     The shipping_tax
     */
    public void setShippingTax(Double shippingTax) {
        this.shippingTax = shippingTax;
    }

    /**
     * 
     * @return
     *     The taxTotal
     */
    public Double getTaxTotal() {
        return taxTotal;
    }

    /**
     * 
     * @param taxTotal
     *     The tax_total
     */
    public void setTaxTotal(Double taxTotal) {
        this.taxTotal = taxTotal;
    }

    /**
     * 
     * @return
     *     The discountTotal
     */
    public Double getDiscountTotal() {
        return discountTotal;
    }

    /**
     * 
     * @param discountTotal
     *     The discount_total
     */
    public void setDiscountTotal(Double discountTotal) {
        this.discountTotal = discountTotal;
    }

    /**
     * 
     * @return
     *     The orderTotal
     */
    public Double getOrderTotal() {
        return orderTotal;
    }

    /**
     * 
     * @param orderTotal
     *     The order_total
     */
    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * 
     * @return
     *     The orderNote
     */
    public String getOrderNote() {
        return orderNote;
    }

    /**
     * 
     * @param orderNote
     *     The order_note
     */
    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    /**
     * 
     * @return
     *     The paymentMethodId
     */
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    /**
     * 
     * @param paymentMethodId
     *     The payment_method_id
     */
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    /**
     * 
     * @return
     *     The paymentMethodTitle
     */
    public String getPaymentMethodTitle() {
        return paymentMethodTitle;
    }

    /**
     * 
     * @param paymentMethodTitle
     *     The payment_method_title
     */
    public void setPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
    }

    /**
     * 
     * @return
     *     The paymentDesc
     */
    public String getPaymentDesc() {
        return paymentDesc;
    }

    /**
     * 
     * @param paymentDesc
     *     The payment_desc
     */
    public void setPaymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
    }

    /**
     * 
     * @return
     *     The orderNotes
     */
    public List<Object> getOrderNotes() {
        return orderNotes;
    }

    /**
     * 
     * @param orderNotes
     *     The order_notes
     */
    public void setOrderNotes(List<Object> orderNotes) {
        this.orderNotes = orderNotes;
    }

}
