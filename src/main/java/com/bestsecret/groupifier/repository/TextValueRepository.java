package com.bestsecret.groupifier.repository;

import com.bestsecret.groupifier.model.TextValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextValueRepository extends JpaRepository<TextValueEntity, Long> {
}
