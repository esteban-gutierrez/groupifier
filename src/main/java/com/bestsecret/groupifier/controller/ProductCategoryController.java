package com.bestsecret.groupifier.controller;

import com.bestsecret.groupifier.exception.PopulatorException;
import com.bestsecret.groupifier.model.ProductCategoryEntity;
import com.bestsecret.groupifier.populator.ProductCategoryPopulator;
import com.bestsecret.groupifier.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Resource
    private ProductCategoryPopulator productCategoryPopulator;

    @GetMapping("/productcategories")
    public List<ProductCategoryEntity> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @GetMapping("/productcategories/{id}")
    public ResponseEntity<ProductCategoryEntity> getAllProductCategoryById(@PathVariable(value = "id") Long productCategoryId)
            throws ResponseStatusException {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Product category with id=%s not found", productCategoryId)));
        return ResponseEntity.ok(productCategoryEntity);
    }

    @PostMapping("/productcategories")
    public ResponseEntity<ProductCategoryEntity> createProductCategory(@RequestBody ProductCategoryEntity productCategory)
            throws ResponseStatusException {
        try {
            ProductCategoryEntity createdProductCategory = productCategoryRepository.save(productCategory);
            return ResponseEntity.ok(createdProductCategory);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating product category", e);
        }
    }

    @PutMapping("/productcategories/maindata/{id}")
    public ResponseEntity<ProductCategoryEntity> updateProductCategoryMainData(
                @PathVariable(value = "id") Long productCategoryId,
                @RequestBody ProductCategoryEntity productCategoryMainData)
            throws ResponseStatusException {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Product category with id=%s not found", productCategoryId)));
        try {
            productCategoryPopulator.populateMainData(productCategoryEntity, productCategoryMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Product category with id=%s could not be updated", productCategoryId),
                    populatorException);
        }
        return ResponseEntity.ok(productCategoryEntity);
    }

    @PutMapping("/productcategories/alldata/{id}")
    public ResponseEntity<ProductCategoryEntity> updateProductCategoryAllData(
            @PathVariable(value = "id") Long productCategoryId,
            @RequestBody ProductCategoryEntity productCategoryMainData)
            throws ResponseStatusException {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Product category with id=%s not found", productCategoryId)));
        try {
            productCategoryPopulator.populateAllData(productCategoryEntity, productCategoryMainData);
        } catch (PopulatorException populatorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Product category with ID=%s could not be updated", productCategoryId),
                    populatorException);
        }
        return ResponseEntity.ok(productCategoryEntity);
    }

    @DeleteMapping("/productcategories/{id}")
    public ResponseEntity<String> deleteProductCategory(@PathVariable(name = "id") Long productCategoryId)
            throws ResponseStatusException {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Product category with id=%s not found", productCategoryId)));
        try {
            productCategoryRepository.delete(productCategoryEntity);
            return ResponseEntity.ok(String.format("Product category with id=%s deleted successfully", productCategoryId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Product category with id=%s could not be deleted", productCategoryId), e);
        }
    }
}
