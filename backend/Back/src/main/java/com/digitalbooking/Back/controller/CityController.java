package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.model.City;
import com.digitalbooking.Back.services.serviceInterface.CityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
/*import org.springframework.http.HttpStatusCode;*/
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="Cities")
/*@CrossOrigin(origins = "*")*/
/* agrego al crosing, la direccion del front*/
@CrossOrigin (origins = "http://127.0.0.1:5173/")
@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<City> createCity (@RequestBody City city, @RequestParam int id_country){
        return ResponseEntity.ok(cityService.saveCity(city, id_country));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<City> getCityById (@PathVariable Integer id){
        Optional<City> searchedCity = cityService.searchCityById(id);

        if (searchedCity.isPresent()){
            return ResponseEntity.ok(searchedCity.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<City>> listCities (){
        return ResponseEntity.ok(cityService.listCities());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCity (@RequestBody City city){
        Optional<City> searchedCity = cityService.searchCityById(city.getId());

        if ( searchedCity.isPresent()){
            return ResponseEntity.ok(cityService.updateCity(city));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The City with Id "+city.getId()+" is not found");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCity (@PathVariable Integer id){
        if (cityService.searchCityById(id).isPresent()){
            cityService.deleteCity(id);
            return ResponseEntity.ok("the city has been removed");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The City with Id "+id+ " is not found");
        }
    }
}
