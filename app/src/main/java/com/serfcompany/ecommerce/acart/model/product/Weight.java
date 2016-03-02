
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Weight {

    @SerializedName("has_weight")
    @Expose
    private Boolean hasWeight;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("value")
    @Expose
    private String value;

    /**
     * 
     * @return
     *     The hasWeight
     */
    public Boolean getHasWeight() {
        return hasWeight;
    }

    /**
     * 
     * @param hasWeight
     *     The has_weight
     */
    public void setHasWeight(Boolean hasWeight) {
        this.hasWeight = hasWeight;
    }

    /**
     * 
     * @return
     *     The unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 
     * @param unit
     *     The unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(hasWeight).append(unit).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Weight) == false) {
            return false;
        }
        Weight rhs = ((Weight) other);
        return new EqualsBuilder().append(hasWeight, rhs.hasWeight).append(unit, rhs.unit).append(value, rhs.value).isEquals();
    }

}
