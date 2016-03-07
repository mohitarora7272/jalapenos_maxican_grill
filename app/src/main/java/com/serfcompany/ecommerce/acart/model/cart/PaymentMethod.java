
package com.serfcompany.ecommerce.acart.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentMethod {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("meta_key")
    @Expose
    private MetaKey metaKey;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * 
     * @param Description
     *     The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * 
     * @return
     *     The metaKey
     */
    public MetaKey getMetaKey() {
        return metaKey;
    }

    /**
     * 
     * @param metaKey
     *     The meta_key
     */
    public void setMetaKey(MetaKey metaKey) {
        this.metaKey = metaKey;
    }

}
