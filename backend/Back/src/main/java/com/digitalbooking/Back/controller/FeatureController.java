package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.exception.DuplicatedValueException;
import com.digitalbooking.Back.model.Feature;
import com.digitalbooking.Back.services.serviceInterface.FeatureService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="Features")
/*@CrossOrigin(origins = "*")*/
/* agrego al crosing, la direccion del front*/
@CrossOrigin (origins = "http://127.0.0.1:5173/")
@RestController
@RequestMapping("/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("/create")
    public ResponseEntity<Feature> createFeature (@RequestBody Feature feature) throws DuplicatedValueException {
        return  ResponseEntity.ok(featureService.saveFeature(feature));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feature> getFeature (@PathVariable Integer id){
        Optional<Feature> searchedFeature = featureService.searchFeatureById(id);

        if (searchedFeature.isPresent()){
            return ResponseEntity.ok(searchedFeature.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Feature>> listFeatures(){
        return ResponseEntity.ok(featureService.listFeatures());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFeature (@RequestBody Feature feature){
        Optional<Feature> searchedFeature = featureService.searchFeatureById(feature.getId());

        if (searchedFeature.isPresent()){
            return ResponseEntity.ok(featureService.updateFeature(feature));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Feature with Id "+feature.getId()+"is not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeature(@PathVariable Integer id){
        if (featureService.searchFeatureById(id).isPresent()){
            featureService.deleteFeature(id);
            return ResponseEntity.ok("The Feature with id "+id+" has been removed");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Feature with id "+id+" is not found");
        }
    }
}
