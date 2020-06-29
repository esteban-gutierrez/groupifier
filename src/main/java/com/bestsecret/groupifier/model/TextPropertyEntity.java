package com.bestsecret.groupifier.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "text_property", schema = "PRODUCT_ATTRIBUTES", catalog = "groupifier")
public class TextPropertyEntity {
    private Long id;
    private Long textCatId;
    private String name;
    private String description;
    private Date createdAt;
    private Date modifiedAt;
    private TextCategoryEntity textCategoryByTextCatId;
    private Collection<TextValueEntity> textValuesById;
    private Collection<TextpropsGroupMappingEntity> textpropsGroupMappingsById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text_cat_id", nullable = false)
    public Long getTextCatId() {
        return textCatId;
    }

    public void setTextCatId(Long textCatId) {
        this.textCatId = textCatId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
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
        TextPropertyEntity that = (TextPropertyEntity) o;
        return id == that.id &&
                textCatId == that.textCatId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(modifiedAt, that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textCatId, name, description, createdAt, modifiedAt);
    }

    @ManyToOne
    @JoinColumn(name = "text_cat_id", referencedColumnName = "id", nullable = false)
    public TextCategoryEntity getTextCategoryByTextCatId() {
        return textCategoryByTextCatId;
    }

    public void setTextCategoryByTextCatId(TextCategoryEntity textCategoryByTextCatId) {
        this.textCategoryByTextCatId = textCategoryByTextCatId;
    }

    @OneToMany(mappedBy = "textPropertyByTextPropId")
    public Collection<TextValueEntity> getTextValuesById() {
        return textValuesById;
    }

    public void setTextValuesById(Collection<TextValueEntity> textValuesById) {
        this.textValuesById = textValuesById;
    }

    @OneToMany(mappedBy = "textPropertyByTextPropId")
    public Collection<TextpropsGroupMappingEntity> getTextpropsGroupMappingsById() {
        return textpropsGroupMappingsById;
    }

    public void setTextpropsGroupMappingsById(Collection<TextpropsGroupMappingEntity> textpropsGroupMappingsById) {
        this.textpropsGroupMappingsById = textpropsGroupMappingsById;
    }
}
