
package com.serfcompany.ecommerce.acart.model.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInfo {

    @SerializedName("featuredImages")
    @Expose
    private String featuredImages;
    @SerializedName("productName")
    @Expose
    private String productName;

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
     *     The featuredImages
     */
    public void setFeaturedImages(String featuredImages) {
        this.featuredImages = featuredImages;
    }

    /**
     * 
     * @return
     *     The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 
     * @param productName
     *     The productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

}
