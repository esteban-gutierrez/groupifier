package com.bestsecret.groupifier.repository;

import com.bestsecret.groupifier.model.TextPropGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextPropGroupRepository extends JpaRepository<TextPropGroupEntity, Long> {
}
