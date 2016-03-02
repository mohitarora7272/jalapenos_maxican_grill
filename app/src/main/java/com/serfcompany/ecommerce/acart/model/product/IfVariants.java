
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class IfVariants {

    @SerializedName("min_price")
    @Expose
    private MinPrice minPrice;
    @SerializedName("variables")
    @Expose
    private List<Object> variables = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The minPrice
     */
    public MinPrice getMinPrice() {
        return minPrice;
    }

    /**
     * 
     * @param minPrice
     *     The min_price
     */
    public void setMinPrice(MinPrice minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 
     * @return
     *     The variables
     */
    public List<Object> getVariables() {
        return variables;
    }

    /**
     * 
     * @param variables
     *     The variables
     */
    public void setVariables(List<Object> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(minPrice).append(variables).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof IfVariants) == false) {
            return false;
        }
        IfVariants rhs = ((IfVariants) other);
        return new EqualsBuilder().append(minPrice, rhs.minPrice).append(variables, rhs.variables).isEquals();
    }

}
