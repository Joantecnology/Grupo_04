package com.digitalbooking.Back.repository;

import com.digitalbooking.Back.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.IntrospectionException;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
