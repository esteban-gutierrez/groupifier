package com.bestsecret.groupifier.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "textprops_group_mapping", schema = "PRODUCT_ATTRIBUTES", catalog = "groupifier")
public class TextpropsGroupMappingEntity {
    private int groupId;
    private int textPropId;
    private Date createdAt;
    private Date modifiedAt;
    private TextPropGroupEntity textPropGroupByGroupId;
    private TextPropertyEntity textPropertyByTextPropId;

    @Basic
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
        TextpropsGroupMappingEntity that = (TextpropsGroupMappingEntity) o;
        return groupId == that.groupId &&
                textPropId == that.textPropId &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(modifiedAt, that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, textPropId, createdAt, modifiedAt);
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    public TextPropGroupEntity getTextPropGroupByGroupId() {
        return textPropGroupByGroupId;
    }

    public void setTextPropGroupByGroupId(TextPropGroupEntity textPropGroupByGroupId) {
        this.textPropGroupByGroupId = textPropGroupByGroupId;
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
