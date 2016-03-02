
package com.serfcompany.ecommerce.acart.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User {

    @SerializedName("ID")
    @Expose
    private Integer ID;
    @SerializedName("user_login")
    @Expose
    private String userLogin;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("user_nicename")
    @Expose
    private String userNicename;
    @SerializedName("user_nickname")
    @Expose
    private String userNickname;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("order_count")
    @Expose
    private String orderCount;
    @SerializedName("credit_card_management_aut_net")
    @Expose
    private List<Object> creditCardManagementAutNet = new ArrayList<Object>();
    @SerializedName("user_registered")
    @Expose
    private UserRegistered userRegistered;
    @SerializedName("billing_address")
    @Expose
    private BillingAddress billingAddress;
    @SerializedName("shipping_address")
    @Expose
    private ShippingAddress shippingAddress;

    /**
     * 
     * @return
     *     The ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * 
     * @param ID
     *     The ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * 
     * @return
     *     The userLogin
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * 
     * @param userLogin
     *     The user_login
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * 
     * @return
     *     The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 
     * @param avatar
     *     The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The userNicename
     */
    public String getUserNicename() {
        return userNicename;
    }

    /**
     * 
     * @param userNicename
     *     The user_nicename
     */
    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename;
    }

    /**
     * 
     * @return
     *     The userNickname
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 
     * @param userNickname
     *     The user_nickname
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * 
     * @return
     *     The userStatus
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 
     * @param userStatus
     *     The user_status
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 
     * @return
     *     The orderCount
     */
    public String getOrderCount() {
        return orderCount;
    }

    /**
     * 
     * @param orderCount
     *     The order_count
     */
    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * 
     * @return
     *     The creditCardManagementAutNet
     */
    public List<Object> getCreditCardManagementAutNet() {
        return creditCardManagementAutNet;
    }

    /**
     * 
     * @param creditCardManagementAutNet
     *     The credit_card_management_aut_net
     */
    public void setCreditCardManagementAutNet(List<Object> creditCardManagementAutNet) {
        this.creditCardManagementAutNet = creditCardManagementAutNet;
    }

    /**
     * 
     * @return
     *     The userRegistered
     */
    public UserRegistered getUserRegistered() {
        return userRegistered;
    }

    /**
     * 
     * @param userRegistered
     *     The user_registered
     */
    public void setUserRegistered(UserRegistered userRegistered) {
        this.userRegistered = userRegistered;
    }

    /**
     * 
     * @return
     *     The billingAddress
     */
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    /**
     * 
     * @param billingAddress
     *     The billing_address
     */
    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * 
     * @return
     *     The shippingAddress
     */
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    /**
     * 
     * @param shippingAddress
     *     The shipping_address
     */
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}
