
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Advanced {

    @SerializedName("purchase_note")
    @Expose
    private String purchaseNote;
    @SerializedName("menu_order")
    @Expose
    private Integer menuOrder;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;

    /**
     * 
     * @return
     *     The purchaseNote
     */
    public String getPurchaseNote() {
        return purchaseNote;
    }

    /**
     * 
     * @param purchaseNote
     *     The purchase_note
     */
    public void setPurchaseNote(String purchaseNote) {
        this.purchaseNote = purchaseNote;
    }

    /**
     * 
     * @return
     *     The menuOrder
     */
    public Integer getMenuOrder() {
        return menuOrder;
    }

    /**
     * 
     * @param menuOrder
     *     The menu_order
     */
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    /**
     * 
     * @return
     *     The commentStatus
     */
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     * 
     * @param commentStatus
     *     The comment_status
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(purchaseNote).append(menuOrder).append(commentStatus).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Advanced) == false) {
            return false;
        }
        Advanced rhs = ((Advanced) other);
        return new EqualsBuilder().append(purchaseNote, rhs.purchaseNote).append(menuOrder, rhs.menuOrder).append(commentStatus, rhs.commentStatus).isEquals();
    }

}
