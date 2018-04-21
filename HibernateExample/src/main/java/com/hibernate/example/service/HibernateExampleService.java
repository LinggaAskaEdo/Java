package com.hibernate.example.service;

import com.hibernate.example.dao.HibernateExampleDAO;
import com.hibernate.example.model.User;
import com.hibernate.example.model.UserListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HibernateExampleService
{
    @Autowired
    private HibernateExampleDAO dao;

    public UserListResponse getAllUserByCarName(String carName)
    {
        UserListResponse response = new UserListResponse();

        List<User> userList = dao.getAllUser(carName);

        response.setUserList(userList);

        return response;
    }
}