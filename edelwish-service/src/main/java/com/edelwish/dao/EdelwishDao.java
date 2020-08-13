package com.edelwish.dao;

import com.edelwish.model.*;
import com.edelwish.util.EdelwishUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class EdelwishDao
{
    private final JdbcTemplate jdbcTemplate;
    private final EdelwishUtil edelwishUtil;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_DETAIL = "detail";

    @Autowired
    public EdelwishDao(JdbcTemplate jdbcTemplate, EdelwishUtil edelwishUtil)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.edelwishUtil = edelwishUtil;
    }

    public User findById(String email, String password)
    {
        User user;

        String sql = "SELECT ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, TYPE FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";

        try
        {
            user = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            {
                User userResult = new User();
                userResult.setId(rs.getLong(COLUMN_ID));
                userResult.setFirstname(rs.getString(COLUMN_FIRSTNAME));
                userResult.setLastname(rs.getString(COLUMN_LASTNAME));
                userResult.setEmail(rs.getString("email"));
                userResult.setPassword(rs.getString("password"));
                userResult.setType(rs.getInt("type"));

                return userResult;
            }, email, password);

            return user;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public User findByEmail(String email)
    {
        User user;

        String sql = "SELECT ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, TYPE FROM USERS WHERE EMAIL = ?";

        try
        {
            user = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            {
                User userResult = new User();
                userResult.setId(rs.getLong(COLUMN_ID));

                return userResult;
            }, email);

            return user;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public void saveUser(String firstname, String lastname, String email, String password)
    {
        String sql = "INSERT INTO USERS (firstname, lastname, email, password, type) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, firstname, lastname, email, password, 1);
    }

    public void saveTestimoni(Long userId, String testimoni)
    {
        String sql = "INSERT INTO TESTIMONI (id_user, testimoni) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, testimoni);
    }

    public void saveBooking(Request request, String bookingNumber)
    {
        String sql = "INSERT INTO BOOKING (booking_number, id_user, address, phone_number, event_datetime, id_wedding_category, id_wedding_package, fee, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bookingNumber, request.getUserId(), request.getAddress(), request.getPhoneNumber(), edelwishUtil.generateTimestamp(request.getEventDateTime()),
                request.getWeddingCategory(), request.getWeddingPackage(), request.getBookingFee(), "PENDING");
    }

    public void updateBooking(Long id, String bookingStatus)
    {
        String sql = "UPDATE BOOKING SET STATUS = ? WHERE ID = ?";
        jdbcTemplate.update(sql, bookingStatus, id);
    }

    public List<Testimoni> getTestimoni()
    {
        String sql = "SELECT TST.ID, USR.FIRSTNAME, USR.LASTNAME, TST.DETAIL FROM USERS USR INNER JOIN TESTIMONI TST ON USR.ID = TST.ID_USER";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        new Testimoni(
                                rs.getLong(COLUMN_ID),
                                rs.getString(COLUMN_FIRSTNAME),
                                rs.getString(COLUMN_LASTNAME),
                                rs.getString(COLUMN_DETAIL)
                        )
        );
    }

    public List<Booking> getSchedule()
    {
        String sql = "SELECT BK.ID, BK.BOOKING_NUMBER, USR.FIRSTNAME, USR.LASTNAME, BK.EVENT_DATETIME, BK.STATUS FROM USERS USR INNER JOIN BOOKING BK ON USR.ID = BK.ID_USER";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        new Booking(
                                rs.getLong(COLUMN_ID),
                                rs.getString("booking_number"),
                                rs.getString(COLUMN_FIRSTNAME),
                                rs.getString(COLUMN_LASTNAME),
                                rs.getString("event_datetime"),
                                rs.getString("status")
                        )
        );
    }

    public List<PaymentType> getListPayment()
    {
        String sql = "SELECT ID, NAME FROM PAYMENT_TYPE";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new PaymentType(rs.getLong("id"), rs.getString("name")));
    }

    public Booking getBookingByNumber(String bookingNumber)
    {
        Booking booking;

        String sql = "SELECT ID, BOOKING_NUMBER FROM BOOKING WHERE BOOKING_NUMBER = ?";

        try
        {
            booking = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Booking(rs.getLong(COLUMN_ID), rs.getString("booking_number")), bookingNumber);

            return booking;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public void savePayment(Long id, Long paymentTypeId, String receipt)
    {
        String sql = "INSERT INTO PAYMENT (id_booking, id_payment_type, payment_date, payment_receipt) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, paymentTypeId, new Date(), receipt);
    }

    public List<WeddingPackage> getListPackage()
    {
        String sql = "SELECT ID, NAME, PRICE, DETAIL FROM WEDDING_PACKAGE";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new WeddingPackage(rs.getLong("id"), rs.getString("name"), rs.getString("price"), rs.getString(COLUMN_DETAIL)));
    }

    public List<WeddingPackage> getListPackageV2()
    {
        String sql = "SELECT TP.ID, TP.NAME, TP.PRICE, TP.TOTAL_BUFFET, TP.TYPE_BUFFET, TB.NAME, TB.DETAIL_BUFFET, TP.DETAIL_PACKAGE, TP.BONUS FROM TEMP_PACKAGE TP INNER JOIN TEMP_BUFFET TB ON TP.TYPE_BUFFET = TB.ID";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            WeddingPackage obj = new WeddingPackage();
            obj.setId(rs.getLong("id"));
            obj.setName(rs.getString("name"));
            obj.setPrice(rs.getString("price"));
            obj.setTotalBuffet(rs.getString("total_buffet"));
            obj.setTypeBuffet(rs.getLong("type_buffet"));
            obj.setBuffetName(rs.getString("name"));
            obj.setDetailBuffet(rs.getString("detail_buffet"));
            obj.setDetailPackage(rs.getString("detail_package"));
            obj.setBonus(rs.getString("bonus").split("#"));

            return obj;
        });
    }

    public List<DetailPackage> getListDetailPackageV2()
    {
        String sql = "SELECT ID, NAME, DETAIL FROM TEMP_DETAIL_PACKAGE";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new DetailPackage(
                        rs.getLong("id"),
                        rs.getString("name"),
                        null != rs.getString(COLUMN_DETAIL) ? rs.getString(COLUMN_DETAIL).split("#") : null));
    }

    public List<Buffet> getListBuffetV2()
    {
        String sql = "SELECT ID, NAME, DETAIL_BUFFET FROM TEMP_BUFFET";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Buffet(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("detail_buffet")));
    }

    public List<DetailBuffet> getListDetailBuffetV2()
    {
        String sql = "SELECT ID, NAME FROM TEMP_DETAIL_BUFFET";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new DetailBuffet(rs.getLong("id"), rs.getString("name")));
    }

    public List<DetailPackage> getDetailPackage(String detail)
    {
        String sql = "SELECT ID, NAME, DETAIL FROM TEMP_DETAIL_PACKAGE WHERE ID IN (%s)";

        List<Integer> ids = Stream.of(detail.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));

        return jdbcTemplate.query(String.format(sql, inSql), (rs, rowNum) ->
                new DetailPackage(
                        rs.getLong("id"),
                        rs.getString("name"),
                        null != rs.getString(COLUMN_DETAIL) ? rs.getString(COLUMN_DETAIL).split("#") : null
                ), ids.toArray());
    }

    public List<DetailBuffet> getDetailBuffet(String detail)
    {
        String sql = "SELECT ID, NAME FROM TEMP_DETAIL_BUFFET WHERE ID IN (%s)";

        List<Integer> ids = Stream.of(detail.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));

        return jdbcTemplate.query(String.format(sql, inSql), (rs, rowNum) ->
                new DetailBuffet(
                        rs.getLong("id"),
                        rs.getString("name")
                ), ids.toArray());
    }

    public List<WeddingGallery> getGallery()
    {
        String sql = "SELECT WG.ID, WC.NAME, WG.IMAGE_URL FROM WEDDING_GALLERY WG INNER JOIN WEDDING_CATEGORY WC ON WG.ID_WEDDING_CATEGORY = WC.ID;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new WeddingGallery(rs.getLong("id"), rs.getString("name"), rs.getString("image_url")));
    }

    public void addPackage(Request request)
    {
        String sql = "INSERT INTO WEDDING_PACKAGE (name, price, detail) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getPackageName(), request.getPackagePrice(), request.getPackageDetail());
    }

    public List<WeddingCategory> getCategory()
    {
        String sql = "SELECT ID, NAME FROM WEDDING_CATEGORY";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new WeddingCategory(rs.getLong("id"), rs.getString("name")));
    }

    public void uploadPhoto(Request request)
    {
        String sql = "INSERT INTO WEDDING_GALLERY (id_wedding_category, image_url) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getWeddingCategoryId(), request.getPhotoUrl());
    }
}