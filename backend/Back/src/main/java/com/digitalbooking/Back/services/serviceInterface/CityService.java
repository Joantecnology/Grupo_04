package com.digitalbooking.Back.services.serviceInterface;

import com.digitalbooking.Back.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {


    City saveCity (City city, int id_country);

    Optional<City> searchCityById (Integer id);
    List<City> listCities ();
    City updateCity (City city);
    void deleteCity (Integer id);
}
