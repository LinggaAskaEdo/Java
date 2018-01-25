package com.main.java.invoice.project.pojo;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class PoEvent
{
    private Integer poEventId;
    private String poEventNo;
    private Integer konrakId;
    private String kegiatan;
    private Date tanggal;
    private BigDecimal jumlah;
    private String keterangan;
    private Blob bukti;


}