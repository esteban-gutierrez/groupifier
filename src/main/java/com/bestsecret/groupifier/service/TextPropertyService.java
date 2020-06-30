package com.bestsecret.groupifier.service;

import com.bestsecret.groupifier.model.TextPropertyEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.populator.TextPropertyPopulator;
import com.bestsecret.groupifier.repository.TextPropertyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class TextPropertyService {
    @Resource
    private TextPropertyRepository textPropertyRepository;

    @Resource
    private TextPropertyPopulator textPropertyPopulator;

    public List<TextPropertyEntity> getAllTextProperties() {
        return textPropertyRepository.findAll();
    }

    public Optional<TextPropertyEntity> getTextPropertyById(Long textPropertyId) {
        return textPropertyRepository.findById(textPropertyId);
    }

    public TextPropertyEntity createTextProperty(TextPropertyEntity textProperty) {
        return textPropertyRepository.save(textProperty);
    }

    public TextPropertyEntity updateTextPropertyMainData(TextPropertyEntity textPropertyEntity,
                                                         TextPropertyEntity textPropertyMainData)
            throws PopulatorException {
        textPropertyPopulator.populateMainData(textPropertyEntity, textPropertyMainData);
        textPropertyRepository.save(textPropertyEntity);
        return textPropertyEntity;
    }

    public TextPropertyEntity updateTextPropertyAllData(TextPropertyEntity textPropertyEntity,
                                                        TextPropertyEntity textPropertyMainData)
            throws PopulatorException {
        textPropertyPopulator.populateAllData(textPropertyEntity, textPropertyMainData);
        textPropertyRepository.save(textPropertyEntity);
        return textPropertyEntity;
    }

    public void deleteTextProperty(TextPropertyEntity textPropertyEntity) {
        textPropertyRepository.delete(textPropertyEntity);
    }
}
