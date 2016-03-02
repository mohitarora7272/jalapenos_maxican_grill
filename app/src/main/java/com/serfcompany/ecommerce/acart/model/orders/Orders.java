
package com.serfcompany.ecommerce.acart.model.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Orders {

    @SerializedName("total_post")
    @Expose
    private Integer totalPost;
    @SerializedName("filter")
    @Expose
    private String filter;
    @SerializedName("my_order")
    @Expose
    private List<MyOrder> myOrder = new ArrayList<MyOrder>();

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
     *     The filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * 
     * @param filter
     *     The filter
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * 
     * @return
     *     The myOrder
     */
    public List<MyOrder> getMyOrder() {
        return myOrder;
    }

    /**
     * 
     * @param myOrder
     *     The my_order
     */
    public void setMyOrder(List<MyOrder> myOrder) {
        this.myOrder = myOrder;
    }

}
