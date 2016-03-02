
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Attributes {

    @SerializedName("has_attributes")
    @Expose
    private Boolean hasAttributes;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The hasAttributes
     */
    public Boolean getHasAttributes() {
        return hasAttributes;
    }

    /**
     * 
     * @param hasAttributes
     *     The has_attributes
     */
    public void setHasAttributes(Boolean hasAttributes) {
        this.hasAttributes = hasAttributes;
    }

    /**
     * 
     * @return
     *     The attributes
     */
    public List<Object> getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(hasAttributes).append(attributes).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Attributes) == false) {
            return false;
        }
        Attributes rhs = ((Attributes) other);
        return new EqualsBuilder().append(hasAttributes, rhs.hasAttributes).append(attributes, rhs.attributes).isEquals();
    }

}
