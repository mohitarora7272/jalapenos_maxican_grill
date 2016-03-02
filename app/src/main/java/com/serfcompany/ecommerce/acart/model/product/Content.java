
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Content {

    @SerializedName("full_html")
    @Expose
    private String fullHtml;
    @SerializedName("excepts")
    @Expose
    private String excepts;

    /**
     * 
     * @return
     *     The fullHtml
     */
    public String getFullHtml() {
        return fullHtml;
    }

    /**
     * 
     * @param fullHtml
     *     The full_html
     */
    public void setFullHtml(String fullHtml) {
        this.fullHtml = fullHtml;
    }

    /**
     * 
     * @return
     *     The excepts
     */
    public String getExcepts() {
        return excepts;
    }

    /**
     * 
     * @param excepts
     *     The excepts
     */
    public void setExcepts(String excepts) {
        this.excepts = excepts;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(fullHtml).append(excepts).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Content) == false) {
            return false;
        }
        Content rhs = ((Content) other);
        return new EqualsBuilder().append(fullHtml, rhs.fullHtml).append(excepts, rhs.excepts).isEquals();
    }

}
