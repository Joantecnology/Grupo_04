package com.digitalbooking.Back.services.impl;

import com.digitalbooking.Back.exception.DuplicatedValueException;
import com.digitalbooking.Back.model.Feature;
import com.digitalbooking.Back.repository.FeatureRepository;
import com.digitalbooking.Back.services.serviceInterface.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Override
    public Feature saveFeature(Feature feature) throws DuplicatedValueException{
        Optional<Feature> existIcon = featureRepository.getByIcon(feature.getIcon());
        if (existIcon.isPresent()){
            throw new DuplicatedValueException("the icon already exists: '"+existIcon.get().getName()+"'");
        }
        return featureRepository.save(feature);
    }

    @Override
    public Optional<Feature> searchFeatureById(Integer id) {
        return featureRepository.findById(id);
    }

    @Override
    public List<Feature> listFeatures() {
        return featureRepository.findAll();
    }

    @Override
    public Feature updateFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public void deleteFeature(Integer id) {
        featureRepository.deleteById(id);
    }
}
