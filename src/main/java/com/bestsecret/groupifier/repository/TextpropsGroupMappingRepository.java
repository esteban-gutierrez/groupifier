package com.bestsecret.groupifier.repository;

import com.bestsecret.groupifier.model.TextpropsGroupMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextpropsGroupMappingRepository extends JpaRepository<TextpropsGroupMappingEntity, Long> {
}
