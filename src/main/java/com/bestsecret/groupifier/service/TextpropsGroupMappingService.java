package com.bestsecret.groupifier.service;

import com.bestsecret.groupifier.model.TextpropsGroupMappingEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.populator.TextpropsGroupMappingPopulator;
import com.bestsecret.groupifier.repository.TextpropsGroupMappingRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class TextpropsGroupMappingService {
    @Resource
    private TextpropsGroupMappingRepository textpropsGroupMappingRepository;

    @Resource
    private TextpropsGroupMappingPopulator textpropsGroupMappingPopulator;

    public List<TextpropsGroupMappingEntity> getAllTextpropsGroupMappings() {
        return textpropsGroupMappingRepository.findAll();
    }

    public Optional<TextpropsGroupMappingEntity> getTextpropsGroupMappingById(Long textpropsGroupMappingId) {
        return textpropsGroupMappingRepository.findById(textpropsGroupMappingId);
    }

    public TextpropsGroupMappingEntity createTextpropsGroupMapping(TextpropsGroupMappingEntity textpropsGroupMapping) {
        return textpropsGroupMappingRepository.save(textpropsGroupMapping);
    }

    public TextpropsGroupMappingEntity updateTextpropsGroupMappingMainData(
                TextpropsGroupMappingEntity textpropsGroupMappingEntity,
                TextpropsGroupMappingEntity textpropsGroupMappingMainData)
            throws PopulatorException {
        textpropsGroupMappingPopulator.populateMainData(textpropsGroupMappingEntity, textpropsGroupMappingMainData);
        textpropsGroupMappingRepository.save(textpropsGroupMappingEntity);
        return textpropsGroupMappingEntity;
    }

    public TextpropsGroupMappingEntity updateTextpropsGroupMappingAllData(
                TextpropsGroupMappingEntity textpropsGroupMappingEntity,
                TextpropsGroupMappingEntity textpropsGroupMappingMainData)
            throws PopulatorException {
        textpropsGroupMappingPopulator.populateAllData(textpropsGroupMappingEntity, textpropsGroupMappingMainData);
        textpropsGroupMappingRepository.save(textpropsGroupMappingEntity);
        return textpropsGroupMappingEntity;
    }

    public void deleteTextpropsGroupMapping(TextpropsGroupMappingEntity textpropsGroupMappingEntity) {
        textpropsGroupMappingRepository.delete(textpropsGroupMappingEntity);
    }
}
