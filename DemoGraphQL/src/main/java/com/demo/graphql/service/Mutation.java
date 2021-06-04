package com.demo.graphql.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.graphql.model.entity.CarBrand;
import com.demo.graphql.model.entity.CarType;
import com.demo.graphql.repository.CarBrandRepository;
import com.demo.graphql.repository.CarTypeRepository;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Mutation implements GraphQLMutationResolver
{
    private static final Logger logger = LogManager.getLogger();

    private final CarBrandRepository carBrandRepository;
    private final CarTypeRepository carTypeRepository;

    @Autowired
    public Mutation(CarBrandRepository carBrandRepository, CarTypeRepository carTypeRepository)
    {
        this.carBrandRepository = carBrandRepository;
        this.carTypeRepository = carTypeRepository;
    }

    public CarBrand createBrand(String brandName)
    {
        logger.debug("createBrand");

        CarBrand carBrand = CarBrand.builder().brandName(brandName).build();

        logger.debug("carBrand={}", carBrand.toString());

        carBrandRepository.save(carBrand);

        return carBrand;
    }

    public CarBrand updateBrand(Long id, String brandName) throws NotFoundException
    {
        logger.debug("updateBrand, id={}, brandName={}", id, brandName);

        Optional<CarBrand> optionalCarBrand = carBrandRepository.findById(id);

        if (optionalCarBrand.isPresent())
        {
            CarBrand carBrand = optionalCarBrand.get();
            carBrand.setBrandName(brandName);

            carBrandRepository.save(carBrand);
        }

        throw new NotFoundException("Not found CarBrand to update!");
    }

    public boolean deleteBrand(Long id)
    {
        logger.debug("deleteBrand, id={}", id);

        boolean result = false;

        try
        {
            carBrandRepository.deleteById(id);
            result = true;
        }
        catch (Exception e)
        {
            logger.error("Error when deleteBrand={}", e.toString());
        }

        return result;
    }

    public CarType createType(String typeName, Long brandId)
    {
        logger.debug("createType, typeNane={}, brandId={}", typeName, brandId);

        CarType carType = new CarType();
        carType.setTypeName(typeName);
        carType.setCarBrand(new CarBrand(brandId));

        carTypeRepository.save(carType);

        return carType;
    }

    public CarType updateType(Long id, String typeName) throws NotFoundException
    {
        logger.debug("updateType, id={}, typeName={}", id, typeName);

        Optional<CarType> optionalCarType = carTypeRepository.findById(id);

        if (optionalCarType.isPresent())
        {
            CarType carType = optionalCarType.get();
            carType.setTypeName(typeName);

            carTypeRepository.save(carType);

            return carType;
        }

        throw new NotFoundException("Not found CarType to update!");
    }

    public boolean deleteType(Long id)
    {
        logger.debug("deleteType, id={}", id);

        boolean result = false;

        try
        {
            carTypeRepository.deleteById(id);
            result = true;
        }
        catch (Exception e)
        {
            logger.error("Error when deleteType={}", e.getMessage());
        }

        return result;
    }
}