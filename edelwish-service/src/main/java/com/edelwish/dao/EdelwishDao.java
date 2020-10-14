package com.edelwish.dao;

import com.edelwish.model.*;
import com.edelwish.util.EdelwishUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class EdelwishDao
{
    private final JdbcTemplate jdbcTemplate;
    private final EdelwishUtil edelwishUtil;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_DETAIL = "detail";
    private static final String COLUMN_BOOKING_NUMBER = "booking_number";

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
        String sql = "INSERT INTO TESTIMONI (id_user, detail) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, testimoni);
    }

    public void saveBooking(Request request, String bookingNumber)
    {
        String sql = "INSERT INTO BOOKING (booking_number, id_user, address, phone_number, event_datetime, id_category, id_package, id_building, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bookingNumber, request.getUserId(), request.getAddress(), request.getPhoneNumber(), edelwishUtil.generateTimestamp(request.getEventDateTime()),
                request.getWeddingCategory(), request.getWeddingPackage(), request.getWeddingBuilding(), "PENDING");
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
                                rs.getString(COLUMN_BOOKING_NUMBER),
                                rs.getString(COLUMN_FIRSTNAME),
                                rs.getString(COLUMN_LASTNAME),
                                rs.getString("event_datetime"),
                                rs.getString("status")
                        )
        );
    }

    public List<Booking> getBookingByUserId(Long userId)
    {
        String sql = "SELECT ID, BOOKING_NUMBER FROM BOOKING WHERE ID_USER = ? AND STATUS <> 'CANCELED'";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Booking(rs.getLong("id"), rs.getString(COLUMN_BOOKING_NUMBER)), userId);
    }

    public List<Booking> getBookingDetailByUserId(Long userId)
    {
        String sql = "SELECT BK.ID, BK.BOOKING_NUMBER, BK.ADDRESS, BK.PHONE_NUMBER, BK.EVENT_DATETIME, CT.NAME AS CATEGORY, PK.NAME AS WEDDING_PACKAGE " +
                "FROM BOOKING BK INNER JOIN CATEGORY CT ON BK.ID_CATEGORY = CT.ID INNER JOIN PACKAGE PK ON BK.ID_PACKAGE = PK.ID " +
                "WHERE BK.ID_USER = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Booking(
                        rs.getLong("id"),
                        rs.getString(COLUMN_BOOKING_NUMBER),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("event_datetime"),
                        rs.getString("category"),
                        rs.getString("wedding_package")), userId);
    }

    public List<PaymentType> getListPaymentType(int[] paymentTypeIds)
    {
        String sql;

        if (paymentTypeIds.length > 0)
        {
            sql = "SELECT ID, NAME FROM PAYMENT_TYPE WHERE ID NOT IN (" + Arrays.stream(paymentTypeIds).mapToObj(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        else
        {
            sql = "SELECT ID, NAME FROM PAYMENT_TYPE";
        }

        return jdbcTemplate.query(sql, (rs, rowNum) -> new PaymentType(rs.getLong("id"), rs.getString("name")));
    }

    public List<Payment> getPaymentByBookingId(Long id)
    {
        String sql = "SELECT ID, ID_BOOKING, ID_PAYMENT_TYPE, PAYMENT_DATE, PAYMENT_RECEIPT FROM PAYMENT WHERE ID_BOOKING = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Payment(
                        rs.getLong("id"),
                        rs.getLong("id_booking"),
                        rs.getLong("id_payment_type"),
                        rs.getDate("payment_date"),
                        rs.getString("payment_receipt")
                ), id);
    }

    public List<Payment> getPaymentDetailByBookingId(Long id)
    {
        String sql = "SELECT PY.ID, PYT.NAME, PY.PAYMENT_DATE, PY.PAYMENT_TOTAL, PY.PAYMENT_RECEIPT FROM PAYMENT PY INNER JOIN PAYMENT_TYPE PYT ON PY.ID_PAYMENT_TYPE = PYT.ID WHERE PY.ID_BOOKING = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Payment(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDate("payment_date"),
                        rs.getLong("payment_total"),
                        rs.getString("payment_receipt")
                ), id);
    }

    public Booking getBookingByNumber(String bookingNumber)
    {
        Booking booking;

        String sql = "SELECT ID, BOOKING_NUMBER FROM BOOKING WHERE BOOKING_NUMBER = ?";

        try
        {
            booking = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Booking(rs.getLong(COLUMN_ID), rs.getString(COLUMN_BOOKING_NUMBER)), bookingNumber);

            return booking;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public List<User> getBookingUser()
    {
        String sql = "SELECT DISTINCT USR.ID, USR.FIRSTNAME, USR.LASTNAME, USR.EMAIL FROM USERS USR INNER JOIN BOOKING BK ON USR.ID = BK.ID_USER WHERE BK.STATUS <> 'CANCELED'";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(rs.getLong("id"), rs.getString(COLUMN_FIRSTNAME), rs.getString(COLUMN_LASTNAME), rs.getString("email")));
    }

    public void savePayment(Long bookingId, Long paymentTypeId, Long total, String receipt)
    {
        String sql = "INSERT INTO PAYMENT (ID_BOOKING, ID_PAYMENT_TYPE, PAYMENT_DATE, PAYMENT_TOTAL, PAYMENT_RECEIPT) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bookingId, paymentTypeId, new Date(), total, receipt);
    }

    public List<WeddingPackage> getListPackage()
    {
        String sql = "SELECT ID, NAME, PRICE, DETAIL FROM WEDDING_PACKAGE";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new WeddingPackage(rs.getLong("id"), rs.getString("name"), rs.getString("price"), rs.getString(COLUMN_DETAIL)));
    }

    public List<WeddingPackage> getListPackageV2()
    {
        String sql = "SELECT P.ID, P.NAME, P.PRICE, P.TOTAL_BUFFET, P.ID_BUFFET, B.NAME AS BUFFET_NAME, P.BONUS FROM PACKAGE P INNER JOIN BUFFET B ON P.ID_BUFFET = B.ID";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            WeddingPackage obj = new WeddingPackage();
            obj.setId(rs.getLong("id"));
            obj.setName(rs.getString("name"));
            obj.setPrice(rs.getString("price"));
            obj.setTotalBuffet(rs.getString("total_buffet"));
            obj.setBuffetId(rs.getLong("id_buffet"));
            obj.setBuffetName(rs.getString("buffet_name"));
            obj.setBonus(rs.getString("bonus").split("#"));

            return obj;
        });
    }

    public List<DetailPackage> getDetailPackage(Long id)
    {
        String sql = "SELECT PDP.ID_DETAIL_PACKAGE AS ID, DP.NAME, DP.DETAIL FROM PACKAGE_DETAIL_PACKAGE PDP INNER JOIN DETAIL_PACKAGE DP ON PDP.ID_DETAIL_PACKAGE = DP.ID WHERE PDP.ID_PACKAGE = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new DetailPackage(
                        rs.getLong("id"),
                        rs.getString("name"),
                        null != rs.getString(COLUMN_DETAIL) ? rs.getString(COLUMN_DETAIL).split("#") : null
                ), id);
    }

    public List<DetailBuffet> getDetailBuffet(Long id)
    {
        String sql = "SELECT BDB.ID_DETAIL_BUFFET AS ID, DB.NAME FROM BUFFET_DETAIL_BUFFET BDB INNER JOIN DETAIL_BUFFET DB ON BDB.ID_DETAIL_BUFFET = DB.ID WHERE BDB.ID_BUFFET = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new DetailBuffet(rs.getLong("id"), rs.getString("name")), id);
    }

    public List<DetailPackage> getListDetailPackageV2()
    {
        String sql = "SELECT ID, NAME, DETAIL FROM DETAIL_PACKAGE";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new DetailPackage(
                        rs.getLong("id"),
                        rs.getString("name"),
                        null != rs.getString(COLUMN_DETAIL) ? rs.getString(COLUMN_DETAIL).split("#") : null));
    }

    public List<Buffet> getListBuffetV2()
    {
        String sql = "SELECT ID, NAME FROM BUFFET";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Buffet(
                        rs.getLong("id"),
                        rs.getString("name")));
    }

    public List<DetailBuffet> getListDetailBuffetV2()
    {
        String sql = "SELECT ID, NAME FROM DETAIL_BUFFET";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new DetailBuffet(rs.getLong("id"), rs.getString("name")));
    }

    public void updatePackageBuffet(Long id, Long buffetId)
    {
        String sql = "UPDATE PACKAGE SET ID_BUFFET = ? WHERE ID = ?";
        jdbcTemplate.update(sql, buffetId, id);
    }

    public void updatePackageDetail(Long id, int[] detailPackageIds)
    {
        String sqlDelete = "DELETE FROM PACKAGE_DETAIL_PACKAGE WHERE ID_PACKAGE = ?";
        jdbcTemplate.update(sqlDelete, id);

        StringBuilder sqlInsert = new StringBuilder("INSERT INTO PACKAGE_DETAIL_PACKAGE (ID_PACKAGE, ID_DETAIL_PACKAGE) VALUES ");

        generateQueryParams(id, detailPackageIds, sqlInsert);
    }

    public void updatePackageDetailBuffet(Long id, int[] detailBuffetIds)
    {
        String sqlDelete = "DELETE FROM BUFFET_DETAIL_BUFFET WHERE ID_BUFFET = ?";
        jdbcTemplate.update(sqlDelete, id);

        StringBuilder sqlInsert = new StringBuilder("INSERT INTO BUFFET_DETAIL_BUFFET (ID_BUFFET, ID_DETAIL_BUFFET) VALUES ");

        generateQueryParams(id, detailBuffetIds, sqlInsert);
    }

    public void deletePackage(Long packageId)
    {
        String sql = "DELETE FROM PACKAGE WHERE ID = ?";
        jdbcTemplate.update(sql, packageId);
    }

    public List<Building> getBuildingByPackage(Long packageId)
    {
        String sql = "SELECT B.ID, B.NAME, B.ADDRESS FROM BUILDING B INNER JOIN PACKAGE_BUILDING PB ON B.ID = PB.ID_BUILDING WHERE PB.ID_PACKAGE = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Building(rs.getLong("id"), rs.getString("name"), rs.getString("address")), packageId);
    }

    public List<WeddingGallery> getGallery()
    {
        String sql = "SELECT G.ID, C.NAME, G.IMAGE_URL FROM GALLERY G INNER JOIN CATEGORY C ON G.ID_CATEGORY = C.ID;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new WeddingGallery(rs.getLong("id"), rs.getString("name"), rs.getString("image_url")));
    }

    public void addPackage(Request request)
    {
        String sql = "INSERT INTO WEDDING_PACKAGE (NAME, PRICE, DETAIL) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getPackageName(), request.getPackagePrice(), request.getPackageDetail());
    }

    public long addPackageV2(Request request)
    {
        String sql = "INSERT INTO PACKAGE (NAME, PRICE, TOTAL_BUFFET, ID_BUFFET, BONUS) VALUES (?, ?, ?, ?, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, request.getPackageName());
            statement.setString(2, request.getPackagePrice());
            statement.setString(3, request.getTotalBuffet());
            statement.setLong(4, request.getBuffetId());
            statement.setString(5, String.join("#", request.getBonus()));

            return statement;
        }, holder);

        return Objects.requireNonNull(holder.getKey()).longValue();
    }

    public void addDetailPackageV2(long id, int[] detailPackageIds)
    {
        StringBuilder sqlInsert = new StringBuilder("INSERT INTO PACKAGE_DETAIL_PACKAGE (ID_PACKAGE, ID_DETAIL_PACKAGE) VALUES ");

        generateQueryParams(id, detailPackageIds, sqlInsert);
    }

    private void generateQueryParams(Long id, int[] ids, StringBuilder sqlBuilder)
    {
        for (int i = 0; i < ids.length; i++)
        {
            sqlBuilder.append("(").append(id).append(",").append(ids[i]).append(")");

            if (i + 1 != ids.length)
            {
                sqlBuilder.append(",");
            }
        }

        jdbcTemplate.update(sqlBuilder.toString());
    }

    public List<WeddingCategory> getCategory()
    {
        String sql = "SELECT ID, NAME FROM CATEGORY";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new WeddingCategory(rs.getLong("id"), rs.getString("name")));
    }

    public void uploadPhoto(Request request)
    {
        String sql = "INSERT INTO GALLERY (ID_CATEGORY, IMAGE_URL) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getWeddingCategoryId(), request.getPhotoUrl());
    }
}