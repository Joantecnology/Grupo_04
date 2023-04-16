package com.digitalbooking.Back.model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "active")
    private Boolean active;
    private String ubication;
    private String address;
    private String latitude;
    private String longitude;
    private String area;
    private Double average_score ;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id")
    private City city;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> image;

    @Column(name = "policies")
    private String policies;

    /*@ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_features", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_features"))
    private List<Feature> features;*/


    public Product() {
    }

    public Product(Integer id, String name, String description, Boolean active, String ubication, String address, String latitude, String longitude, String area, Double average_score, Category category, City city, List<Image> image, String policies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.ubication = ubication;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
        this.average_score = average_score;
        this.category = category;
        this.city = city;
        this.image = image;
        this.policies = policies;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getAverage_score() {
        return average_score;
    }

    public void setAverage_score(Double average_score) {
        this.average_score = average_score;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getPolicies() {
        return policies;
    }

    public void setPolicies(String policies) {
        this.policies = policies;
    }
}
