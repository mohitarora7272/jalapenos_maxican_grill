
package com.serfcompany.ecommerce.acart.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Category {

    @SerializedName("term_id")
    @Expose
    private Integer termId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("term_group")
    @Expose
    private Integer termGroup;
    @SerializedName("term_taxonomy_id")
    @Expose
    private Integer termTaxonomyId;
    @SerializedName("taxonomy")
    @Expose
    private String taxonomy;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("filter")
    @Expose
    private String filter;

    /**
     * 
     * @return
     *     The termId
     */
    public Integer getTermId() {
        return termId;
    }

    /**
     * 
     * @param termId
     *     The term_id
     */
    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 
     * @param slug
     *     The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 
     * @return
     *     The termGroup
     */
    public Integer getTermGroup() {
        return termGroup;
    }

    /**
     * 
     * @param termGroup
     *     The term_group
     */
    public void setTermGroup(Integer termGroup) {
        this.termGroup = termGroup;
    }

    /**
     * 
     * @return
     *     The termTaxonomyId
     */
    public Integer getTermTaxonomyId() {
        return termTaxonomyId;
    }

    /**
     * 
     * @param termTaxonomyId
     *     The term_taxonomy_id
     */
    public void setTermTaxonomyId(Integer termTaxonomyId) {
        this.termTaxonomyId = termTaxonomyId;
    }

    /**
     * 
     * @return
     *     The taxonomy
     */
    public String getTaxonomy() {
        return taxonomy;
    }

    /**
     * 
     * @param taxonomy
     *     The taxonomy
     */
    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The parent
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * 
     * @param parent
     *     The parent
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * 
     * @param filter
     *     The filter
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(termId).append(name).append(slug).append(termGroup).append(termTaxonomyId).append(taxonomy).append(description).append(parent).append(count).append(filter).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Category) == false) {
            return false;
        }
        Category rhs = ((Category) other);
        return new EqualsBuilder().append(termId, rhs.termId).append(name, rhs.name).append(slug, rhs.slug).append(termGroup, rhs.termGroup).append(termTaxonomyId, rhs.termTaxonomyId).append(taxonomy, rhs.taxonomy).append(description, rhs.description).append(parent, rhs.parent).append(count, rhs.count).append(filter, rhs.filter).isEquals();
    }

}
