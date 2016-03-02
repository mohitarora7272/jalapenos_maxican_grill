
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class IfExternal {

    @SerializedName("product_url")
    @Expose
    private String productUrl;
    @SerializedName("button_name")
    @Expose
    private String buttonName;

    /**
     * 
     * @return
     *     The productUrl
     */
    public String getProductUrl() {
        return productUrl;
    }

    /**
     * 
     * @param productUrl
     *     The product_url
     */
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    /**
     * 
     * @return
     *     The buttonName
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * 
     * @param buttonName
     *     The button_name
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(productUrl).append(buttonName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof IfExternal) == false) {
            return false;
        }
        IfExternal rhs = ((IfExternal) other);
        return new EqualsBuilder().append(productUrl, rhs.productUrl).append(buttonName, rhs.buttonName).isEquals();
    }

}
