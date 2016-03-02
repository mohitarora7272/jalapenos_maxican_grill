
package com.serfcompany.ecommerce.acart.model.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Notification {

    @SerializedName("logged")
    @Expose
    private Boolean logged;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_page")
    @Expose
    private Integer totalPage;
    @SerializedName("postPerPage")
    @Expose
    private Integer postPerPage;
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;
    @SerializedName("total_post")
    @Expose
    private Integer totalPost;
    @SerializedName("data")
    @Expose
    private List<NotificationData> data = new ArrayList<NotificationData>();

    /**
     * 
     * @return
     *     The logged
     */
    public Boolean getLogged() {
        return logged;
    }

    /**
     * 
     * @param logged
     *     The logged
     */
    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    /**
     * 
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    public void setPage(Integer page) {
        this.page = page;
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
     *     The postPerPage
     */
    public void setPostPerPage(Integer postPerPage) {
        this.postPerPage = postPerPage;
    }

    /**
     * 
     * @return
     *     The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * 
     * @param pageCount
     *     The page_count
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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
     *     The data
     */
    public List<NotificationData> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<NotificationData> data) {
        this.data = data;
    }

}
