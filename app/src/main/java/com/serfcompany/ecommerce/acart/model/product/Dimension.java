
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Dimension {

    @SerializedName("has_dimension")
    @Expose
    private Boolean hasDimension;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("value_l")
    @Expose
    private String valueL;
    @SerializedName("value_w")
    @Expose
    private String valueW;
    @SerializedName("value_h")
    @Expose
    private String valueH;

    /**
     * 
     * @return
     *     The hasDimension
     */
    public Boolean getHasDimension() {
        return hasDimension;
    }

    /**
     * 
     * @param hasDimension
     *     The has_dimension
     */
    public void setHasDimension(Boolean hasDimension) {
        this.hasDimension = hasDimension;
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
     *     The valueL
     */
    public String getValueL() {
        return valueL;
    }

    /**
     * 
     * @param valueL
     *     The value_l
     */
    public void setValueL(String valueL) {
        this.valueL = valueL;
    }

    /**
     * 
     * @return
     *     The valueW
     */
    public String getValueW() {
        return valueW;
    }

    /**
     * 
     * @param valueW
     *     The value_w
     */
    public void setValueW(String valueW) {
        this.valueW = valueW;
    }

    /**
     * 
     * @return
     *     The valueH
     */
    public String getValueH() {
        return valueH;
    }

    /**
     * 
     * @param valueH
     *     The value_h
     */
    public void setValueH(String valueH) {
        this.valueH = valueH;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(hasDimension).append(unit).append(valueL).append(valueW).append(valueH).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Dimension) == false) {
            return false;
        }
        Dimension rhs = ((Dimension) other);
        return new EqualsBuilder().append(hasDimension, rhs.hasDimension).append(unit, rhs.unit).append(valueL, rhs.valueL).append(valueW, rhs.valueW).append(valueH, rhs.valueH).isEquals();
    }

}
