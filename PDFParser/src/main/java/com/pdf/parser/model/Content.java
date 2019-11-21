package com.pdf.parser.model;

import java.util.List;

public class Content
{
	private String nomorPengajuan;
	private String nomorTanggalPendaftaran;
	private String nomorPetiKemas;
	private String ukuranPetiKemas;
	private String tempatStuffing;
	private String tanggalStuffing;
	private Konsolidasi konsolidasi;
	private List<Data> dataList;

	public String getNomorPengajuan()
	{
		return nomorPengajuan;
	}

	public void setNomorPengajuan(String nomorPengajuan)
	{
		this.nomorPengajuan = nomorPengajuan;
	}

	public String getNomorTanggalPendaftaran()
	{
		return nomorTanggalPendaftaran;
	}

	public void setNomorTanggalPendaftaran(String nomorTanggalPendaftaran)
	{
		this.nomorTanggalPendaftaran = nomorTanggalPendaftaran;
	}

	public String getNomorPetiKemas()
	{
		return nomorPetiKemas;
	}

	public void setNomorPetiKemas(String nomorPetiKemas)
	{
		this.nomorPetiKemas = nomorPetiKemas;
	}

	public String getUkuranPetiKemas()
	{
		return ukuranPetiKemas;
	}

	public void setUkuranPetiKemas(String ukuranPetiKemas)
	{
		this.ukuranPetiKemas = ukuranPetiKemas;
	}

	public String getTempatStuffing()
	{
		return tempatStuffing;
	}

	public void setTempatStuffing(String tempatStuffing)
	{
		this.tempatStuffing = tempatStuffing;
	}

	public String getTanggalStuffing()
	{
		return tanggalStuffing;
	}

	public void setTanggalStuffing(String tanggalStuffing)
	{
		this.tanggalStuffing = tanggalStuffing;
	}

	public Konsolidasi getKonsolidasi()
	{
		return konsolidasi;
	}

	public void setKonsolidasi(Konsolidasi konsolidasi)
	{
		this.konsolidasi = konsolidasi;
	}

	public List<Data> getDataList()
	{
		return dataList;
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