
package com.serfcompany.ecommerce.acart.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Childs {

    @SerializedName("postID")
    @Expose
    private Integer postID;
    @SerializedName("main_comment")
    @Expose
    private MainComment mainComment;
    @SerializedName("comments")
    @Expose
    private List<Comment__> comments = new ArrayList<Comment__>();

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
    public MainComment getMainComment() {
        return mainComment;
    }

    /**
     * 
     * @param mainComment
     *     The main_comment
     */
    public void setMainComment(MainComment mainComment) {
        this.mainComment = mainComment;
    }

    /**
     * 
     * @return
     *     The comments
     */
    public List<Comment__> getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    public void setComments(List<Comment__> comments) {
        this.comments = comments;
    }

}
