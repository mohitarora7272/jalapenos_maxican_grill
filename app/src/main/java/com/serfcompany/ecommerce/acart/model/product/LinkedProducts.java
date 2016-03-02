
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class LinkedProducts {

    @SerializedName("upsells")
    @Expose
    private List<Object> upsells = new ArrayList<Object>();
    @SerializedName("cross_sale")
    @Expose
    private List<Object> crossSale = new ArrayList<Object>();
    @SerializedName("grouped")
    @Expose
    private Integer grouped;

    /**
     * 
     * @return
     *     The upsells
     */
    public List<Object> getUpsells() {
        return upsells;
    }

    /**
     * 
     * @param upsells
     *     The upsells
     */
    public void setUpsells(List<Object> upsells) {
        this.upsells = upsells;
    }

    /**
     * 
     * @return
     *     The crossSale
     */
    public List<Object> getCrossSale() {
        return crossSale;
    }

    /**
     * 
     * @param crossSale
     *     The cross_sale
     */
    public void setCrossSale(List<Object> crossSale) {
        this.crossSale = crossSale;
    }

    /**
     * 
     * @return
     *     The grouped
     */
    public Integer getGrouped() {
        return grouped;
    }

    /**
     * 
     * @param grouped
     *     The grouped
     */
    public void setGrouped(Integer grouped) {
        this.grouped = grouped;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(upsells).append(crossSale).append(grouped).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LinkedProducts) == false) {
            return false;
        }
        LinkedProducts rhs = ((LinkedProducts) other);
        return new EqualsBuilder().append(upsells, rhs.upsells).append(crossSale, rhs.crossSale).append(grouped, rhs.grouped).isEquals();
    }

}
