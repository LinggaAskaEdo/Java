package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class PoMedia
{
    private Integer poMediaId;
    private String poMediaNo;
    private Integer kontrakId;
    private String pekerjaanKementerian;
    private Integer masterMediaId;
    private Date tanggalTayang;
    private String ukuran;
    private BigDecimal harga;
    private BigDecimal ppn;
    private String keterangan;
    private Blob buktiPotongPph;


}