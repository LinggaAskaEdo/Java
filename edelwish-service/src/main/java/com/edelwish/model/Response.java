package com.edelwish.model;

import java.util.List;

public class Response
{
    //Error Objects
    private Integer code;
    private String message;
    private String detailMessage;
    private String serverity;
    private Boolean error;

    private User user;
    private List<Testimoni> testimoniList;
    private List<Booking> bookingList;
    private String bookingNumber;
    private List<PaymentType> paymentTypes;
    private List<WeddingPackage> weddingPackages;
    private List<WeddingGallery> weddingGalleries;
    private List<WeddingCategory> weddingCategories;
    private List<DetailPackage> detailPackages;
    private List<DetailBuffet> detailBuffets;
    private List<Buffet> buffets;
    private List<PaymentHistory> paymentHistories;
    private List<Building> buildingList;

    public Response()
    {}

    public Response(int code, String message, String detailMessage, String serverity, boolean error)
    {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
        this.serverity = serverity;
        this.error = error;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
    }

    public String getServerity()
    {
        return serverity;
    }

    public void setServerity(String serverity)
    {
        this.serverity = serverity;
    }

    public Boolean getError()
    {
        return error;
    }

    public void setError(Boolean error)
    {
        this.error = error;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<Testimoni> getTestimoniList()
    {
        return testimoniList;
    }

    public void setTestimoniList(List<Testimoni> testimoniList)
    {
        this.testimoniList = testimoniList;
    }

    public List<Booking> getBookingList()
    {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList)
    {
        this.bookingList = bookingList;
    }

    public String getBookingNumber()
    {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber)
    {
        this.bookingNumber = bookingNumber;
    }

    public List<PaymentType> getPaymentTypes()
    {
        return paymentTypes;
    }

    public void setPaymentTypes(List<PaymentType> paymentTypes)
    {
        this.paymentTypes = paymentTypes;
    }

    public List<WeddingPackage> getWeddingPackages()
    {
        return weddingPackages;
    }

    public void setWeddingPackages(List<WeddingPackage> weddingPackages)
    {
        this.weddingPackages = weddingPackages;
    }

    public List<WeddingGallery> getWeddingGalleries()
    {
        return weddingGalleries;
    }

    public void setWeddingGalleries(List<WeddingGallery> weddingGalleries)
    {
        this.weddingGalleries = weddingGalleries;
    }

    public List<WeddingCategory> getWeddingCategories()
    {
        return weddingCategories;
    }

    public void setWeddingCategories(List<WeddingCategory> weddingCategories)
    {
        this.weddingCategories = weddingCategories;
    }

    public List<DetailPackage> getDetailPackages()
    {
        return detailPackages;
    }

    public void setDetailPackages(List<DetailPackage> detailPackages)
    {
        this.detailPackages = detailPackages;
    }

    public List<DetailBuffet> getDetailBuffets()
    {
        return detailBuffets;
    }

    public void setDetailBuffets(List<DetailBuffet> detailBuffets)
    {
        this.detailBuffets = detailBuffets;
    }

    public List<Buffet> getBuffets()
    {
        return buffets;
    }

    public void setBuffets(List<Buffet> buffets)
    {
        this.buffets = buffets;
    }

    public List<PaymentHistory> getPaymentHistories()
    {
        return paymentHistories;
    }

    public void setPaymentHistories(List<PaymentHistory> paymentHistories)
    {
        this.paymentHistories = paymentHistories;
    }

    public List<Building> getBuildingList()
    {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList)
    {
        this.buildingList = buildingList;
    }
}