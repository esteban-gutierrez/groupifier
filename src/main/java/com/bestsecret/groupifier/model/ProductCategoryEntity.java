package com.bestsecret.groupifier.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product_category", schema = "PRODUCT_ATTRIBUTES", catalog = "groupifier")
public class ProductCategoryEntity {
    private Long id;
    private Long parentId;
    private String name;
    private String description;
    private Date createdAt;
    private Date modifiedAt;
    private ProductCategoryEntity productCategoryByParentId;
    private Collection<ProductCategoryEntity> productCategoriesById;
    private Collection<TextCategoryEntity> textCategoriesById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "modified_at", nullable = false)
    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryEntity that = (ProductCategoryEntity) o;
        return id == that.id &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(modifiedAt, that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name, description, createdAt, modifiedAt);
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public ProductCategoryEntity getProductCategoryByParentId() {
        return productCategoryByParentId;
    }

    public void setProductCategoryByParentId(ProductCategoryEntity productCategoryByParentId) {
        this.productCategoryByParentId = productCategoryByParentId;
    }

    @OneToMany(mappedBy = "productCategoryByParentId")
    public Collection<ProductCategoryEntity> getProductCategoriesById() {
        return productCategoriesById;
    }

    public void setProductCategoriesById(Collection<ProductCategoryEntity> productCategoriesById) {
        this.productCategoriesById = productCategoriesById;
    }

    @OneToMany(mappedBy = "productCategoryByProductCatId")
    public Collection<TextCategoryEntity> getTextCategoriesById() {
        return textCategoriesById;
    }

    public void setTextCategoriesById(Collection<TextCategoryEntity> textCategoriesById) {
        this.textCategoriesById = textCategoriesById;
    }
}
