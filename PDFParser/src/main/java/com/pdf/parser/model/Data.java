package com.pdf.parser.model;

public class Data
{
	private String nomorPEB;
	private String tanggalPEB;
	private String nomorNPE;
	private String tanggalNPE;
	private String keterangan;
	
	public void setNomorPEB(String nomorPEB) 
	{
		this.nomorPEB = nomorPEB;
	}
	
	public void setTanggalPEB(String tanggalPEB)
	{
		this.tanggalPEB = tanggalPEB;
	}
	
	public void setNomorNPE(String nomorNPE) 
	{
		this.nomorNPE = nomorNPE;
	}
	
	public void setTanggalNPE(String tanggalNPE)
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