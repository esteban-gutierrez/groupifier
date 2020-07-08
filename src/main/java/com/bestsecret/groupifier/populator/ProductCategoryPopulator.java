package com.bestsecret.groupifier.populator;

import com.bestsecret.groupifier.model.ProductCategoryEntity;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductCategoryPopulator {
    @Resource
    private TimeService timeService;

    public void populateMainData(ProductCategoryEntity productCategory, ProductCategoryEntity productCategoryMainData)
        throws PopulatorException {
        try {
            productCategory.setName(productCategoryMainData.getName());
            productCategory.setDescription(productCategoryMainData.getDescription());
            setCreatedAt(productCategory, productCategoryMainData);
            setModifiedAt(productCategory, productCategoryMainData);
        } catch (Exception e) {
            throw new PopulatorException("Error populating main data of product category", e);
        }
    }

    private void setCreatedAt(ProductCategoryEntity productCategory, ProductCategoryEntity productCategoryMainData) {
        if (productCategoryMainData.getCreatedAt() != null) {
            productCategory.setCreatedAt(productCategoryMainData.getCreatedAt());
        } else if (productCategory.getCreatedAt() == null) {
            productCategory.setCreatedAt(timeService.getCurrentSQLDate());
        }
    }

    private void setModifiedAt(ProductCategoryEntity productCategory, ProductCategoryEntity productCategoryMainData) {
        if (productCategoryMainData.getModifiedAt() != null) {
            productCategory.setCreatedAt(productCategoryMainData.getModifiedAt());
        } else {
            productCategory.setCreatedAt(timeService.getCurrentSQLDate());
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
