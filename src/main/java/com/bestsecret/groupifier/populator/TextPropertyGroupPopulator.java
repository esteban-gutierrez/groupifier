package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextPropGroupEntity;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TextPropertyGroupPopulator {
    @Resource
    private TimeService timeService;

    public void populateMainData(TextPropGroupEntity textPropGroup, TextPropGroupEntity textPropGroupMainData)
        throws PopulatorException {
        try {
            textPropGroup.setName(textPropGroupMainData.getName());
            textPropGroup.setDescription(textPropGroupMainData.getDescription());
            setCreatedAt(textPropGroup, textPropGroupMainData);
            setModifiedAt(textPropGroup, textPropGroupMainData);
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text property group", e);
        }
    }

    private void setCreatedAt(TextPropGroupEntity textPropGroup, TextPropGroupEntity textPropGroupMainData) {
        if (textPropGroupMainData.getCreatedAt() != null) {
            textPropGroup.setCreatedAt(textPropGroupMainData.getCreatedAt());
        } else if (textPropGroup.getCreatedAt() == null) {
            textPropGroup.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    private void setModifiedAt(TextPropGroupEntity textPropGroup, TextPropGroupEntity textPropGroupMainData) {
        if (textPropGroupMainData.getModifiedAt() != null) {
            textPropGroup.setCreatedAt(textPropGroupMainData.getModifiedAt());
        } else {
            textPropGroup.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    public void populateAllData(TextPropGroupEntity textPropGroup, TextPropGroupEntity textPropGroupData)
            throws PopulatorException {
        populateMainData(textPropGroup, textPropGroupData);
        try {
            textPropGroup.setId(textPropGroupData.getId());
        } catch (Exception e) {
            throw new PopulatorException("Error populating all data of text property group", e);
        }
    }
}
