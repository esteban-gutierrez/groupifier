package com.bestsecret.groupifier.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "text_value", schema = "PRODUCT_ATTRIBUTES", catalog = "groupifier")
public class TextValueEntity {
    private int id;
    private int textPropId;
    private String name;
    private String description;
    private Date createdAt;
    private Date modifiedAt;
    private TextPropertyEntity textPropertyByTextPropId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text_prop_id", nullable = false)
    public int getTextPropId() {
        return textPropId;
    }

    public void setTextPropId(int textPropId) {
        this.textPropId = textPropId;
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
        TextValueEntity that = (TextValueEntity) o;
        return id == that.id &&
                textPropId == that.textPropId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(modifiedAt, that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textPropId, name, description, createdAt, modifiedAt);
    }

    @ManyToOne
    @JoinColumn(name = "text_prop_id", referencedColumnName = "id", nullable = false)
    public TextPropertyEntity getTextPropertyByTextPropId() {
        return textPropertyByTextPropId;
    }

    public void setTextPropertyByTextPropId(TextPropertyEntity textPropertyByTextPropId) {
        this.textPropertyByTextPropId = textPropertyByTextPropId;
    }
}
