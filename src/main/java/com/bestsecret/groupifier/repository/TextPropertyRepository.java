package com.bestsecret.groupifier.repository;

import com.bestsecret.groupifier.model.TextPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextPropertyRepository extends JpaRepository<TextPropertyEntity, Long> {
}
