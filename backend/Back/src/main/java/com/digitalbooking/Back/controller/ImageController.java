package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.model.Image;
import com.digitalbooking.Back.services.serviceInterface.ImageService;
import io.swagger.annotations.Api;
/*import jakarta.persistence.criteria.CriteriaBuilder;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="Images")
/*@CrossOrigin(origins = "*")*/
/* agrego al crosing, la direccion del front*/
@CrossOrigin (origins = "http://127.0.0.1:5173/")
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/create/{productId}")
    public ResponseEntity<Image> createImage(@RequestBody Image image, @PathVariable Integer productId){
        return ResponseEntity.ok(imageService.saveImage(image,productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Integer id){
        Optional<Image> searchedImage = imageService.searchImageById(id);

        if (searchedImage.isPresent()){
            return ResponseEntity.ok(searchedImage.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Image>> listImages(){
        return ResponseEntity.ok(imageService.listImages());
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateImage(@RequestBody Image image,@PathVariable Integer productId) throws Exception{
        Optional<Image> searchedImage = imageService.searchImageById(image.getId());

        if (searchedImage.isPresent()){
            return ResponseEntity.ok(imageService.updateImage(image,productId));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Image with Id "+image.getId()+" is not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Integer id){
        if (imageService.searchImageById(id).isPresent()){
            imageService.deleteImage(id);
            return ResponseEntity.ok("The Image with id"+id+ " has been removed");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Image with Id "+id+ " is not found");
        }
    }
}
