package com.digitalbooking.Back.services.impl;

import com.digitalbooking.Back.exception.BadRequestException;
import com.digitalbooking.Back.model.Image;
import com.digitalbooking.Back.model.Product;
import com.digitalbooking.Back.repository.ImageRepository;
import com.digitalbooking.Back.repository.ProductRepository;
import com.digitalbooking.Back.services.serviceInterface.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Image saveImage(Image image, Integer productId) {
        Optional<Product> product_Img = productRepository.findById(productId);
        image.setProduct(product_Img.get());
        return imageRepository.save(image);
    }

    @Override
    public Optional<Image> searchImageById(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public List<Image> listImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image updateImage(Image image, Integer productId) throws BadRequestException {
        Optional<Image> findImage = imageRepository.findById(image.getId());
        boolean product_unchanged = findImage.get().getProduct().getId().equals(productId);

        if (!product_unchanged){
            throw new BadRequestException("Images does not match Product Id: "+productId);
        }

        findImage.get().setTitle(image.getTitle());
        findImage.get().setUrl(image.getUrl());

        return imageRepository.save(findImage.get());
    }

    @Override
    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }
}
