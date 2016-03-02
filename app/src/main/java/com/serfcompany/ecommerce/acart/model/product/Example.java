
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Example {

    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("total_page")
    @Expose
    private Integer totalPage;
    @SerializedName("post_per_page")
    @Expose
    private Integer postPerPage;
    @SerializedName("total_post")
    @Expose
    private Integer totalPost;
    @SerializedName("products")
    @Expose
    private List<Product> products = new ArrayList<Product>();

    /**
     * 
     * @return
     *     The currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The current_page
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The totalPage
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * 
     * @param totalPage
     *     The total_page
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 
     * @return
     *     The postPerPage
     */
    public Integer getPostPerPage() {
        return postPerPage;
    }

    /**
     * 
     * @param postPerPage
     *     The post_per_page
     */
    public void setPostPerPage(Integer postPerPage) {
        this.postPerPage = postPerPage;
    }

    /**
     * 
     * @return
     *     The totalPost
     */
    public Integer getTotalPost() {
        return totalPost;
    }

    /**
     * 
     * @param totalPost
     *     The total_post
     */
    public void setTotalPost(Integer totalPost) {
        this.totalPost = totalPost;
    }

    /**
     * 
     * @return
     *     The products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * 
     * @param products
     *     The products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(currentPage).append(totalPage).append(postPerPage).append(totalPost).append(products).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Example) == false) {
            return false;
        }
        Example rhs = ((Example) other);
        return new EqualsBuilder().append(currentPage, rhs.currentPage).append(totalPage, rhs.totalPage).append(postPerPage, rhs.postPerPage).append(totalPost, rhs.totalPost).append(products, rhs.products).isEquals();
    }

}
