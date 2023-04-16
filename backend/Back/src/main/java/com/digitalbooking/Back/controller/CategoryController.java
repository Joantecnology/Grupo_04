package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.model.Category;
import com.digitalbooking.Back.services.serviceInterface.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
/*import org.springframework.http.HttpStatusCode;*/
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="Categories")
/*@CrossOrigin (origins = "*")*/
@RestController
@RequestMapping("/categories")
/* agrego al crosing, la direccion del front*/
@CrossOrigin (origins = "http://127.0.0.1:5173/")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> searchCategoryById(@PathVariable Integer id){
        Optional<Category> searchedCategory = categoryService.searchCategory(id);

        if (searchedCategory.isPresent()){
            return  ResponseEntity.ok(searchedCategory.get());
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> listCategories(){
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        Optional<Category> searchedCategories = categoryService.searchCategory(category.getId());

        if (searchedCategories.isPresent()){
            return ResponseEntity.ok(categoryService.updateCategory(category));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The ID: "+ category.getId() +" is not registered Category");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){

        if (categoryService.searchCategory(id).isPresent()){
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("The Category with ID "+ id + "has been removed");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Category with ID"+ id+ " you are trying delete is not registered");
        }
    }
}
