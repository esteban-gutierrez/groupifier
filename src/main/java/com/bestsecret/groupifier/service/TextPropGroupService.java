package com.bestsecret.groupifier.service;

import com.bestsecret.groupifier.model.TextPropGroupEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.populator.TextPropGroupPopulator;
import com.bestsecret.groupifier.repository.TextPropGroupRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class TextPropGroupService {
    @Resource
    private TextPropGroupRepository textPropGroupRepository;

    @Resource
    private TextPropGroupPopulator textPropGroupPopulator;

    public List<TextPropGroupEntity> getAllTextPropertyGroups() {
        return textPropGroupRepository.findAll();
    }

    public Optional<TextPropGroupEntity> getTextPropGroupById(Long textPropGroupId) {
        return textPropGroupRepository.findById(textPropGroupId);
    }

    public TextPropGroupEntity createTextPropGroup(TextPropGroupEntity textPropGroup) {
        return textPropGroupRepository.save(textPropGroup);
    }

    public TextPropGroupEntity updateTextPropGroupMainData(TextPropGroupEntity textPropGroupEntity,
                                                           TextPropGroupEntity textPropGroupMainData)
            throws PopulatorException {
        textPropGroupPopulator.populateMainData(textPropGroupEntity, textPropGroupMainData);
        textPropGroupRepository.save(textPropGroupEntity);
        return textPropGroupEntity;
    }

    public TextPropGroupEntity updateTextPropGroupAllData(TextPropGroupEntity textPropGroupEntity,
                                                          TextPropGroupEntity textPropGroupMainData)
            throws PopulatorException {
        textPropGroupPopulator.populateAllData(textPropGroupEntity, textPropGroupMainData);
        textPropGroupRepository.save(textPropGroupEntity);
        return textPropGroupEntity;
    }

    public void deleteTextPropGroup(TextPropGroupEntity textPropGroupEntity) {
        textPropGroupRepository.delete(textPropGroupEntity);
    }
}
