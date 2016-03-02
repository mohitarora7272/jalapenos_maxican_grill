
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ShippingClass {

    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("class_id")
    @Expose
    private Integer classId;

    /**
     * 
     * @return
     *     The className
     */
    public String getClassName() {
        return className;
    }

    /**
     * 
     * @param className
     *     The class_name
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 
     * @return
     *     The classId
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 
     * @param classId
     *     The class_id
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(className).append(classId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShippingClass) == false) {
            return false;
        }
        ShippingClass rhs = ((ShippingClass) other);
        return new EqualsBuilder().append(className, rhs.className).append(classId, rhs.classId).isEquals();
    }

}
