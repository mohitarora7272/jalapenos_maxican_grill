
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class IfGroup {

    @SerializedName("min_price")
    @Expose
    private MinPrice_ minPrice;
    @SerializedName("group")
    @Expose
    private List<Object> group = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The minPrice
     */
    public MinPrice_ getMinPrice() {
        return minPrice;
    }

    /**
     * 
     * @param minPrice
     *     The min_price
     */
    public void setMinPrice(MinPrice_ minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 
     * @return
     *     The group
     */
    public List<Object> getGroup() {
        return group;
    }

    /**
     * 
     * @param group
     *     The group
     */
    public void setGroup(List<Object> group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(minPrice).append(group).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof IfGroup) == false) {
            return false;
        }
        IfGroup rhs = ((IfGroup) other);
        return new EqualsBuilder().append(minPrice, rhs.minPrice).append(group, rhs.group).isEquals();
    }

}
