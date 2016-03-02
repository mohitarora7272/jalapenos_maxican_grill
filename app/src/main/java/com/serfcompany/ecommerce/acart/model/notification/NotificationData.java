
package com.serfcompany.ecommerce.acart.model.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationData {

    @SerializedName("pushed_message")
    @Expose
    private String pushedMessage;
    @SerializedName("unixtime")
    @Expose
    private String unixtime;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("ago")
    @Expose
    private String ago;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("objectID")
    @Expose
    private String objectID;

    /**
     * 
     * @return
     *     The pushedMessage
     */
    public String getPushedMessage() {
        return pushedMessage;
    }

    /**
     * 
     * @param pushedMessage
     *     The pushed_message
     */
    public void setPushedMessage(String pushedMessage) {
        this.pushedMessage = pushedMessage;
    }

    /**
     * 
     * @return
     *     The unixtime
     */
    public String getUnixtime() {
        return unixtime;
    }

    /**
     * 
     * @param unixtime
     *     The unixtime
     */
    public void setUnixtime(String unixtime) {
        this.unixtime = unixtime;
    }

    /**
     * 
     * @return
     *     The dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * 
     * @param dateTime
     *     The date_time
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The objectID
     */
    public String getObjectID() {
        return objectID;
    }

    /**
     * 
     * @param objectID
     *     The objectID
     */
    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

}
