package com.pdf.parser.model;

public class Data
{
	private String nomorPEB;
	private String tanggalPEB;
	private String nomorNPE;
	private String tanggalNPE;
	private String keterangan;

	public String getNomorPEB()
	{
		return nomorPEB;
	}

	public void setNomorPEB(String nomorPEB)
	{
		this.nomorPEB = nomorPEB;
	}

	public String getTanggalPEB()
	{
		return tanggalPEB;
	}

	public void setTanggalPEB(String tanggalPEB)
	{
		this.tanggalPEB = tanggalPEB;
	}

	public String getNomorNPE()
	{
		return nomorNPE;
	}

	public void setNomorNPE(String nomorNPE)
	{
		this.nomorNPE = nomorNPE;
	}

	public String getTanggalNPE()
	{
		return tanggalNPE;
	}

	public void setTanggalNPE(String tanggalNPE)
	{
		this.tanggalNPE = tanggalNPE;
	}

	public String getKeterangan()
	{
		return keterangan;
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