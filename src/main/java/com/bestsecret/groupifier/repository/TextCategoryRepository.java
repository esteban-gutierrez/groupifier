package com.bestsecret.groupifier.repository;

import com.bestsecret.groupifier.model.TextCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextCategoryRepository extends JpaRepository<TextCategoryEntity, Long> {
}
