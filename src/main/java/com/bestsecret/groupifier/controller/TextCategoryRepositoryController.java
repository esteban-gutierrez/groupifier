package com.bestsecret.groupifier.controller;

import com.bestsecret.groupifier.model.TextCategoryEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.service.TextCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("${groupifier.rest.api.uri}")
public class TextCategoryRepositoryController {
    @Resource
    private TextCategoryService textCategoryService;

    @GetMapping("/textcategories")
    public ResponseEntity<List<TextCategoryEntity>> getAllTextCategories() throws ResponseStatusException {
        try {
            List<TextCategoryEntity> textCategoryEntityList = textCategoryService.getAllTextCategories();
            return ResponseEntity.ok(textCategoryEntityList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting all text categories", e);
        }
    }

    private TextCategoryEntity getTextCategoryEntityById(Long textCategoryId) {
        return textCategoryService.getTextCategoryById(textCategoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Text category with id=%s not found", textCategoryId)));
    }

    @GetMapping("/textcategories/{id}")
    public ResponseEntity<TextCategoryEntity> getTextCategoryById(@PathVariable(value = "id") Long textCategoryId)
            throws ResponseStatusException {
        TextCategoryEntity textCategoryEntity = getTextCategoryEntityById(textCategoryId);
        return ResponseEntity.ok(textCategoryEntity);
    }

    @PostMapping("/textcategories")
    public ResponseEntity<TextCategoryEntity> createTextCategory(@RequestBody TextCategoryEntity textCategory)
            throws ResponseStatusException {
        try {
            TextCategoryEntity createdTextCategory = textCategoryService.createTextCategory(textCategory);
            return ResponseEntity.ok(createdTextCategory);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating text category", e);
        }
    }

    @PutMapping("/textcategories/maindata/{id}")
    public ResponseEntity<TextCategoryEntity> updateTextCategoryMainData(
                @PathVariable(value = "id") Long textCategoryId,
                @RequestBody TextCategoryEntity textCategoryMainData)
            throws ResponseStatusException {
        TextCategoryEntity textCategoryEntity = getTextCategoryEntityById(textCategoryId);
        try {
            textCategoryService.updateTextCategoryMainData(textCategoryEntity, textCategoryMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text category with id=%s could not be updated", textCategoryId), populatorException);
        }
        return ResponseEntity.ok(textCategoryEntity);
    }

    @PutMapping("/textcategories/alldata/{id}")
    public ResponseEntity<TextCategoryEntity> updateTextCategoryAllData(
                @PathVariable(value = "id") Long textCategoryId,
                @RequestBody TextCategoryEntity textCategoryMainData)
            throws ResponseStatusException {
        TextCategoryEntity textCategoryEntity = getTextCategoryEntityById(textCategoryId);
        try {
            textCategoryService.updateTextCategoryAllData(textCategoryEntity, textCategoryMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text category with id=%s could not be updated", textCategoryId),
                    populatorException);
        }
        return ResponseEntity.ok(textCategoryEntity);
    }

    @DeleteMapping("/textcategories/{id}")
    public ResponseEntity<String> deleteTextCategory(@PathVariable(name = "id") Long textCategoryId)
            throws ResponseStatusException {
        TextCategoryEntity textCategoryEntity = getTextCategoryEntityById(textCategoryId);
        try {
            textCategoryService.deleteTextCategory(textCategoryEntity);
            return ResponseEntity.ok(String.format("Text category with id=%s deleted successfully", textCategoryId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Text category with id=%s could not be deleted", textCategoryId), e);
        }
    }
}
