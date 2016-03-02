
package com.serfcompany.ecommerce.acart.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingAddress {

    @SerializedName("billing_first_name")
    @Expose
    private String billingFirstName;
    @SerializedName("billing_last_name")
    @Expose
    private String billingLastName;
    @SerializedName("billing_company")
    @Expose
    private String billingCompany;
    @SerializedName("billing_address_1")
    @Expose
    private String billingAddress1;
    @SerializedName("billing_address_2")
    @Expose
    private String billingAddress2;
    @SerializedName("billing_city")
    @Expose
    private String billingCity;
    @SerializedName("billing_postcode")
    @Expose
    private String billingPostcode;
    @SerializedName("billing_state")
    @Expose
    private String billingState;
    @SerializedName("billing_state_code")
    @Expose
    private String billingStateCode;
    @SerializedName("billing_has_state")
    @Expose
    private Boolean billingHasState;
    @SerializedName("billing_country")
    @Expose
    private String billingCountry;
    @SerializedName("billing_country_code")
    @Expose
    private String billingCountryCode;
    @SerializedName("billing_phone")
    @Expose
    private String billingPhone;
    @SerializedName("billing_email")
    @Expose
    private String billingEmail;

    /**
     * 
     * @return
     *     The billingFirstName
     */
    public String getBillingFirstName() {
        return billingFirstName;
    }

    /**
     * 
     * @param billingFirstName
     *     The billing_first_name
     */
    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    /**
     * 
     * @return
     *     The billingLastName
     */
    public String getBillingLastName() {
        return billingLastName;
    }

    /**
     * 
     * @param billingLastName
     *     The billing_last_name
     */
    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    /**
     * 
     * @return
     *     The billingCompany
     */
    public String getBillingCompany() {
        return billingCompany;
    }

    /**
     * 
     * @param billingCompany
     *     The billing_company
     */
    public void setBillingCompany(String billingCompany) {
        this.billingCompany = billingCompany;
    }

    /**
     * 
     * @return
     *     The billingAddress1
     */
    public String getBillingAddress1() {
        return billingAddress1;
    }

    /**
     * 
     * @param billingAddress1
     *     The billing_address_1
     */
    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    /**
     * 
     * @return
     *     The billingAddress2
     */
    public String getBillingAddress2() {
        return billingAddress2;
    }

    /**
     * 
     * @param billingAddress2
     *     The billing_address_2
     */
    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    /**
     * 
     * @return
     *     The billingCity
     */
    public String getBillingCity() {
        return billingCity;
    }

    /**
     * 
     * @param billingCity
     *     The billing_city
     */
    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    /**
     * 
     * @return
     *     The billingPostcode
     */
    public String getBillingPostcode() {
        return billingPostcode;
    }

    /**
     * 
     * @param billingPostcode
     *     The billing_postcode
     */
    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode = billingPostcode;
    }

    /**
     * 
     * @return
     *     The billingState
     */
    public String getBillingState() {
        return billingState;
    }

    /**
     * 
     * @param billingState
     *     The billing_state
     */
    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    /**
     * 
     * @return
     *     The billingStateCode
     */
    public String getBillingStateCode() {
        return billingStateCode;
    }

    /**
     * 
     * @param billingStateCode
     *     The billing_state_code
     */
    public void setBillingStateCode(String billingStateCode) {
        this.billingStateCode = billingStateCode;
    }

    /**
     * 
     * @return
     *     The billingHasState
     */
    public Boolean getBillingHasState() {
        return billingHasState;
    }

    /**
     * 
     * @param billingHasState
     *     The billing_has_state
     */
    public void setBillingHasState(Boolean billingHasState) {
        this.billingHasState = billingHasState;
    }

    /**
     * 
     * @return
     *     The billingCountry
     */
    public String getBillingCountry() {
        return billingCountry;
    }

    /**
     * 
     * @param billingCountry
     *     The billing_country
     */
    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    /**
     * 
     * @return
     *     The billingCountryCode
     */
    public String getBillingCountryCode() {
        return billingCountryCode;
    }

    /**
     * 
     * @param billingCountryCode
     *     The billing_country_code
     */
    public void setBillingCountryCode(String billingCountryCode) {
        this.billingCountryCode = billingCountryCode;
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

}
