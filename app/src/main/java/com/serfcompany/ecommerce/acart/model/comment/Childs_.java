
package com.serfcompany.ecommerce.acart.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Childs_ {

    @SerializedName("postID")
    @Expose
    private Integer postID;
    @SerializedName("main_comment")
    @Expose
    private MainComment_ mainComment;
    @SerializedName("comments")
    @Expose
    private List<Object> comments = new ArrayList<Object>();

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
     *     The mainComment
     */
    public MainComment_ getMainComment() {
        return mainComment;
    }

    /**
     * 
     * @param mainComment
     *     The main_comment
     */
    public void setMainComment(MainComment_ mainComment) {
        this.mainComment = mainComment;
    }

    /**
     * 
     * @return
     *     The comments
     */
    public List<Object> getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

}
