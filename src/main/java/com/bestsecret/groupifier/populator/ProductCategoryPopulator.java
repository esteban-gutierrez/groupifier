package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.ProductCategoryEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryPopulator {
    public void populateMainData(ProductCategoryEntity productCategory, ProductCategoryEntity productCategoryMainData)
        throws PopulatorException {
        try {
            productCategory.setName(productCategoryMainData.getName());
            productCategory.setDescription(productCategoryMainData.getDescription());
            if (productCategoryMainData.getCreatedAt() != null) {
                productCategory.setCreatedAt(productCategoryMainData.getCreatedAt());
            }
            if (productCategoryMainData.getModifiedAt() != null) {
                productCategory.setCreatedAt(productCategoryMainData.getModifiedAt());
            }
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of product category", e);
        }
    }

    public void populateAllData(ProductCategoryEntity productCategory, ProductCategoryEntity productCategoryData)
            throws PopulatorException {
        populateMainData(productCategory, productCategoryData);
        try {
            productCategory.setId(productCategoryData.getId());
            productCategory.setParentId(productCategoryData.getParentId());
        } catch (Exception e) {
            throw new PopulatorException("Error populating all data of product category", e);
        }
    }
}
