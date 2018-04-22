package com.hibernate.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car
{
    @Id
    @Column(name = "car_id")
    private int id;

    @Column(name = "car_name")
    private String carName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    private List<User> users = new ArrayList<>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCarName()
    {
        return carName;
    }

    public void setCarName(String carName)
    {
        this.carName = carName;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }
}