
package com.serfcompany.ecommerce.acart.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author__ {

    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("author_id")
    @Expose
    private Integer authorId;
    @SerializedName("author_name")
    @Expose
    private String authorName;

    /**
     * 
     * @return
     *     The avatar
     */
    public Object getAvatar() {
        return avatar;
    }

    /**
     * 
     * @param avatar
     *     The avatar
     */
    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * @return
     *     The authorId
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 
     * @param authorId
     *     The author_id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 
     * @return
     *     The authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 
     * @param authorName
     *     The author_name
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
