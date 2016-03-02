
package com.serfcompany.ecommerce.acart.model.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_info")
    @Expose
    private ProductInfo productInfo;
    @SerializedName("variation_id")
    @Expose
    private String variationId;
    @SerializedName("variation_info")
    @Expose
    private VariationInfo variationInfo;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("product_price")
    @Expose
    private Double productPrice;
    @SerializedName("product_price_ex_tax")
    @Expose
    private Double productPriceExTax;
    @SerializedName("total_price")
    @Expose
    private Double totalPrice;
    @SerializedName("total_price_ex_tax")
    @Expose
    private String totalPriceExTax;

    /**
     * 
     * @return
     *     The productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 
     * @param productId
     *     The product_id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 
     * @return
     *     The productInfo
     */
    public ProductInfo getProductInfo() {
        return productInfo;
    }

    /**
     * 
     * @param productInfo
     *     The product_info
     */
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    /**
     * 
     * @return
     *     The variationId
     */
    public String getVariationId() {
        return variationId;
    }

    /**
     * 
     * @param variationId
     *     The variation_id
     */
    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    /**
     * 
     * @return
     *     The variationInfo
     */
    public VariationInfo getVariationInfo() {
        return variationInfo;
    }

    /**
     * 
     * @param variationInfo
     *     The variation_info
     */
    public void setVariationInfo(VariationInfo variationInfo) {
        this.variationInfo = variationInfo;
    }

    /**
     * 
     * @return
     *     The quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity
     *     The quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return
     *     The productPrice
     */
    public Double getProductPrice() {
        return productPrice;
    }

    /**
     * 
     * @param productPrice
     *     The product_price
     */
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 
     * @return
     *     The productPriceExTax
     */
    public Double getProductPriceExTax() {
        return productPriceExTax;
    }

    /**
     * 
     * @param productPriceExTax
     *     The product_price_ex_tax
     */
    public void setProductPriceExTax(Double productPriceExTax) {
        this.productPriceExTax = productPriceExTax;
    }

    /**
     * 
     * @return
     *     The totalPrice
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 
     * @param totalPrice
     *     The total_price
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 
     * @return
     *     The totalPriceExTax
     */
    public String getTotalPriceExTax() {
        return totalPriceExTax;
    }

    /**
     * 
     * @param totalPriceExTax
     *     The total_price_ex_tax
     */
    public void setTotalPriceExTax(String totalPriceExTax) {
        this.totalPriceExTax = totalPriceExTax;
    }

}
