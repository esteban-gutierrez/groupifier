package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextPropertyEntity;
import org.springframework.stereotype.Service;

@Service
public class TextPropertyPopulator {
    public void populateMainData(TextPropertyEntity textProperty, TextPropertyEntity textPropertyMainData)
        throws PopulatorException {
        try {
            textProperty.setName(textPropertyMainData.getName());
            textProperty.setDescription(textPropertyMainData.getDescription());
            if (textPropertyMainData.getCreatedAt() != null) {
                textProperty.setCreatedAt(textPropertyMainData.getCreatedAt());
            }
            if (textPropertyMainData.getModifiedAt() != null) {
                textProperty.setCreatedAt(textPropertyMainData.getModifiedAt());
            }
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text property", e);
        }
    }

    public void populateAllData(TextPropertyEntity textProperty, TextPropertyEntity textPropertyData)
            throws PopulatorException {
        populateMainData(textProperty, textPropertyData);
        try {
            textProperty.setId(textPropertyData.getId());
            textProperty.setTextCatId(textPropertyData.getTextCatId());
        } catch (Exception e) {
            throw new PopulatorException("Error populating all data of text property", e);
        }
    }
}
