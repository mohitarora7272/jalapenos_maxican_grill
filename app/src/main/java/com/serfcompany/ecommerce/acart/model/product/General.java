
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class General {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("content")
    @Expose
    private Content content;
    @SerializedName("SKU")
    @Expose
    private String SKU;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("if_external")
    @Expose
    private IfExternal ifExternal;
    @SerializedName("pricing")
    @Expose
    private Pricing pricing;
    @SerializedName("tax_status")
    @Expose
    private String taxStatus;
    @SerializedName("tax_class")
    @Expose
    private String taxClass;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The content
     */
    public Content getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The SKU
     */
    public String getSKU() {
        return SKU;
    }

    /**
     * 
     * @param SKU
     *     The SKU
     */
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    /**
     * 
     * @return
     *     The productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 
     * @param productType
     *     The product_type
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * 
     * @return
     *     The ifExternal
     */
    public IfExternal getIfExternal() {
        return ifExternal;
    }

    /**
     * 
     * @param ifExternal
     *     The if_external
     */
    public void setIfExternal(IfExternal ifExternal) {
        this.ifExternal = ifExternal;
    }

    /**
     * 
     * @return
     *     The pricing
     */
    public Pricing getPricing() {
        return pricing;
    }

    /**
     * 
     * @param pricing
     *     The pricing
     */
    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    /**
     * 
     * @return
     *     The taxStatus
     */
    public String getTaxStatus() {
        return taxStatus;
    }

    /**
     * 
     * @param taxStatus
     *     The tax_status
     */
    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    /**
     * 
     * @return
     *     The taxClass
     */
    public String getTaxClass() {
        return taxClass;
    }

    /**
     * 
     * @param taxClass
     *     The tax_class
     */
    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(link).append(content).append(SKU).append(productType).append(ifExternal).append(pricing).append(taxStatus).append(taxClass).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof General) == false) {
            return false;
        }
        General rhs = ((General) other);
        return new EqualsBuilder().append(title, rhs.title).append(link, rhs.link).append(content, rhs.content).append(SKU, rhs.SKU).append(productType, rhs.productType).append(ifExternal, rhs.ifExternal).append(pricing, rhs.pricing).append(taxStatus, rhs.taxStatus).append(taxClass, rhs.taxClass).isEquals();
    }

}
