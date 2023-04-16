package com.digitalbooking.Back.services.impl;
import com.digitalbooking.Back.model.Country;
import com.digitalbooking.Back.repository.CountryRepository;
import com.digitalbooking.Back.services.serviceInterface.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{
    @Autowired
    private CountryRepository countryRepository;
    @Override
    public Country saveCountry(Country country) { return countryRepository.save(country);
    }

    @Override
    public Optional<Country> searchCountryById(Integer id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<Country> listCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country updateCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id);
    }
}
