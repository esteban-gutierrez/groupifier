package com.bestsecret.groupifier.service;

import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.model.ProductCategoryEntity;
import com.bestsecret.groupifier.populator.ProductCategoryPopulator;
import com.bestsecret.groupifier.repository.ProductCategoryRepository;
import com.bestsecret.groupifier.util.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Resource
    private ProductCategoryRepository productCategoryRepository;

    @Resource
    private ProductCategoryPopulator productCategoryPopulator;

    @Resource
    private TimeService timeService;

    public List<ProductCategoryEntity> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategoryEntity> getProductCategoryById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId);
    }

    public ProductCategoryEntity createProductCategory(ProductCategoryEntity productCategory) {
        productCategory.setCreatedAt(timeService.getCurrentSQLDate());
        productCategory.setModifiedAt(timeService.getCurrentSQLDate());
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategoryEntity updateProductCategoryMainData(ProductCategoryEntity productCategoryEntity,
                                                               ProductCategoryEntity productCategoryMainData)
            throws PopulatorException {
        productCategoryPopulator.populateMainData(productCategoryEntity, productCategoryMainData);
        productCategoryRepository.save(productCategoryEntity);
        return productCategoryEntity;
    }

    public ProductCategoryEntity updateProductCategoryAllData(ProductCategoryEntity productCategoryEntity,
                                                              ProductCategoryEntity productCategoryMainData)
            throws PopulatorException {
        productCategoryPopulator.populateAllData(productCategoryEntity, productCategoryMainData);
        productCategoryRepository.save(productCategoryEntity);
        return productCategoryEntity;
    }

    public void deleteProductCategory(ProductCategoryEntity productCategoryEntity) {
        productCategoryRepository.delete(productCategoryEntity);
    }
}
