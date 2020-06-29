package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextPropGroupEntity;
import org.springframework.stereotype.Service;

@Service
public class TextPropGroupPopulator {
    public void populateMainData(TextPropGroupEntity textPropGroup, TextPropGroupEntity textPropGroupMainData)
        throws PopulatorException {
        try {
            textPropGroup.setName(textPropGroupMainData.getName());
            textPropGroup.setDescription(textPropGroupMainData.getDescription());
            if (textPropGroupMainData.getCreatedAt() != null) {
                textPropGroup.setCreatedAt(textPropGroupMainData.getCreatedAt());
            }
            if (textPropGroupMainData.getModifiedAt() != null) {
                textPropGroup.setCreatedAt(textPropGroupMainData.getModifiedAt());
            }
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text property group", e);
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
