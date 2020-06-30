package com.bestsecret.groupifier.controller;

import com.bestsecret.groupifier.model.TextpropsGroupMappingEntity;
import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.service.TextpropsGroupMappingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("${groupifier.rest.api.uri}")
public class TextpropsGroupMappingController {
    @Resource
    private TextpropsGroupMappingService textpropsGroupMappingpService;

    @GetMapping("/textpropsgroupmappings")
    public ResponseEntity<List<TextpropsGroupMappingEntity>> getAllTextpropsGroupMappings()
            throws ResponseStatusException {
        try {
            List<TextpropsGroupMappingEntity> textpropsGroupMappingEntityList =
                    textpropsGroupMappingpService.getAllTextpropsGroupMappings();
            return ResponseEntity.ok(textpropsGroupMappingEntityList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error getting all text property group mappings", e);
        }
    }

    private TextpropsGroupMappingEntity getTextpropsGroupMappingEntityById(Long textpropsGroupMappingId) {
        return textpropsGroupMappingpService.getTextpropsGroupMappingById(textpropsGroupMappingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Text property group mapping with id=%s not found", textpropsGroupMappingId)));
    }

    @GetMapping("/textpropsgroupmappings/{id}")
    public ResponseEntity<TextpropsGroupMappingEntity> getTextpropsGroupMappingById(@PathVariable(value = "id") Long textpropsGroupMappingId)
            throws ResponseStatusException {
        TextpropsGroupMappingEntity textpropsGroupMappingEntity = getTextpropsGroupMappingEntityById(textpropsGroupMappingId);
        return ResponseEntity.ok(textpropsGroupMappingEntity);
    }

    @PostMapping("/textpropsgroupmappings")
    public ResponseEntity<TextpropsGroupMappingEntity> createTextpropsGroupMapping(@RequestBody TextpropsGroupMappingEntity textpropsGroupMapping)
            throws ResponseStatusException {
        try {
            TextpropsGroupMappingEntity createdTextpropsGroupMapping =
                    textpropsGroupMappingpService.createTextpropsGroupMapping(textpropsGroupMapping);
            return ResponseEntity.ok(createdTextpropsGroupMapping);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error creating text property group mapping", e);
        }
    }

    @PutMapping("/textpropsgroupmappings/maindata/{id}")
    public ResponseEntity<TextpropsGroupMappingEntity> updateTextpropsGroupMappingMainData(
                @PathVariable(value = "id") Long textpropsGroupMappingId,
                @RequestBody TextpropsGroupMappingEntity textpropsGroupMappingMainData)
            throws ResponseStatusException {
        TextpropsGroupMappingEntity textpropsGroupMappingEntity =
                getTextpropsGroupMappingEntityById(textpropsGroupMappingId);
        try {
            textpropsGroupMappingpService.updateTextpropsGroupMappingMainData(textpropsGroupMappingEntity, textpropsGroupMappingMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text property group mapping with id=%s could not be updated",
                            textpropsGroupMappingId), populatorException);
        }
        return ResponseEntity.ok(textpropsGroupMappingEntity);
    }

    @PutMapping("/textpropsgroupmappings/alldata/{id}")
    public ResponseEntity<TextpropsGroupMappingEntity> updateTextpropsGroupMappingAllData(
                @PathVariable(value = "id") Long textpropsGroupMappingId,
                @RequestBody TextpropsGroupMappingEntity textpropsGroupMappingMainData)
            throws ResponseStatusException {
        TextpropsGroupMappingEntity textpropsGroupMappingEntity = getTextpropsGroupMappingEntityById(textpropsGroupMappingId);
        try {
            textpropsGroupMappingpService.updateTextpropsGroupMappingAllData(textpropsGroupMappingEntity, textpropsGroupMappingMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Text property group mapping with id=%s could not be updated", textpropsGroupMappingId),
                    populatorException);
        }
        return ResponseEntity.ok(textpropsGroupMappingEntity);
    }

    @DeleteMapping("/textpropsgroupmappings/{id}")
    public ResponseEntity<String> deleteTextpropsGroupMapping(@PathVariable(name = "id") Long textpropsGroupMappingId)
            throws ResponseStatusException {
        TextpropsGroupMappingEntity textpropsGroupMappingEntity = getTextpropsGroupMappingEntityById(textpropsGroupMappingId);
        try {
            textpropsGroupMappingpService.deleteTextpropsGroupMapping(textpropsGroupMappingEntity);
            return ResponseEntity.ok(String.format("Text property group mapping with id=%s deleted successfully",
                    textpropsGroupMappingId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Text property group mapping with id=%s could not be deleted", textpropsGroupMappingId), e);
        }
    }
}
