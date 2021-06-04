package com.demo.graphql.service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.model.entity.CarBrand;
import com.demo.graphql.model.entity.CarType;
import com.demo.graphql.repository.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarTypeResolver implements GraphQLResolver<CarType>
{
    private final CarBrandRepository carBrandRepository;

    @Autowired
    public CarTypeResolver(CarBrandRepository carBrandRepository)
    {
        this.carBrandRepository = carBrandRepository;
    }

    public CarBrand getCarBrand(CarType carType)
    {
        return carBrandRepository.findById(carType.getCarBrand().getId()).orElseThrow(null);
    }
}