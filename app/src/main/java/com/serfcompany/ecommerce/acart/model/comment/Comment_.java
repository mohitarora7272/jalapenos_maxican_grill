
package com.serfcompany.ecommerce.acart.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment_ {

    @SerializedName("comment_id")
    @Expose
    private Integer commentId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("comment_author_IP")
    @Expose
    private String commentAuthorIP;
    @SerializedName("unixtime")
    @Expose
    private Integer unixtime;
    @SerializedName("servertime")
    @Expose
    private Integer servertime;
    @SerializedName("ago")
    @Expose
    private String ago;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("agent")
    @Expose
    private String agent;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("childs")
    @Expose
    private Childs childs;

    /**
     * 
     * @return
     *     The commentId
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 
     * @param commentId
     *     The comment_id
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The commentAuthorIP
     */
    public String getCommentAuthorIP() {
        return commentAuthorIP;
    }

    /**
     * 
     * @param commentAuthorIP
     *     The comment_author_IP
     */
    public void setCommentAuthorIP(String commentAuthorIP) {
        this.commentAuthorIP = commentAuthorIP;
    }

    /**
     * 
     * @return
     *     The unixtime
     */
    public Integer getUnixtime() {
        return unixtime;
    }

    /**
     * 
     * @param unixtime
     *     The unixtime
     */
    public void setUnixtime(Integer unixtime) {
        this.unixtime = unixtime;
    }

    /**
     * 
     * @return
     *     The servertime
     */
    public Integer getServertime() {
        return servertime;
    }

    /**
     * 
     * @param servertime
     *     The servertime
     */
    public void setServertime(Integer servertime) {
        this.servertime = servertime;
    }

    /**
     * 
     * @return
     *     The ago
     */
    public String getAgo() {
        return ago;
    }

    /**
     * 
     * @param ago
     *     The ago
     */
    public void setAgo(String ago) {
        this.ago = ago;
    }

    /**
     * 
     * @return
     *     The parent
     */
    public String getParent() {
        return parent;
    }

    /**
     * 
     * @param parent
     *     The parent
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * 
     * @return
     *     The agent
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 
     * @param agent
     *     The agent
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The childs
     */
    public Childs getChilds() {
        return childs;
    }

    /**
     * 
     * @param childs
     *     The childs
     */
    public void setChilds(Childs childs) {
        this.childs = childs;
    }

}
