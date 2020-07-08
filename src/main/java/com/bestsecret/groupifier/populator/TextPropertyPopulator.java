package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextPropertyEntity;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TextPropertyPopulator {
    @Resource
    private TimeService timeService;

    public void populateMainData(TextPropertyEntity textProperty, TextPropertyEntity textPropertyMainData)
        throws PopulatorException {
        try {
            textProperty.setName(textPropertyMainData.getName());
            textProperty.setDescription(textPropertyMainData.getDescription());
            setCreatedAt(textProperty, textPropertyMainData);
            setModifiedAt(textProperty, textPropertyMainData);
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text property", e);
        }
    }

    private void setCreatedAt(TextPropertyEntity textProperty, TextPropertyEntity textPropertyMainData) {
        if (textPropertyMainData.getCreatedAt() != null) {
            textProperty.setCreatedAt(textPropertyMainData.getCreatedAt());
        } else if (textProperty.getCreatedAt() == null) {
            textProperty.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    private void setModifiedAt(TextPropertyEntity textProperty, TextPropertyEntity textPropertyMainData) {
        if (textPropertyMainData.getModifiedAt() != null) {
            textProperty.setCreatedAt(textPropertyMainData.getModifiedAt());
        } else {
            textProperty.setCreatedAt(timeService.getCurrentSQLDate());
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
