package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextCategoryEntity;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TextCategoryPopulator {
    @Resource
    private TimeService timeService;

    public void populateMainData(TextCategoryEntity textCategory, TextCategoryEntity textCategoryMainData)
        throws PopulatorException {
        try {
            textCategory.setName(textCategoryMainData.getName());
            textCategory.setDescription(textCategoryMainData.getDescription());
            setCreatedAt(textCategory, textCategoryMainData);
            setModifiedAt(textCategory, textCategoryMainData);
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text category", e);
        }
    }

    private void setCreatedAt(TextCategoryEntity textCategory, TextCategoryEntity textCategoryMainData) {
        if (textCategoryMainData.getCreatedAt() != null) {
            textCategory.setCreatedAt(textCategoryMainData.getCreatedAt());
        } else if (textCategory.getCreatedAt() == null) {
            textCategory.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    private void setModifiedAt(TextCategoryEntity textCategory, TextCategoryEntity textCategoryMainData) {
        if (textCategoryMainData.getModifiedAt() != null) {
            textCategory.setCreatedAt(textCategoryMainData.getModifiedAt());
        } else {
            textCategory.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    public void populateAllData(TextCategoryEntity textCategory, TextCategoryEntity textCategoryData)
            throws PopulatorException {
        populateMainData(textCategory, textCategoryData);
        try {
            textCategory.setId(textCategoryData.getId());
            textCategory.setProductCatId(textCategoryData.getProductCatId());
        } catch (Exception e) {
            throw new PopulatorException("Error populating all data of text category", e);
        }
    }
}
