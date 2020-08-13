package com.edelwish.service;

import com.edelwish.dao.EdelwishDao;
import com.edelwish.exception.ConflictException;
import com.edelwish.exception.InternalServerErrorException;
import com.edelwish.exception.NoContentException;
import com.edelwish.exception.NotFoundException;
import com.edelwish.model.*;
import com.edelwish.preference.ConstantPreference;
import com.edelwish.util.EdelwishUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdelwishService
{
    private final EdelwishUtil edelwishUtil;
    private final EdelwishDao edelwishDao;

    @Autowired
    public EdelwishService(EdelwishUtil edelwishUtil, EdelwishDao edelwishDao)
    {
        this.edelwishUtil = edelwishUtil;
        this.edelwishDao = edelwishDao;
    }

    public ResponseEntity<String> login(Request request)
    {
        Response response = new Response();

        try
        {
            User user = edelwishDao.findById(request.getEmail(), request.getPassword());

            if (null != user)
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setUser(user);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> register(Request request)
    {
        Response response = new Response();

        try
        {
            User user = edelwishDao.findByEmail(request.getEmail());

            if (null == user)
            {
                edelwishDao.saveUser(request.getFirstname(), request.getLastname(), request.getEmail(), request.getPassword());
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
            }
            else
            {
                throw new ConflictException("Email " + request.getEmail() + " already exist");
            }
        }
        catch (ConflictException ce)
        {
            throw new ConflictException(ce.getMessage());
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.CREATED);
    }

    public ResponseEntity<String> putTestimoni(Request request)
    {
        Response response = new Response();

        try
        {
            edelwishDao.saveTestimoni(request.getUserId(), request.getTestimoni());
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.CREATED);
    }

    public ResponseEntity<String> getTestimoni()
    {
        Response response = new Response();

        try
        {
            List<Testimoni> testimoniList = edelwishDao.getTestimoni();

            if (!testimoniList.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setTestimoniList(testimoniList);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> booking(Request request)
    {
        Response response = new Response();

        try
        {
            String bookingNumber = edelwishUtil.generateRandomNumber();
            edelwishDao.saveBooking(request, bookingNumber);
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
            response.setBookingNumber(bookingNumber);
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateBooking(Request request)
    {
        Response response = new Response();

        try
        {
            edelwishDao.updateBooking(request.getId(), request.getBookingStatus());
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> getSchedule()
    {
        Response response = new Response();

        try
        {
            List<Booking> bookings = edelwishDao.getSchedule();

            if (!bookings.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setBookingList(bookings);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> listPayment()
    {
        Response response = new Response();

        try
        {
            List<PaymentType> paymentTypes = edelwishDao.getListPayment();

            if (!paymentTypes.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setPaymentTypes(paymentTypes);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> payment(String bookingNumber, Long paymentTypeId, String receipt)
    {
        Response response = new Response();

        try
        {
            Booking booking = edelwishDao.getBookingByNumber(bookingNumber);

            if (null != booking)
            {
                edelwishDao.savePayment(booking.getId(), paymentTypeId, receipt);
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
            }
            else
            {
                throw new NotFoundException("Invalid booking number " + bookingNumber);
            }
        }
        catch (NotFoundException nfe)
        {
            throw new NotFoundException(nfe.getMessage());
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.CREATED);
    }

    public ResponseEntity<String> listPackage()
    {
        Response response = new Response();

        try
        {
            List<WeddingPackage> packageList = edelwishDao.getListPackage();

            if (!packageList.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setWeddingPackages(packageList);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> listPackageV2()
    {
        Response response = new Response();

        try
        {
            List<WeddingPackage> packageList = edelwishDao.getListPackageV2();

            if (!packageList.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);

                for (WeddingPackage aPackage : packageList)
                {
                    List<DetailPackage> detailPackages = edelwishDao.getDetailPackage(aPackage.getDetailPackage());
                    aPackage.setDetailPackage(null);
                    aPackage.setDetailPackages(detailPackages);

                    List<DetailBuffet> detailBuffets = edelwishDao.getDetailBuffet(aPackage.getDetailBuffet());
                    aPackage.setDetailBuffet(null);
                    aPackage.setDetailBuffets(detailBuffets);
                }

                response.setWeddingPackages(packageList);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> listDetailPackageV2()
    {
        Response response = new Response();

        try
        {
            List<DetailPackage> detailPackages = edelwishDao.getListDetailPackageV2();

            if (!detailPackages.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setDetailPackages(detailPackages);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> listBuffetV2()
    {
        Response response = new Response();

        try
        {
            List<Buffet> buffets = edelwishDao.getListBuffetV2();

            if (!buffets.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);

                for (Buffet buffet : buffets)
                {
                    List<DetailBuffet> detailBuffets = edelwishDao.getDetailBuffet(buffet.getDetailBuffet());
                    buffet.setDetailBuffet(null);
                    buffet.setDetailBuffets(detailBuffets);
                }

                response.setBuffets(buffets);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> listDetailBuffetV2()
    {
        Response response = new Response();

        try
        {
            List<DetailBuffet> detailBuffets = edelwishDao.getListDetailBuffetV2();

            if (!detailBuffets.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setDetailBuffets(detailBuffets);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> gallery()
    {
        Response response = new Response();

        try
        {
            List<WeddingGallery> galleryList = edelwishDao.getGallery();

            if (!galleryList.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setWeddingGalleries(galleryList);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> addPackage(Request request)
    {
        Response response = new Response();

        try
        {
            edelwishDao.addPackage(request);
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.CREATED);
    }

    public ResponseEntity<String> category()
    {
        Response response = new Response();

        try
        {
            List<WeddingCategory> categories = edelwishDao.getCategory();

            if (!categories.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setWeddingCategories(categories);
            }
            else
            {
                throw new NoContentException();
            }
        }
        catch (NoContentException nce)
        {
            throw new NoContentException();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> uploadPhoto(Request request)
    {
        Response response = new Response();

        try {
            edelwishDao.uploadPhoto(request);
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.CREATED);
    }
}