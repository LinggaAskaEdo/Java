package com.edelwish.controller;

import com.edelwish.exception.BadRequestException;
import com.edelwish.model.Request;
import com.edelwish.preference.ConstantPreference;
import com.edelwish.service.EdelwishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class EdelwishController
{
    private static final String[] BOOKING_STATUS = new String[] { "CONFIRMED", "CANCELED" };

    private final EdelwishService service;

    @Autowired
    public EdelwishController(EdelwishService service)
    {
        this.service = service;
    }

    @PostMapping(value = "/v1/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody Request request)
    {
        if (null != request.getEmail() && null != request.getPassword())
        {
            return service.login(request);
        }
        else
        {
            throw new BadRequestException(ConstantPreference.MISSING_OBJECT);
        }
    }

    @PostMapping(value = "/v1/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody Request request)
    {
        if (null != request.getFirstname() && null != request.getLastname() && null != request.getEmail() && null != request.getPassword())
        {
            return service.register(request);
        }
        else
        {
            throw new BadRequestException(ConstantPreference.MISSING_OBJECT);
        }
    }

    @PostMapping(value = "/v1/testimoni", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> putTestimoni(@RequestBody Request request)
    {
        if (null != request.getUserId() && null != request.getTestimoni())
        {
            return service.putTestimoni(request);
        }
        else
        {
            throw new BadRequestException(ConstantPreference.MISSING_OBJECT);
        }
    }

    @GetMapping(value = "/v1/testimoni", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTestimoni()
    {
        return service.getTestimoni();
    }

    @PostMapping(value = "/v1/booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> booking(@RequestBody Request request)
    {
        if (null != request.getUserId() && null != request.getAddress() && null != request.getPhoneNumber() && null != request.getEventDateTime()
                && null != request.getWeddingCategory() && null != request.getWeddingPackage() && null != request.getBookingFee())
        {
            return service.booking(request);
        }
        else
        {
            throw new BadRequestException(ConstantPreference.MISSING_OBJECT);
        }
    }

    @PutMapping(value = "/v1/booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBooking(@RequestBody Request request)
    {
        if (null != request.getId() && null != request.getBookingStatus() && Arrays.asList(BOOKING_STATUS).contains(request.getBookingStatus()))
        {
            return service.updateBooking(request);
        }
        else
        {
            throw new BadRequestException(ConstantPreference.MISSING_OBJECT);
        }
    }

    @GetMapping(value = "/v1/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSchedule()
    {
        return service.getSchedule();
    }

    @GetMapping(value = "/v1/list-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listPayment()
    {
        return service.listPayment();
    }

    @PostMapping(value = "/v1/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> payment(@RequestBody Request request)
    {
        if (null != request.getBookingNumber() && null != request.getPaymentTypeId() && null != request.getReceipt())
        {
            return service.payment(request.getBookingNumber(), request.getPaymentTypeId(), request.getReceipt());
        }
        else
        {
            throw new BadRequestException(ConstantPreference.MISSING_OBJECT);
        }
    }

    @GetMapping(value = "/v1/list-package", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listPackage()
    {
        return service.listPackage();
    }

    @GetMapping(value = "/v2/list-package", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listPackageV2()
    {
        return service.listPackageV2();
    }

    @GetMapping(value = "/v2/list-detail-package", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listDetailPackageV2()
    {
        return service.listDetailPackageV2();
    }

    @GetMapping(value = "/v2/list-buffet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listBuffetV2()
    {
        return service.listBuffetV2();
    }

    @GetMapping(value = "/v2/list-detail-buffet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listDetailBuffetV2()
    {
        return service.listDetailBuffetV2();
    }

    @GetMapping(value = "/v1/gallery", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> gallery()
    {
        return service.gallery();
    }

    @PostMapping(value = "/v1/add-package", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPackage(@RequestBody Request request)
    {
        if (null != request.getPackageName() && null != request.getPackagePrice() && null != request.getPackageDetail())
        {
            return service.addPackage(request);
        }
        else
        {
            throw new BadRequestException(ConstantPreference.MISSING_OBJECT);
        }
    }

    @GetMapping(value = "/v1/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> category()
    {
        return service.category();
    }

    @PostMapping(value = "/v1/upload-photo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadPhoto(@RequestBody Request request)
    {
        if (null != request.getWeddingCategoryId() && null != request.getPhotoUrl())
        {
            return service.uploadPhoto(request);
        }
        else
        {
            throw new BadRequestException(ConstantPreference.MISSING_OBJECT);
        }
    }
}