package com.digitalbooking.Back.model;

import javax.persistence.*;

import java.sql.Time;
import java.time.LocalDate;


@Entity
@Table(name = "booking")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Time arrival_time;
    private LocalDate checkin_date;
    private LocalDate checkout_date;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;

    public Reservation() {
    }

    public Reservation(Integer id, Time arrival, LocalDate checkin_date, LocalDate checkout_date, String comments, User user, Product product) {
        this.id = id;
        this.arrival_time = arrival_time;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.comments = comments;
        this.user = user;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getArrival() {
        return arrival_time;
    }

    public void setArrival(Time arrival) {
        this.arrival_time = arrival_time;
    }

    public LocalDate getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(LocalDate checkin_date) {
        this.checkin_date = checkin_date;
    }

    public LocalDate getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(LocalDate checkout_date) {
        this.checkout_date = checkout_date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
