
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Product {

    @SerializedName("product_ID")
    @Expose
    private Integer productID;
    @SerializedName("is_downloadble")
    @Expose
    private Boolean isDownloadble;
    @SerializedName("is_virtual")
    @Expose
    private Boolean isVirtual;
    @SerializedName("is_purchasable")
    @Expose
    private Boolean isPurchasable;
    @SerializedName("is_featured")
    @Expose
    private Boolean isFeatured;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("general")
    @Expose
    private General general;
    @SerializedName("inventory")
    @Expose
    private Inventory inventory;
    @SerializedName("shipping")
    @Expose
    private Shipping shipping;
    @SerializedName("linked_products")
    @Expose
    private LinkedProducts linkedProducts;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("advanced")
    @Expose
    private Advanced advanced;
    @SerializedName("ratings")
    @Expose
    private Ratings ratings;
    @SerializedName("if_variants")
    @Expose
    private IfVariants ifVariants;
    @SerializedName("if_group")
    @Expose
    private IfGroup ifGroup;
    @SerializedName("product_gallery")
    @Expose
    private ProductGallery productGallery;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = new ArrayList<Category>();

    /**
     * 
     * @return
     *     The productID
     */
    public Integer getProductID() {
        return productID;
    }

    /**
     * 
     * @param productID
     *     The product_ID
     */
    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    /**
     * 
     * @return
     *     The isDownloadble
     */
    public Boolean getIsDownloadble() {
        return isDownloadble;
    }

    /**
     * 
     * @param isDownloadble
     *     The is_downloadble
     */
    public void setIsDownloadble(Boolean isDownloadble) {
        this.isDownloadble = isDownloadble;
    }

    /**
     * 
     * @return
     *     The isVirtual
     */
    public Boolean getIsVirtual() {
        return isVirtual;
    }

    /**
     * 
     * @param isVirtual
     *     The is_virtual
     */
    public void setIsVirtual(Boolean isVirtual) {
        this.isVirtual = isVirtual;
    }

    /**
     * 
     * @return
     *     The isPurchasable
     */
    public Boolean getIsPurchasable() {
        return isPurchasable;
    }

    /**
     * 
     * @param isPurchasable
     *     The is_purchasable
     */
    public void setIsPurchasable(Boolean isPurchasable) {
        this.isPurchasable = isPurchasable;
    }

    /**
     * 
     * @return
     *     The isFeatured
     */
    public Boolean getIsFeatured() {
        return isFeatured;
    }

    /**
     * 
     * @param isFeatured
     *     The is_featured
     */
    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    /**
     * 
     * @return
     *     The visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * 
     * @param visibility
     *     The visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * 
     * @return
     *     The general
     */
    public General getGeneral() {
        return general;
    }

    /**
     * 
     * @param general
     *     The general
     */
    public void setGeneral(General general) {
        this.general = general;
    }

    /**
     * 
     * @return
     *     The inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * 
     * @param inventory
     *     The inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * 
     * @return
     *     The shipping
     */
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * 
     * @param shipping
     *     The shipping
     */
    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    /**
     * 
     * @return
     *     The linkedProducts
     */
    public LinkedProducts getLinkedProducts() {
        return linkedProducts;
    }

    /**
     * 
     * @param linkedProducts
     *     The linked_products
     */
    public void setLinkedProducts(LinkedProducts linkedProducts) {
        this.linkedProducts = linkedProducts;
    }

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    /**
     * 
     * @return
     *     The advanced
     */
    public Advanced getAdvanced() {
        return advanced;
    }

    /**
     * 
     * @param advanced
     *     The advanced
     */
    public void setAdvanced(Advanced advanced) {
        this.advanced = advanced;
    }

    /**
     * 
     * @return
     *     The ratings
     */
    public Ratings getRatings() {
        return ratings;
    }

    /**
     * 
     * @param ratings
     *     The ratings
     */
    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    /**
     * 
     * @return
     *     The ifVariants
     */
    public IfVariants getIfVariants() {
        return ifVariants;
    }

    /**
     * 
     * @param ifVariants
     *     The if_variants
     */
    public void setIfVariants(IfVariants ifVariants) {
        this.ifVariants = ifVariants;
    }

    /**
     * 
     * @return
     *     The ifGroup
     */
    public IfGroup getIfGroup() {
        return ifGroup;
    }

    /**
     * 
     * @param ifGroup
     *     The if_group
     */
    public void setIfGroup(IfGroup ifGroup) {
        this.ifGroup = ifGroup;
    }

    /**
     * 
     * @return
     *     The productGallery
     */
    public ProductGallery getProductGallery() {
        return productGallery;
    }

    /**
     * 
     * @param productGallery
     *     The product_gallery
     */
    public void setProductGallery(ProductGallery productGallery) {
        this.productGallery = productGallery;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(productID).append(isDownloadble).append(isVirtual).append(isPurchasable).append(isFeatured).append(visibility).append(general).append(inventory).append(shipping).append(linkedProducts).append(attributes).append(advanced).append(ratings).append(ifVariants).append(ifGroup).append(productGallery).append(categories).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Product rhs = ((Product) other);
        return new EqualsBuilder().append(productID, rhs.productID).append(isDownloadble, rhs.isDownloadble).append(isVirtual, rhs.isVirtual).append(isPurchasable, rhs.isPurchasable).append(isFeatured, rhs.isFeatured).append(visibility, rhs.visibility).append(general, rhs.general).append(inventory, rhs.inventory).append(shipping, rhs.shipping).append(linkedProducts, rhs.linkedProducts).append(attributes, rhs.attributes).append(advanced, rhs.advanced).append(ratings, rhs.ratings).append(ifVariants, rhs.ifVariants).append(ifGroup, rhs.ifGroup).append(productGallery, rhs.productGallery).append(categories, rhs.categories).isEquals();
    }

}
