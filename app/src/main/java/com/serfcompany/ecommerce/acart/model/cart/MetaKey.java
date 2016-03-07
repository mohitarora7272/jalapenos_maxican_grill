
package com.serfcompany.ecommerce.acart.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaKey {

    @SerializedName("hideit")
    @Expose
    private Integer hideit;
    @SerializedName("safari")
    @Expose
    private Integer safari;

    /**
     * 
     * @return
     *     The hideit
     */
    public Integer getHideit() {
        return hideit;
    }

    /**
     * 
     * @param hideit
     *     The hideit
     */
    public void setHideit(Integer hideit) {
        this.hideit = hideit;
    }

    /**
     * 
     * @return
     *     The safari
     */
    public Integer getSafari() {
        return safari;
    }

    /**
     * 
     * @param safari
     *     The safari
     */
    public void setSafari(Integer safari) {
        this.safari = safari;
    }

}
