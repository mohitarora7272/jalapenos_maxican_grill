
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Pricing {

    @SerializedName("is_on_sale")
    @Expose
    private Boolean isOnSale;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("sale_start")
    @Expose
    private SaleStart saleStart;
    @SerializedName("sale_end")
    @Expose
    private SaleEnd saleEnd;

    /**
     * 
     * @return
     *     The isOnSale
     */
    public Boolean getIsOnSale() {
        return isOnSale;
    }

    /**
     * 
     * @param isOnSale
     *     The is_on_sale
     */
    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
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
     *     The regularPrice
     */
    public String getRegularPrice() {
        return regularPrice;
    }

    /**
     * 
     * @param regularPrice
     *     The regular_price
     */
    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    /**
     * 
     * @return
     *     The salePrice
     */
    public String getSalePrice() {
        return salePrice;
    }

    /**
     * 
     * @param salePrice
     *     The sale_price
     */
    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * 
     * @return
     *     The saleStart
     */
    public SaleStart getSaleStart() {
        return saleStart;
    }

    /**
     * 
     * @param saleStart
     *     The sale_start
     */
    public void setSaleStart(SaleStart saleStart) {
        this.saleStart = saleStart;
    }

    /**
     * 
     * @return
     *     The saleEnd
     */
    public SaleEnd getSaleEnd() {
        return saleEnd;
    }

    /**
     * 
     * @param saleEnd
     *     The sale_end
     */
    public void setSaleEnd(SaleEnd saleEnd) {
        this.saleEnd = saleEnd;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isOnSale).append(currency).append(regularPrice).append(salePrice).append(saleStart).append(saleEnd).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pricing) == false) {
            return false;
        }
        Pricing rhs = ((Pricing) other);
        return new EqualsBuilder().append(isOnSale, rhs.isOnSale).append(currency, rhs.currency).append(regularPrice, rhs.regularPrice).append(salePrice, rhs.salePrice).append(saleStart, rhs.saleStart).append(saleEnd, rhs.saleEnd).isEquals();
    }

}
