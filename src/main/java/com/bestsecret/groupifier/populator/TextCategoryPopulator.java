package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.TextCategoryEntity;
import org.springframework.stereotype.Service;

@Service
public class TextCategoryPopulator {
    public void populateMainData(TextCategoryEntity textCategory, TextCategoryEntity textCategoryMainData)
        throws PopulatorException {
        try {
            textCategory.setName(textCategoryMainData.getName());
            textCategory.setDescription(textCategoryMainData.getDescription());
            if (textCategoryMainData.getCreatedAt() != null) {
                textCategory.setCreatedAt(textCategoryMainData.getCreatedAt());
            }
            if (textCategoryMainData.getModifiedAt() != null) {
                textCategory.setCreatedAt(textCategoryMainData.getModifiedAt());
            }
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of text category", e);
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
