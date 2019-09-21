package com.pdf.parser.model;

import java.util.Date;

public class Data
{
	private String nomorPEB;
	private Date tanggalPEB;
	private String nomorNPE;
	private Date tanggalNPE;
	private String keterangan;
	
	public void setNomorPEB(String nomorPEB) 
	{
		this.nomorPEB = nomorPEB;
	}
	
	public void setTanggalPEB(Date tanggalPEB)
	{
		this.tanggalPEB = tanggalPEB;
	}
	
	public void setNomorNPE(String nomorNPE) 
	{
		this.nomorNPE = nomorNPE;
	}
	
	public void setTanggalNPE(Date tanggalNPE) 
	{
		this.tanggalNPE = tanggalNPE;
	}
	
	public void setKeterangan(String keterangan) 
	{
		this.keterangan = keterangan;
	}

	@Override
	public String toString()
	{
		return "Data [nomorPEB=" + nomorPEB + ", tanggalPEB=" + tanggalPEB + ", nomorNPE=" + nomorNPE + ", tanggalNPE="
				+ tanggalNPE + ", keterangan=" + keterangan + "]";
	}
}