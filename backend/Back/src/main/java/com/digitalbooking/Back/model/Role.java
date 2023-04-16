package com.digitalbooking.Back.model;

import javax.persistence.*;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //uso de la lista de usuarios de acuerdo a su rol asignado
    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
    }

    public Role(int id, String name ) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
