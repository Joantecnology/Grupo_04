package com.digitalbooking.Back.services.serviceInterface;

import com.digitalbooking.Back.exception.DuplicatedValueException;
import com.digitalbooking.Back.model.Feature;

import java.util.List;
import java.util.Optional;

public interface FeatureService {

    Feature saveFeature (Feature feature) throws DuplicatedValueException;
    Optional<Feature> searchFeatureById (Integer id);
    List<Feature> listFeatures ();
    Feature updateFeature (Feature feature);
    void deleteFeature(Integer id);
}
