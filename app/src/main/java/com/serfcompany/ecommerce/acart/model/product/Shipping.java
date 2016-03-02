
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Shipping {

    @SerializedName("weight")
    @Expose
    private Weight weight;
    @SerializedName("dimension")
    @Expose
    private Dimension dimension;
    @SerializedName("shipping_class")
    @Expose
    private ShippingClass shippingClass;

    /**
     * 
     * @return
     *     The weight
     */
    public Weight getWeight() {
        return weight;
    }

    /**
     * 
     * @param weight
     *     The weight
     */
    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    /**
     * 
     * @return
     *     The dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * 
     * @param dimension
     *     The dimension
     */
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * 
     * @return
     *     The shippingClass
     */
    public ShippingClass getShippingClass() {
        return shippingClass;
    }

    /**
     * 
     * @param shippingClass
     *     The shipping_class
     */
    public void setShippingClass(ShippingClass shippingClass) {
        this.shippingClass = shippingClass;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(weight).append(dimension).append(shippingClass).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Shipping) == false) {
            return false;
        }
        Shipping rhs = ((Shipping) other);
        return new EqualsBuilder().append(weight, rhs.weight).append(dimension, rhs.dimension).append(shippingClass, rhs.shippingClass).isEquals();
    }

}
