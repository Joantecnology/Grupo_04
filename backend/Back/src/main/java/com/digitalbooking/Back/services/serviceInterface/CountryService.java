package com.digitalbooking.Back.services.serviceInterface;

import com.digitalbooking.Back.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Country saveCountry (Country country);
    Optional<Country> searchCountryById (Integer id);
    List<Country> listCountries ();
    Country updateCountry (Country country);
    void deleteCountry (Integer id);
}
