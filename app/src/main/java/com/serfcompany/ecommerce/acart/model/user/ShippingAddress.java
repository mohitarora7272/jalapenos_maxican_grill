
package com.serfcompany.ecommerce.acart.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingAddress {

    @SerializedName("shipping_first_name")
    @Expose
    private String shippingFirstName;
    @SerializedName("shipping_last_name")
    @Expose
    private String shippingLastName;
    @SerializedName("shipping_company")
    @Expose
    private String shippingCompany;
    @SerializedName("shipping_address_1")
    @Expose
    private String shippingAddress1;
    @SerializedName("shipping_address_2")
    @Expose
    private String shippingAddress2;
    @SerializedName("shipping_city")
    @Expose
    private String shippingCity;
    @SerializedName("shipping_postcode")
    @Expose
    private String shippingPostcode;
    @SerializedName("shipping_state")
    @Expose
    private String shippingState;
    @SerializedName("shipping_state_code")
    @Expose
    private String shippingStateCode;
    @SerializedName("shipping_has_state")
    @Expose
    private Boolean shippingHasState;
    @SerializedName("shipping_country")
    @Expose
    private String shippingCountry;
    @SerializedName("shipping_country_code")
    @Expose
    private String shippingCountryCode;

    /**
     * 
     * @return
     *     The shippingFirstName
     */
    public String getShippingFirstName() {
        return shippingFirstName;
    }

    /**
     * 
     * @param shippingFirstName
     *     The shipping_first_name
     */
    public void setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    /**
     * 
     * @return
     *     The shippingLastName
     */
    public String getShippingLastName() {
        return shippingLastName;
    }

    /**
     * 
     * @param shippingLastName
     *     The shipping_last_name
     */
    public void setShippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
    }

    /**
     * 
     * @return
     *     The shippingCompany
     */
    public String getShippingCompany() {
        return shippingCompany;
    }

    /**
     * 
     * @param shippingCompany
     *     The shipping_company
     */
    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    /**
     * 
     * @return
     *     The shippingAddress1
     */
    public String getShippingAddress1() {
        return shippingAddress1;
    }

    /**
     * 
     * @param shippingAddress1
     *     The shipping_address_1
     */
    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    /**
     * 
     * @return
     *     The shippingAddress2
     */
    public String getShippingAddress2() {
        return shippingAddress2;
    }

    /**
     * 
     * @param shippingAddress2
     *     The shipping_address_2
     */
    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    /**
     * 
     * @return
     *     The shippingCity
     */
    public String getShippingCity() {
        return shippingCity;
    }

    /**
     * 
     * @param shippingCity
     *     The shipping_city
     */
    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    /**
     * 
     * @return
     *     The shippingPostcode
     */
    public String getShippingPostcode() {
        return shippingPostcode;
    }

    /**
     * 
     * @param shippingPostcode
     *     The shipping_postcode
     */
    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    /**
     * 
     * @return
     *     The shippingState
     */
    public String getShippingState() {
        return shippingState;
    }

    /**
     * 
     * @param shippingState
     *     The shipping_state
     */
    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    /**
     * 
     * @return
     *     The shippingStateCode
     */
    public String getShippingStateCode() {
        return shippingStateCode;
    }

    /**
     * 
     * @param shippingStateCode
     *     The shipping_state_code
     */
    public void setShippingStateCode(String shippingStateCode) {
        this.shippingStateCode = shippingStateCode;
    }

    /**
     * 
     * @return
     *     The shippingHasState
     */
    public Boolean getShippingHasState() {
        return shippingHasState;
    }

    /**
     * 
     * @param shippingHasState
     *     The shipping_has_state
     */
    public void setShippingHasState(Boolean shippingHasState) {
        this.shippingHasState = shippingHasState;
    }

    /**
     * 
     * @return
     *     The shippingCountry
     */
    public String getShippingCountry() {
        return shippingCountry;
    }

    /**
     * 
     * @param shippingCountry
     *     The shipping_country
     */
    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    /**
     * 
     * @return
     *     The shippingCountryCode
     */
    public String getShippingCountryCode() {
        return shippingCountryCode;
    }

    /**
     * 
     * @param shippingCountryCode
     *     The shipping_country_code
     */
    public void setShippingCountryCode(String shippingCountryCode) {
        this.shippingCountryCode = shippingCountryCode;
    }

}
