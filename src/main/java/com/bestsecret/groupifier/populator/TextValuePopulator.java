package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextValueEntity;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TextValuePopulator {
    @Resource
    private TimeService timeService;

    public void populateMainData(TextValueEntity textValue, TextValueEntity textValueMainData)
        throws PopulatorException {
        try {
            textValue.setName(textValueMainData.getName());
            textValue.setDescription(textValueMainData.getDescription());
            textValue.setTextPropId(textValueMainData.getTextPropId());
            setCreatedAt(textValue, textValueMainData);
            setModifiedAt(textValue, textValueMainData);
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text value", e);
        }
    }

    private void setCreatedAt(TextValueEntity textValue, TextValueEntity textValueMainData) {
        if (textValueMainData.getCreatedAt() != null) {
            textValue.setCreatedAt(textValueMainData.getCreatedAt());
        } else if (textValue.getCreatedAt() == null) {
            textValue.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    private void setModifiedAt(TextValueEntity textValue, TextValueEntity textValueMainData) {
        if (textValueMainData.getModifiedAt() != null) {
            textValue.setCreatedAt(textValueMainData.getModifiedAt());
        } else {
            textValue.setCreatedAt(timeService.getCurrentSQLDate());
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
