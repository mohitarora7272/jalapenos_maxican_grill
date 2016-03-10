
package com.serfcompany.ecommerce.acart.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscountAmmount {

    @SerializedName("2016map")
    @Expose
    private Double _2016map;

    /**
     *
     * @return
     *     The _2016map
     */
    public Double get2016map() {
        return _2016map;
    }

    /**
     *
     * @param _2016map
     *     The 2016map
     */
    public void set2016map(Double _2016map) {
        this._2016map = _2016map;
    }

}
