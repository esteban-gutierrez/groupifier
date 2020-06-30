package com.bestsecret.groupifier.controller;

import com.bestsecret.groupifier.model.TextValueEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.service.TextValueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("${groupifier.rest.api.uri}")
public class TextValueController {
    @Resource
    private TextValueService textValueService;

    @GetMapping("/textvalues")
    public ResponseEntity<List<TextValueEntity>> getAllTextValues() throws ResponseStatusException {
        try {
            List<TextValueEntity> textValueEntityList = textValueService.getAllTextValues();
            return ResponseEntity.ok(textValueEntityList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting all text values", e);
        }
    }

    private TextValueEntity getTextValueEntityById(Long textValueId) {
        return textValueService.getTextValueById(textValueId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Text value with id=%s not found", textValueId)));
    }

    @GetMapping("/textvalues/{id}")
    public ResponseEntity<TextValueEntity> getTextValueById(@PathVariable(value = "id") Long textValueId)
            throws ResponseStatusException {
        TextValueEntity textValueEntity = getTextValueEntityById(textValueId);
        return ResponseEntity.ok(textValueEntity);
    }

    @PostMapping("/textvalues")
    public ResponseEntity<TextValueEntity> createTextValue(@RequestBody TextValueEntity textValue)
            throws ResponseStatusException {
        try {
            TextValueEntity createdTextValue = textValueService.createTextValue(textValue);
            return ResponseEntity.ok(createdTextValue);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating text value", e);
        }
    }

    @PutMapping("/textvalues/maindata/{id}")
    public ResponseEntity<TextValueEntity> updateTextValueMainData(
                @PathVariable(value = "id") Long textValueId,
                @RequestBody TextValueEntity textValueMainData)
            throws ResponseStatusException {
        TextValueEntity textValueEntity = getTextValueEntityById(textValueId);
        try {
            textValueService.updateTextValueMainData(textValueEntity, textValueMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text value with id=%s could not be updated", textValueId), populatorException);
        }
        return ResponseEntity.ok(textValueEntity);
    }

    @PutMapping("/textvalues/alldata/{id}")
    public ResponseEntity<TextValueEntity> updateTextValueAllData(
                @PathVariable(value = "id") Long textValueId,
                @RequestBody TextValueEntity textValueMainData)
            throws ResponseStatusException {
        TextValueEntity textValueEntity = getTextValueEntityById(textValueId);
        try {
            textValueService.updateTextValueAllData(textValueEntity, textValueMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text value with id=%s could not be updated", textValueId), populatorException);
        }
        return ResponseEntity.ok(textValueEntity);
    }

    @DeleteMapping("/textvalues/{id}")
    public ResponseEntity<String> deleteTextValue(@PathVariable(name = "id") Long textValueId)
            throws ResponseStatusException {
        TextValueEntity textValueEntity = getTextValueEntityById(textValueId);
        try {
            textValueService.deleteTextValue(textValueEntity);
            return ResponseEntity.ok(String.format("Text value with id=%s deleted successfully", textValueId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Text value with id=%s could not be deleted", textValueId), e);
        }
    }
}
