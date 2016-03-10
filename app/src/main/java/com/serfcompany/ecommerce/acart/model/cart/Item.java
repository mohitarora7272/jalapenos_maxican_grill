
package com.serfcompany.ecommerce.acart.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product-price")
    @Expose
    private Double productPrice;
    @SerializedName("product-price-tax")
    @Expose
    private Integer productPriceTax;
    @SerializedName("total-price")
    @Expose
    private Double totalPrice;
    @SerializedName("total-price-tax")
    @Expose
    private Integer totalPriceTax;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
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
     *     The product-price
     */
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 
     * @return
     *     The productPriceTax
     */
    public Integer getProductPriceTax() {
        return productPriceTax;
    }

    /**
     * 
     * @param productPriceTax
     *     The product-price-tax
     */
    public void setProductPriceTax(Integer productPriceTax) {
        this.productPriceTax = productPriceTax;
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
     *     The total-price
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 
     * @return
     *     The totalPriceTax
     */
    public Integer getTotalPriceTax() {
        return totalPriceTax;
    }

    /**
     * 
     * @param totalPriceTax
     *     The total-price-tax
     */
    public void setTotalPriceTax(Integer totalPriceTax) {
        this.totalPriceTax = totalPriceTax;
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

}
