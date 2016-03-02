
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Inventory {

    @SerializedName("manage_stock")
    @Expose
    private Boolean manageStock;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("stock_status")
    @Expose
    private Boolean stockStatus;
    @SerializedName("allow_backorder")
    @Expose
    private Boolean allowBackorder;
    @SerializedName("allow_backorder_require_notification")
    @Expose
    private Boolean allowBackorderRequireNotification;
    @SerializedName("sold_individually")
    @Expose
    private Boolean soldIndividually;

    /**
     * 
     * @return
     *     The manageStock
     */
    public Boolean getManageStock() {
        return manageStock;
    }

    /**
     * 
     * @param manageStock
     *     The manage_stock
     */
    public void setManageStock(Boolean manageStock) {
        this.manageStock = manageStock;
    }

    /**
     * 
     * @return
     *     The quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity
     *     The quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return
     *     The stockStatus
     */
    public Boolean getStockStatus() {
        return stockStatus;
    }

    /**
     * 
     * @param stockStatus
     *     The stock_status
     */
    public void setStockStatus(Boolean stockStatus) {
        this.stockStatus = stockStatus;
    }

    /**
     * 
     * @return
     *     The allowBackorder
     */
    public Boolean getAllowBackorder() {
        return allowBackorder;
    }

    /**
     * 
     * @param allowBackorder
     *     The allow_backorder
     */
    public void setAllowBackorder(Boolean allowBackorder) {
        this.allowBackorder = allowBackorder;
    }

    /**
     * 
     * @return
     *     The allowBackorderRequireNotification
     */
    public Boolean getAllowBackorderRequireNotification() {
        return allowBackorderRequireNotification;
    }

    /**
     * 
     * @param allowBackorderRequireNotification
     *     The allow_backorder_require_notification
     */
    public void setAllowBackorderRequireNotification(Boolean allowBackorderRequireNotification) {
        this.allowBackorderRequireNotification = allowBackorderRequireNotification;
    }

    /**
     * 
     * @return
     *     The soldIndividually
     */
    public Boolean getSoldIndividually() {
        return soldIndividually;
    }

    /**
     * 
     * @param soldIndividually
     *     The sold_individually
     */
    public void setSoldIndividually(Boolean soldIndividually) {
        this.soldIndividually = soldIndividually;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(manageStock).append(quantity).append(stockStatus).append(allowBackorder).append(allowBackorderRequireNotification).append(soldIndividually).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Inventory) == false) {
            return false;
        }
        Inventory rhs = ((Inventory) other);
        return new EqualsBuilder().append(manageStock, rhs.manageStock).append(quantity, rhs.quantity).append(stockStatus, rhs.stockStatus).append(allowBackorder, rhs.allowBackorder).append(allowBackorderRequireNotification, rhs.allowBackorderRequireNotification).append(soldIndividually, rhs.soldIndividually).isEquals();
    }

}
