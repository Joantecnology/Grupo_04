package com.digitalbooking.Back.repository;

import com.digitalbooking.Back.model.Category;
import com.digitalbooking.Back.model.City;
import com.digitalbooking.Back.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getProductsByCategory(Category id);
    List<Product> getProductsByCity(City id);
    @Query(value = "SELECT * FROM product ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Product> getRandomProducts();

    @Query(value = "select P.* from product P where P.id not in (select distinct B.id_product from booking B where (B.checkout_date >= ?1 and B.checkin_date <= ?2));", nativeQuery = true)
    List<Product> getByDateRange(LocalDate checking_date, LocalDate checkout_date);
    @Query(value = "select P.* from product P where P.id_city = ?1 and P.id not in (select distinct B.id_product from booking B where (B.checkout_date >= ?2 and B.checkin_date <= ?3));", nativeQuery = true)
    List<Product> getByCityAndDateRange(Integer id_city,LocalDate checking_date, LocalDate checkout_date);

}
