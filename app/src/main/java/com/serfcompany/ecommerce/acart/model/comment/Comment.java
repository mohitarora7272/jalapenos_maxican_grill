
package com.serfcompany.ecommerce.acart.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    @SerializedName("postID")
    @Expose
    private Integer postID;
    @SerializedName("comments")
    @Expose
    private List<Comment_> comments = new ArrayList<Comment_>();

    /**
     * 
     * @return
     *     The postID
     */
    public Integer getPostID() {
        return postID;
    }

    /**
     * 
     * @param postID
     *     The postID
     */
    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    /**
     * 
     * @return
     *     The comments
     */
    public List<Comment_> getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    public void setComments(List<Comment_> comments) {
        this.comments = comments;
    }

}
