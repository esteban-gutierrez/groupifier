package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextValueEntity;
import org.springframework.stereotype.Service;

@Service
public class TextValuePopulator {
    public void populateMainData(TextValueEntity textValue, TextValueEntity textValueMainData)
        throws PopulatorException {
        try {
            textValue.setName(textValueMainData.getName());
            textValue.setDescription(textValueMainData.getDescription());
            textValue.setTextPropId(textValueMainData.getTextPropId());
            if (textValueMainData.getCreatedAt() != null) {
                textValue.setCreatedAt(textValueMainData.getCreatedAt());
            }
            if (textValueMainData.getModifiedAt() != null) {
                textValue.setCreatedAt(textValueMainData.getModifiedAt());
            }
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text value", e);
        }
    }

    public void populateAllData(TextValueEntity textValue, TextValueEntity textValueData)
            throws PopulatorException {
        populateMainData(textValue, textValueData);
        try {
            textValue.setId(textValueData.getId());
        } catch (Exception e) {
            throw new PopulatorException("Error populating all data of text value", e);
        }
    }
}
