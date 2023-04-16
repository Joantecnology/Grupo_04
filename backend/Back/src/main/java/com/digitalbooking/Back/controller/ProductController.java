package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.model.Category;
import com.digitalbooking.Back.model.City;
import com.digitalbooking.Back.model.Product;
import com.digitalbooking.Back.response.ApiResponseHandler;
import com.digitalbooking.Back.services.serviceInterface.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
/*import org.springframework.http.HttpStatusCode;*/
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Api(tags="Products")
/*@CrossOrigin (origins = "*")*/
/* agrego al crosing, la direccion del front*/
@CrossOrigin (origins = "http://127.0.0.1:5173/")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @RequestParam int id_city, @RequestParam int id_category, @RequestParam int id_image){
        return ResponseEntity.ok(productService.saveProduct(product,  id_city,  id_category,  id_image));
    }

    @ApiOperation(value="Product by ID", notes="Product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Object> searchProductById (@PathVariable Integer id){
        Optional<Product>  searchedProduct = productService.searchProductById(id);

        if (searchedProduct.isPresent()){
            return ApiResponseHandler.generateResponse("data saved successfully", HttpStatus.OK, searchedProduct.get());
        }else {
            return ApiResponseHandler.generateResponseError("Product "+ id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts (){
        return ResponseEntity.ok(productService.listProducts());
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> listProductsByCategory(@PathVariable Category id){
        List<Product> searchedProducts = productService.listProductsByCategory(id);

        if (!searchedProducts.isEmpty()){
            return ResponseEntity.ok(searchedProducts);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Product>> listProductsByCity (@PathVariable City id){
        List<Product> searchedProducts = productService.listProductsByCity(id);

        if(!searchedProducts.isEmpty()){
            return ResponseEntity.ok(searchedProducts);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("all/random")
    public ResponseEntity<List<Product>> findAllRandom (){
        return ResponseEntity.ok(productService.listRandomProducts());
    }

    @GetMapping("/date/{start}/{end}")
    public ResponseEntity<List<Product>> listProductsBydDateRange(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){

        List<Product> searchedProducts = productService.getByDateRange(start, end);


        if(!searchedProducts.isEmpty()){
            return ResponseEntity.ok(searchedProducts);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/cityAndDates/{cityId}/{start}/{end}")
    public ResponseEntity<List<Product>> listProductsByCityAndDateRange(@PathVariable Integer cityId,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                                        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){

        List<Product> searchedProductsCityRange = productService.getByCityAndDateRange(cityId, start, end);

        if (!searchedProductsCityRange.isEmpty()){
            return ResponseEntity.ok(searchedProductsCityRange);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        Optional<Product>  searchedProduct = productService.searchProductById(product.getId());

        if (searchedProduct.isPresent()){
            return ResponseEntity.ok(productService.updateProduct(product));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product with Id: "+ product.getId()+", is not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct (@PathVariable Integer id){

        if (productService.searchProductById(id).isPresent()){
            productService.deleteProduct(id);
            return ResponseEntity.ok("The product with Id "+id+" has been remove");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product with Id "+id+" is not found");
        }
    }
}
