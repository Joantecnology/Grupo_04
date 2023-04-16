package com.digitalbooking.Back.services.serviceInterface;

import com.digitalbooking.Back.exception.BadRequestException;
import com.digitalbooking.Back.model.Image;
import com.digitalbooking.Back.model.Product;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    Image saveImage (Image image, Integer productId);
    Optional<Image> searchImageById(Integer id);
    List<Image> listImages ();
    Image updateImage (Image image, Integer productId) throws BadRequestException;
    void deleteImage (Integer id);
}
