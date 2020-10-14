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

import java.util.ArrayList;
import java.util.Comparator;
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

    public ResponseEntity<String> listPayment(Long userId)
    {
        Response response = new Response();

        try
        {
            List<Booking> bookings = edelwishDao.getBookingByUserId(userId);

            if (!bookings.isEmpty())
            {
                List<Payment> payments = new ArrayList<>();
                int[] paymentTypeIds = new int[8];

                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);

                for (Booking booking : bookings)
                {
                    payments = edelwishDao.getPaymentByBookingId(booking.getId());
                    booking.setPaymentList(payments);
                }

                response.setBookingList(bookings);

                for (int i = 0; i < payments.size(); i++)
                {
                    paymentTypeIds[i] = payments.get(i).getIdPaymentType().intValue();
                }

                List<PaymentType> paymentTypes = edelwishDao.getListPaymentType(paymentTypeIds);
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

    public ResponseEntity<String> payment(String bookingNumber, Long paymentTypeId, Long total, String receipt)
    {
        Response response = new Response();

        try
        {
            Booking booking = edelwishDao.getBookingByNumber(bookingNumber);

            if (null != booking)
            {
                edelwishDao.savePayment(booking.getId(), paymentTypeId, total, receipt);

                if (paymentTypeId == 1)
                {
                    edelwishDao.updateBooking(booking.getId(), "CONFIRMED");
                }

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

    public ResponseEntity<String> historyPayment()
    {
        Response response = new Response();

        try
        {
            List<User> userList = edelwishDao.getBookingUser();

            if (!userList.isEmpty())
            {
                List<PaymentHistory> paymentHistories = new ArrayList<>();

                for (User user : userList)
                {
                    PaymentHistory history = new PaymentHistory();
                    history.setId(user.getId());
                    history.setFirstname(user.getFirstname());
                    history.setLastname(user.getLastname());
                    history.setEmail(user.getEmail());

                    List<Booking> bookingList = edelwishDao.getBookingDetailByUserId(user.getId());

                    for (Booking booking : bookingList)
                    {
                        List<Payment> paymentList = edelwishDao.getPaymentDetailByBookingId(booking.getId());
                        booking.setPaymentList(paymentList);
                    }

                    history.setBookingList(bookingList);
                    paymentHistories.add(history);
                }

                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setPaymentHistories(paymentHistories);
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
                    List<DetailPackage> detailPackages = edelwishDao.getDetailPackage(aPackage.getId());
                    aPackage.setDetailPackages(detailPackages);

                    List<DetailBuffet> detailBuffets = edelwishDao.getDetailBuffet(aPackage.getBuffetId());
                    aPackage.setDetailBuffets(detailBuffets);
                }

                packageList.sort(Comparator.comparing(WeddingPackage::getId));
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
                    List<DetailBuffet> detailBuffets = edelwishDao.getDetailBuffet(buffet.getId());
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

    public ResponseEntity<String> updatePackageBuffet(Long id, Long buffetId)
    {
        Response response = new Response();

        try
        {
            edelwishDao.updatePackageBuffet(id, buffetId);
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> updatePackageDetail(Long id, int[] detailPackageIds)
    {
        Response response = new Response();

        try
        {
            edelwishDao.updatePackageDetail(id, detailPackageIds);
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> updatePackageDetailBuffet(Long id, int[] detailBuffetIds)
    {
        Response response = new Response();

        try
        {
            edelwishDao.updatePackageDetailBuffet(id, detailBuffetIds);
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> deletePackage(Long packageId)
    {
        Response response = new Response();

        try
        {
            edelwishDao.deletePackage(packageId);
            response.setCode(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e.getMessage());
        }

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ResponseEntity<String> packageBuilding(Long packageId)
    {
        Response response = new Response();

        try
        {
            List<Building> buildings = edelwishDao.getBuildingByPackage(packageId);

            if (!buildings.isEmpty())
            {
                response.setCode(ConstantPreference.RESPONSE_CODE_OK);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                response.setBuildingList(buildings);
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

    public ResponseEntity<String> addPackageV2(Request request)
    {
        Response response = new Response();

        try
        {
            long id = edelwishDao.addPackageV2(request);
            edelwishDao.addDetailPackageV2(id, request.getDetailPackageIds());
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