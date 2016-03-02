
package com.serfcompany.ecommerce.acart.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegistered {

    @SerializedName("db_format")
    @Expose
    private String dbFormat;
    @SerializedName("unixtime")
    @Expose
    private Integer unixtime;
    @SerializedName("servertime")
    @Expose
    private Integer servertime;
    @SerializedName("ago")
    @Expose
    private String ago;

    /**
     * 
     * @return
     *     The dbFormat
     */
    public String getDbFormat() {
        return dbFormat;
    }

    /**
     * 
     * @param dbFormat
     *     The db_format
     */
    public void setDbFormat(String dbFormat) {
        this.dbFormat = dbFormat;
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

}
