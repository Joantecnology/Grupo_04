package com.digitalbooking.Back.services.serviceInterface;

import com.digitalbooking.Back.model.Category;
import com.digitalbooking.Back.model.City;
import com.digitalbooking.Back.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct (Product product, int id_city, int id_category, int id_image);
    Optional <Product> searchProductById (Integer id);
    List<Product> listProducts ();
    List<Product> listProductsByCategory(Category id);
    Product updateProduct (Product product);
    void deleteProduct (Integer id);
    List<Product> listProductsByCity (City id);
    List<Product> listRandomProducts ();
    List<Product> getByDateRange(LocalDate checkin_date, LocalDate checking_date);
    List<Product> getByCityAndDateRange(Integer id_city,LocalDate checkin_date, LocalDate checking_date);

}
