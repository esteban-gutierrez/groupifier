package com.bestsecret.groupifier.service;

import com.bestsecret.groupifier.model.TextCategoryEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.populator.TextCategoryPopulator;
import com.bestsecret.groupifier.repository.TextCategoryRepository;
import com.bestsecret.groupifier.util.TimeService;
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

    @Resource
    private TimeService timeService;

    public List<TextCategoryEntity> getAllTextCategories() {
        return textCategoryRepository.findAll();
    }

    public Optional<TextCategoryEntity> getTextCategoryById(Long textCategoryId) {
        return textCategoryRepository.findById(textCategoryId);
    }

    public TextCategoryEntity createTextCategory(TextCategoryEntity textCategory) {
        textCategory.setCreatedAt(timeService.getCurrentSQLDate());
        textCategory.setModifiedAt(timeService.getCurrentSQLDate());
        return textCategoryRepository.save(textCategory);
    }

    public TextCategoryEntity updateTextCategoryMainData(TextCategoryEntity textCategoryEntity,
                                                         TextCategoryEntity textCategoryMainData)
            throws PopulatorException {
        textCategoryPopulator.populateMainData(textCategoryEntity, textCategoryMainData);
        textCategoryRepository.save(textCategoryEntity);
        return textCategoryEntity;
    }

    public TextCategoryEntity updateTextCategoryAllData(TextCategoryEntity textCategoryEntity,
                                                        TextCategoryEntity textCategoryMainData)
            throws PopulatorException {
        textCategoryPopulator.populateAllData(textCategoryEntity, textCategoryMainData);
        textCategoryRepository.save(textCategoryEntity);
        return textCategoryEntity;
    }

    public void deleteTextCategory(TextCategoryEntity textCategoryEntity) {
        textCategoryRepository.delete(textCategoryEntity);
    }
}
