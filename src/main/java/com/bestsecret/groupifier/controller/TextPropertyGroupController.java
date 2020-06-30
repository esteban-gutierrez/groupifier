package com.bestsecret.groupifier.controller;

import com.bestsecret.groupifier.model.TextPropGroupEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.service.TextPropertyGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("${groupifier.rest.api.uri}")
public class TextPropertyGroupController {
    @Resource
    private TextPropertyGroupService textPropertyGroupService;

    @GetMapping("/textpropertygroups")
    public ResponseEntity<List<TextPropGroupEntity>> getAllTextPropertyGroups() throws ResponseStatusException {
        try {
            List<TextPropGroupEntity> textPropGroupEntityList = textPropertyGroupService.getAllTextPropertyGroups();
            return ResponseEntity.ok(textPropGroupEntityList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error getting all text property groups", e);
        }
    }

    private TextPropGroupEntity getTextPropertyGroupEntityById(Long textPropGroupId) {
        return textPropertyGroupService.getTextPropGroupById(textPropGroupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Text property group with id=%s not found", textPropGroupId)));
    }

    @GetMapping("/textpropertygroups/{id}")
    public ResponseEntity<TextPropGroupEntity> getTextPropertyGroupById(@PathVariable(value = "id") Long textPropGroupId)
            throws ResponseStatusException {
        TextPropGroupEntity textPropGroupEntity = getTextPropertyGroupEntityById(textPropGroupId);
        return ResponseEntity.ok(textPropGroupEntity);
    }

    @PostMapping("/textpropertygroups")
    public ResponseEntity<TextPropGroupEntity> createTextPropertyGroup(@RequestBody TextPropGroupEntity textPropGroup)
            throws ResponseStatusException {
        try {
            TextPropGroupEntity createdTextPropGroup = textPropertyGroupService.createTextPropGroup(textPropGroup);
            return ResponseEntity.ok(createdTextPropGroup);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating text property group", e);
        }
    }

    @PutMapping("/textpropertygroups/maindata/{id}")
    public ResponseEntity<TextPropGroupEntity> updateTextPropertyGroupMainData(
                @PathVariable(value = "id") Long textPropGroupId,
                @RequestBody TextPropGroupEntity textPropGroupMainData)
            throws ResponseStatusException {
        TextPropGroupEntity textPropGroupEntity = getTextPropertyGroupEntityById(textPropGroupId);
        try {
            textPropertyGroupService.updateTextPropGroupMainData(textPropGroupEntity, textPropGroupMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text property group with id=%s could not be updated", textPropGroupId), populatorException);
        }
        return ResponseEntity.ok(textPropGroupEntity);
    }

    @PutMapping("/textpropertygroups/alldata/{id}")
    public ResponseEntity<TextPropGroupEntity> updateTextPropertyGroupAllData(
                @PathVariable(value = "id") Long textPropGroupId,
                @RequestBody TextPropGroupEntity textPropGroupMainData)
            throws ResponseStatusException {
        TextPropGroupEntity textPropGroupEntity = getTextPropertyGroupEntityById(textPropGroupId);
        try {
            textPropertyGroupService.updateTextPropGroupAllData(textPropGroupEntity, textPropGroupMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text property group with id=%s could not be updated", textPropGroupId),
                    populatorException);
        }
        return ResponseEntity.ok(textPropGroupEntity);
    }

    @DeleteMapping("/textpropertygroups/{id}")
    public ResponseEntity<String> deleteTextPropertyGroup(@PathVariable(name = "id") Long textPropGroupId)
            throws ResponseStatusException {
        TextPropGroupEntity textPropGroupEntity = getTextPropertyGroupEntityById(textPropGroupId);
        try {
            textPropertyGroupService.deleteTextPropGroup(textPropGroupEntity);
            return ResponseEntity.ok(String.format("Text property group with id=%s deleted successfully", textPropGroupId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Text property group with id=%s could not be deleted", textPropGroupId), e);
        }
    }
}
