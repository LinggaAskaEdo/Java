package com.demo.graphql.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.graphql.model.Response;
import com.demo.graphql.model.entity.CarBrand;
import com.demo.graphql.model.entity.CarType;
import com.demo.graphql.preference.ConstantPreference;
import com.demo.graphql.repository.CarBrandRepository;
import com.demo.graphql.repository.CarTypeRepository;
import com.demo.graphql.util.GraphQLUtil;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Query implements GraphQLQueryResolver
{
    private static final Logger logger = LogManager.getLogger();

    private final CarBrandRepository carBrandRepository;
    private final CarTypeRepository carTypeRepository;
    private final GraphQLUtil util;

    @Autowired
    public Query(CarBrandRepository carBrandRepository, CarTypeRepository carTypeRepository, GraphQLUtil util)
    {
        this.carBrandRepository = carBrandRepository;
        this.carTypeRepository = carTypeRepository;
        this.util = util;
    }

    public List<CarBrand> findAllBrands(DataFetchingEnvironment env)
    {
        logger.debug("findAllBrands");

        util.getToken(env);

        return carBrandRepository.findAll();
    }

    public Response getAllBrands()
    {
        logger.debug("getAllBrands");

        List<CarBrand> brands = carBrandRepository.findAll();

        return Response.builder().status(ConstantPreference.RESPONSE_CODE_OK).message(ConstantPreference.RESPONSE_MESSAGE_OK).brandList(brands).build();
    }

    public long countBrands()
    {
        logger.debug("countBrands");

        return carBrandRepository.count();
    }

    public List<CarType> findAllTypes()
    {
        logger.debug("findAllTypes");

        return carTypeRepository.findAll();
    }

    public long countTypes()
    {
        logger.debug("countTypes");

        return carTypeRepository.count();
    }
}