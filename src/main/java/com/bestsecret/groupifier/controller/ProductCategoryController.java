package com.bestsecret.groupifier.controller;

import com.bestsecret.groupifier.populator.PopulatorException;
import com.bestsecret.groupifier.model.ProductCategoryEntity;
import com.bestsecret.groupifier.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductCategoryController {
    @Resource
    private ProductCategoryService productCategoryService;

    @GetMapping("/productcategories")
    public ResponseEntity<List<ProductCategoryEntity>> getAllProductCategories() throws ResponseStatusException {
        try {
            List<ProductCategoryEntity> productCategoryEntityList = productCategoryService.getAllProductCategories();
            return ResponseEntity.ok(productCategoryEntityList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error getting all product categories", e);
        }
    }

    private ProductCategoryEntity getProductCategoryEntityById(Long productCategoryId) {
        return productCategoryService.getProductCategoryById(productCategoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Product category with id=%s not found", productCategoryId)));
    }

    @GetMapping("/productcategories/{id}")
    public ResponseEntity<ProductCategoryEntity> getProductCategoryById(@PathVariable(value = "id") Long productCategoryId)
            throws ResponseStatusException {
        ProductCategoryEntity productCategoryEntity = getProductCategoryEntityById(productCategoryId);
        return ResponseEntity.ok(productCategoryEntity);
    }

    @PostMapping("/productcategories")
    public ResponseEntity<ProductCategoryEntity> createProductCategory(@RequestBody ProductCategoryEntity productCategory)
            throws ResponseStatusException {
        try {
            ProductCategoryEntity createdProductCategory = productCategoryService.createProductCategory(productCategory);
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
        ProductCategoryEntity productCategoryEntity = getProductCategoryEntityById(productCategoryId);
        try {
            productCategoryService.updateProductCategoryMainData(productCategoryEntity, productCategoryMainData);
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
        ProductCategoryEntity productCategoryEntity = getProductCategoryEntityById(productCategoryId);
        try {
            productCategoryService.updateProductCategoryAllData(productCategoryEntity, productCategoryMainData);
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
        ProductCategoryEntity productCategoryEntity = getProductCategoryEntityById(productCategoryId);
        try {
            productCategoryService.deleteProductCategory(productCategoryEntity);
            return ResponseEntity.ok(String.format("Product category with id=%s deleted successfully", productCategoryId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Product category with id=%s could not be deleted", productCategoryId), e);
        }
    }
}
