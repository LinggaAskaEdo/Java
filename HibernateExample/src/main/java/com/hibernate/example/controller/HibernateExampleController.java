package com.hibernate.example.controller;

import com.hibernate.example.model.Request;
import com.hibernate.example.model.UserListResponse;
import com.hibernate.example.service.HibernateExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HibernateExampleController
{
    @Autowired
    private HibernateExampleService service;

    @RequestMapping(value = "/getAllUserByCarName", method = RequestMethod.POST)
    public ResponseEntity<?> getAllUserByCarId(@RequestBody Request request)
    {
        UserListResponse listResponse = service.getAllUserByCarName(request.getCarName());

        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }
}