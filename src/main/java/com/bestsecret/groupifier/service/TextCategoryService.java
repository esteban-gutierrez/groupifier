package com.bestsecret.groupifier.service;

import com.bestsecret.groupifier.model.TextCategoryEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.populator.TextCategoryPopulator;
import com.bestsecret.groupifier.repository.TextCategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class TextCategoryService {
    @Resource
    private TextCategoryRepository textCategoryRepository;

    @Resource
    private TextCategoryPopulator textCategoryPopulator;

    public List<TextCategoryEntity> getAllTextCategories() {
        return textCategoryRepository.findAll();
    }

    public Optional<TextCategoryEntity> getTextCategoryById(Long textCategoryId) {
        return textCategoryRepository.findById(textCategoryId);
    }

    public TextCategoryEntity createTextCategory(TextCategoryEntity productCategory) {
        return textCategoryRepository.save(productCategory);
    }

    public TextCategoryEntity updateTextCategoryMainData(TextCategoryEntity productCategoryEntity,
                                                         TextCategoryEntity productCategoryMainData)
            throws PopulatorException {
        textCategoryPopulator.populateMainData(productCategoryEntity, productCategoryMainData);
        return productCategoryEntity;
    }

    public TextCategoryEntity updateTextCategoryAllData(TextCategoryEntity productCategoryEntity,
                                                        TextCategoryEntity productCategoryMainData)
            throws PopulatorException {
        textCategoryPopulator.populateAllData(productCategoryEntity, productCategoryMainData);
        return productCategoryEntity;
    }

    public void deleteTextCategory(TextCategoryEntity productCategoryEntity) {
        textCategoryRepository.delete(productCategoryEntity);
    }
}
