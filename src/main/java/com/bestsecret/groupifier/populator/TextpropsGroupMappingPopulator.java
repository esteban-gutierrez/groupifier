package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextpropsGroupMappingEntity;
import org.springframework.stereotype.Service;

@Service
public class TextpropsGroupMappingPopulator {
    public void populateMainData(TextpropsGroupMappingEntity textpropsGroupMapping, TextpropsGroupMappingEntity textpropsGroupMappingMainData)
        throws PopulatorException {
        try {
            textpropsGroupMapping.setGroupId(textpropsGroupMappingMainData.getGroupId());
            textpropsGroupMapping.setTextPropId(textpropsGroupMappingMainData.getTextPropId());
            if (textpropsGroupMappingMainData.getCreatedAt() != null) {
                textpropsGroupMapping.setCreatedAt(textpropsGroupMappingMainData.getCreatedAt());
            }
            if (textpropsGroupMappingMainData.getModifiedAt() != null) {
                textpropsGroupMapping.setCreatedAt(textpropsGroupMappingMainData.getModifiedAt());
            }
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text properties group mapping", e);
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
