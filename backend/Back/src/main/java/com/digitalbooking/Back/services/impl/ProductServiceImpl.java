package com.digitalbooking.Back.services.impl;

import com.digitalbooking.Back.model.Category;
import com.digitalbooking.Back.model.City;
import com.digitalbooking.Back.model.Image;
import com.digitalbooking.Back.model.Product;
import com.digitalbooking.Back.repository.CategoryRepository;
import com.digitalbooking.Back.repository.CityRepository;
import com.digitalbooking.Back.repository.ImageRepository;
import com.digitalbooking.Back.repository.ProductRepository;
import com.digitalbooking.Back.services.serviceInterface.CityService;
import com.digitalbooking.Back.services.serviceInterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Product saveProduct(Product product, int id_city, int id_category, int id_image) {

        City city = cityRepository.findById(id_city).orElse(null);
        Category category = categoryRepository.findById(id_category).orElse(null);
        Image image = imageRepository.findById(id_image).orElse(null);
        product.setCity(city);
        product.setCategory(category);
        product.setImage((List<Image>) image);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> searchProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> listProductsByCategory(Category id) {
        return productRepository.getProductsByCategory(id);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> listProductsByCity(City id) {
        return productRepository.getProductsByCity(id);
    }

    @Override
    public List<Product> listRandomProducts() {
        return productRepository.getRandomProducts();
    }

    @Override
    public List<Product> getByDateRange(LocalDate checkin_date, LocalDate checkout_date) {
        return productRepository.getByDateRange(checkin_date,checkout_date);
    }

    @Override
    public List<Product> getByCityAndDateRange(Integer id_city, LocalDate checkin_date, LocalDate checkout_date) {
        return productRepository.getByCityAndDateRange(id_city, checkin_date, checkout_date);
    }
}
