
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductGallery {

    @SerializedName("featured_images")
    @Expose
    private String featuredImages;
    @SerializedName("other_images")
    @Expose
    private List<Object> otherImages = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The featuredImages
     */
    public String getFeaturedImages() {
        return featuredImages;
    }

    /**
     * 
     * @param featuredImages
     *     The featured_images
     */
    public void setFeaturedImages(String featuredImages) {
        this.featuredImages = featuredImages;
    }

    /**
     * 
     * @return
     *     The otherImages
     */
    public List<Object> getOtherImages() {
        return otherImages;
    }

    /**
     * 
     * @param otherImages
     *     The other_images
     */
    public void setOtherImages(List<Object> otherImages) {
        this.otherImages = otherImages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(featuredImages).append(otherImages).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductGallery) == false) {
            return false;
        }
        ProductGallery rhs = ((ProductGallery) other);
        return new EqualsBuilder().append(featuredImages, rhs.featuredImages).append(otherImages, rhs.otherImages).isEquals();
    }

}
