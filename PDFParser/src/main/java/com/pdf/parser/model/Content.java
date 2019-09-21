package com.pdf.parser.model;

import java.util.Date;
import java.util.List;

public class Content
{
	private String nomorPengajuan;
	private String nomorTanggalPendaftaran;
	private String nomorPetiKemas;
	private String ukuranPetiKemas;
	private String tempatStuffing;
	private Date tanggalStuffing;
	private Konsolidasi konsolidasi;
	private List<Data> dataList;
	
	public void setNomorPengajuan(String nomorPengajuan) 
	{
		this.nomorPengajuan = nomorPengajuan;
	}
	
	public void setNomorTanggalPendaftaran(String nomorTanggalPendaftaran) 
	{
		this.nomorTanggalPendaftaran = nomorTanggalPendaftaran;
	}
	
	public void setNomorPetiKemas(String nomorPetiKemas) 
	{
		this.nomorPetiKemas = nomorPetiKemas;
	}
	
	public void setUkuranPetiKemas(String ukuranPetiKemas)
	{
		this.ukuranPetiKemas = ukuranPetiKemas;
	}
	
	public void setTempatStuffing(String tempatStuffing)
	{
		this.tempatStuffing = tempatStuffing;
	}
	
	public void setTanggalStuffing(Date tanggalStuffing)
	{
		this.tanggalStuffing = tanggalStuffing;
	}
	
	public void setKonsolidasi(Konsolidasi konsolidasi) 
	{
		this.konsolidasi = konsolidasi;
	}

	public void setDataList(List<Data> dataList)
	{
		this.dataList = dataList;
	}

	@Override
	public String toString() 
	{
		return "Content [nomorPengajuan=" + nomorPengajuan + ", nomorTanggalPendaftaran=" + nomorTanggalPendaftaran
				+ ", nomorPetiKemas=" + nomorPetiKemas + ", ukuranPetiKemas=" + ukuranPetiKemas + ", tempatStuffing="
				+ tempatStuffing + ", tanggalStuffing=" + tanggalStuffing + ", konsolidasi=" + konsolidasi
				+ ", dataList=" + dataList + "]";
	}	
}