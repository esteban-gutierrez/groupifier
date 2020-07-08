package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextpropsGroupMappingEntity;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TextpropsGroupMappingPopulator {
    @Resource
    private TimeService timeService;

    public void populateMainData(TextpropsGroupMappingEntity textpropsGroupMapping, TextpropsGroupMappingEntity textpropsGroupMappingMainData)
        throws PopulatorException {
        try {
            textpropsGroupMapping.setGroupId(textpropsGroupMappingMainData.getGroupId());
            textpropsGroupMapping.setTextPropId(textpropsGroupMappingMainData.getTextPropId());
            setCreatedAt(textpropsGroupMapping, textpropsGroupMappingMainData);
            setModifiedAt(textpropsGroupMapping, textpropsGroupMappingMainData);
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text properties group mapping", e);
        }
    }

    private void setCreatedAt(TextpropsGroupMappingEntity textpropsGroupMapping, TextpropsGroupMappingEntity textpropsGroupMappingMainData) {
        if (textpropsGroupMappingMainData.getCreatedAt() != null) {
            textpropsGroupMapping.setCreatedAt(textpropsGroupMappingMainData.getCreatedAt());
        } else if (textpropsGroupMapping.getCreatedAt() == null) {
            textpropsGroupMapping.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    private void setModifiedAt(TextpropsGroupMappingEntity textpropsGroupMapping, TextpropsGroupMappingEntity textpropsGroupMappingMainData) {
        if (textpropsGroupMappingMainData.getModifiedAt() != null) {
            textpropsGroupMapping.setCreatedAt(textpropsGroupMappingMainData.getModifiedAt());
        } else {
            textpropsGroupMapping.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    public void populateAllData(TextpropsGroupMappingEntity textpropsGroupMapping, TextpropsGroupMappingEntity textpropsGroupMappingData)
            throws PopulatorException {
        populateMainData(textpropsGroupMapping, textpropsGroupMappingData);
        try {
            textpropsGroupMapping.setId(textpropsGroupMappingData.getId());
        } catch (Exception e) {
            throw new PopulatorException("Error populating all data of text properties group mapping", e);
        }
    }
}
