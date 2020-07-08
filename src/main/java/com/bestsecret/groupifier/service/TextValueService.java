package com.bestsecret.groupifier.service;

import com.bestsecret.groupifier.model.TextValueEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.populator.TextValuePopulator;
import com.bestsecret.groupifier.repository.TextValueRepository;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class TextValueService {
    @Resource
    private TextValueRepository textValueRepository;

    @Resource
    private TextValuePopulator textValuePopulator;

    @Resource
    private TimeService timeService;

    public List<TextValueEntity> getAllTextValues() {
        return textValueRepository.findAll();
    }

    public Optional<TextValueEntity> getTextValueById(Long textValueId) {
        return textValueRepository.findById(textValueId);
    }

    public TextValueEntity createTextValue(TextValueEntity textValue) {
        textValue.setCreatedAt(timeService.getCurrentSQLDate());
        textValue.setModifiedAt(timeService.getCurrentSQLDate());
        return textValueRepository.save(textValue);
    }

    public TextValueEntity updateTextValueMainData(TextValueEntity textValueEntity,
                                                   TextValueEntity textValueMainData)
            throws PopulatorException {
        textValuePopulator.populateMainData(textValueEntity, textValueMainData);
        textValueRepository.save(textValueEntity);
        return textValueEntity;
    }

    public TextValueEntity updateTextValueAllData(TextValueEntity textValueEntity,
                                                  TextValueEntity textValueMainData)
            throws PopulatorException {
        textValuePopulator.populateAllData(textValueEntity, textValueMainData);
        textValueRepository.save(textValueEntity);
        return textValueEntity;
    }

    public void deleteTextValue(TextValueEntity textValueEntity) {
        textValueRepository.delete(textValueEntity);
    }
}
