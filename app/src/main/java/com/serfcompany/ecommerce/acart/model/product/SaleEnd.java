
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SaleEnd {

    @SerializedName("unixtime")
    @Expose
    private String unixtime;
    @SerializedName("day")
    @Expose
    private Boolean day;
    @SerializedName("month")
    @Expose
    private Boolean month;
    @SerializedName("year")
    @Expose
    private Boolean year;
    @SerializedName("day_name")
    @Expose
    private Boolean dayName;
    @SerializedName("fulldate")
    @Expose
    private Boolean fulldate;

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
     *     The day
     */
    public Boolean getDay() {
        return day;
    }

    /**
     * 
     * @param day
     *     The day
     */
    public void setDay(Boolean day) {
        this.day = day;
    }

    /**
     * 
     * @return
     *     The month
     */
    public Boolean getMonth() {
        return month;
    }

    /**
     * 
     * @param month
     *     The month
     */
    public void setMonth(Boolean month) {
        this.month = month;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Boolean getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Boolean year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The dayName
     */
    public Boolean getDayName() {
        return dayName;
    }

    /**
     * 
     * @param dayName
     *     The day_name
     */
    public void setDayName(Boolean dayName) {
        this.dayName = dayName;
    }

    /**
     * 
     * @return
     *     The fulldate
     */
    public Boolean getFulldate() {
        return fulldate;
    }

    /**
     * 
     * @param fulldate
     *     The fulldate
     */
    public void setFulldate(Boolean fulldate) {
        this.fulldate = fulldate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(unixtime).append(day).append(month).append(year).append(dayName).append(fulldate).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SaleEnd) == false) {
            return false;
        }
        SaleEnd rhs = ((SaleEnd) other);
        return new EqualsBuilder().append(unixtime, rhs.unixtime).append(day, rhs.day).append(month, rhs.month).append(year, rhs.year).append(dayName, rhs.dayName).append(fulldate, rhs.fulldate).isEquals();
    }

}
