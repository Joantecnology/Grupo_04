package com.digitalbooking.Back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_country", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Product> product;


    public City() {
    }

    public City(Integer id, String name, Country country, List<Product> product) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.product = product;
    }

    public City(Integer id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
