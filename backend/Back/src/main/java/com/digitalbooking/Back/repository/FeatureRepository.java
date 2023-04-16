package com.digitalbooking.Back.repository;

import com.digitalbooking.Back.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    @Query(value = "SELECT F.* FROM features F WHERE F.icon = ?1", nativeQuery = true)
    Optional<Feature> getByIcon(String icon);
}
