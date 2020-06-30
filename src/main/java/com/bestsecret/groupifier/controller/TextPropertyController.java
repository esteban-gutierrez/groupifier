package com.bestsecret.groupifier.controller;

import com.bestsecret.groupifier.model.TextPropertyEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.service.TextPropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("${groupifier.rest.api.uri}")
public class TextPropertyController {
    @Resource
    private TextPropertyService textPropertyService;

    @GetMapping("/textproperties")
    public ResponseEntity<List<TextPropertyEntity>> getAllTextProperties() throws ResponseStatusException {
        try {
            List<TextPropertyEntity> textPropertyEntityList = textPropertyService.getAllTextProperties();
            return ResponseEntity.ok(textPropertyEntityList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting all text properties", e);
        }
    }

    private TextPropertyEntity getTextPropertyEntityById(Long textPropertyId) {
        return textPropertyService.getTextPropertyById(textPropertyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Text property with id=%s not found", textPropertyId)));
    }

    @GetMapping("/textproperties/{id}")
    public ResponseEntity<TextPropertyEntity> getTextPropertyById(@PathVariable(value = "id") Long textPropertyId)
            throws ResponseStatusException {
        TextPropertyEntity textPropertyEntity = getTextPropertyEntityById(textPropertyId);
        return ResponseEntity.ok(textPropertyEntity);
    }

    @PostMapping("/textproperties")
    public ResponseEntity<TextPropertyEntity> createTextProperty(@RequestBody TextPropertyEntity textProperty)
            throws ResponseStatusException {
        try {
            TextPropertyEntity createdTextProperty = textPropertyService.createTextProperty(textProperty);
            return ResponseEntity.ok(createdTextProperty);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating text property", e);
        }
    }

    @PutMapping("/textproperties/maindata/{id}")
    public ResponseEntity<TextPropertyEntity> updateTextPropertyMainData(
                @PathVariable(value = "id") Long textPropertyId,
                @RequestBody TextPropertyEntity textPropertyMainData)
            throws ResponseStatusException {
        TextPropertyEntity textPropertyEntity = getTextPropertyEntityById(textPropertyId);
        try {
            textPropertyService.updateTextPropertyMainData(textPropertyEntity, textPropertyMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text property with id=%s could not be updated", textPropertyId), populatorException);
        }
        return ResponseEntity.ok(textPropertyEntity);
    }

    @PutMapping("/textproperties/alldata/{id}")
    public ResponseEntity<TextPropertyEntity> updateTextPropertyAllData(
                @PathVariable(value = "id") Long textPropertyId,
                @RequestBody TextPropertyEntity textPropertyMainData)
            throws ResponseStatusException {
        TextPropertyEntity textPropertyEntity = getTextPropertyEntityById(textPropertyId);
        try {
            textPropertyService.updateTextPropertyAllData(textPropertyEntity, textPropertyMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text property with id=%s could not be updated", textPropertyId),
                    populatorException);
        }
        return ResponseEntity.ok(textPropertyEntity);
    }

    @DeleteMapping("/textproperties/{id}")
    public ResponseEntity<String> deleteTextProperty(@PathVariable(name = "id") Long textPropertyId)
            throws ResponseStatusException {
        TextPropertyEntity textPropertyEntity = getTextPropertyEntityById(textPropertyId);
        try {
            textPropertyService.deleteTextProperty(textPropertyEntity);
            return ResponseEntity.ok(String.format("Text property with id=%s deleted successfully", textPropertyId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Text property with id=%s could not be deleted", textPropertyId), e);
        }
    }
}
