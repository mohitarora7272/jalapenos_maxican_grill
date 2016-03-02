
package com.serfcompany.ecommerce.acart.model.category;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("term_id")
    @Expose
    private Integer termId;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("category_parent")
    @Expose
    private Integer categoryParent;
    @SerializedName("post_count")
    @Expose
    private Integer postCount;
    @SerializedName("children")
    @Expose
    private List<Category> children = new ArrayList<Category>();

    /**
     * 
     * @return
     *     The termId
     */
    public Integer getTermId() {
        return termId;
    }

    /**
     * 
     * @param termId
     *     The term_id
     */
    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    /**
     * 
     * @return
     *     The thumb
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * 
     * @param thumb
     *     The thumb
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 
     * @param slug
     *     The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 
     * @return
     *     The categoryParent
     */
    public Integer getCategoryParent() {
        return categoryParent;
    }

    /**
     * 
     * @param categoryParent
     *     The category_parent
     */
    public void setCategoryParent(Integer categoryParent) {
        this.categoryParent = categoryParent;
    }

    /**
     * 
     * @return
     *     The postCount
     */
    public Integer getPostCount() {
        return postCount;
    }

    /**
     * 
     * @param postCount
     *     The post_count
     */
    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    /**
     * 
     * @return
     *     The children
     */
    public List<Category> getChildren() {
        return children;
    }

    /**
     * 
     * @param children
     *     The children
     */
    public void setChildren(List<Category> children) {
        this.children = children;
    }

}
