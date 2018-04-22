package com.hibernate.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "user_car",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "car_id") }
    )
    private Set<Car> cars = new HashSet<>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Set<Car> getCars()
    {
        return cars;
    }

    public void setCars(Set<Car> cars)
    {
        this.cars = cars;
    }
}