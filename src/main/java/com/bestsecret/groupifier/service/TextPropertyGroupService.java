package com.bestsecret.groupifier.service;

import com.bestsecret.groupifier.model.TextPropGroupEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.populator.TextPropertyGroupPopulator;
import com.bestsecret.groupifier.repository.TextPropertyGroupRepository;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class TextPropertyGroupService {
    @Resource
    private TextPropertyGroupRepository textPropertyGroupRepository;

    @Resource
    private TextPropertyGroupPopulator textPropertyGroupPopulator;

    @Resource
    private TimeService timeService;

    public List<TextPropGroupEntity> getAllTextPropertyGroups() {
        return textPropertyGroupRepository.findAll();
    }

    public Optional<TextPropGroupEntity> getTextPropGroupById(Long textPropGroupId) {
        return textPropertyGroupRepository.findById(textPropGroupId);
    }

    public TextPropGroupEntity createTextPropGroup(TextPropGroupEntity textPropGroup) {
        textPropGroup.setCreatedAt(timeService.getCurrentSQLDate());
        textPropGroup.setModifiedAt(timeService.getCurrentSQLDate());
        return textPropertyGroupRepository.save(textPropGroup);
    }

    public TextPropGroupEntity updateTextPropGroupMainData(TextPropGroupEntity textPropGroupEntity,
                                                           TextPropGroupEntity textPropGroupMainData)
            throws PopulatorException {
        textPropertyGroupPopulator.populateMainData(textPropGroupEntity, textPropGroupMainData);
        textPropertyGroupRepository.save(textPropGroupEntity);
        return textPropGroupEntity;
    }

    public TextPropGroupEntity updateTextPropGroupAllData(TextPropGroupEntity textPropGroupEntity,
                                                          TextPropGroupEntity textPropGroupMainData)
            throws PopulatorException {
        textPropertyGroupPopulator.populateAllData(textPropGroupEntity, textPropGroupMainData);
        textPropertyGroupRepository.save(textPropGroupEntity);
        return textPropGroupEntity;
    }

    public void deleteTextPropGroup(TextPropGroupEntity textPropGroupEntity) {
        textPropertyGroupRepository.delete(textPropGroupEntity);
    }
}
