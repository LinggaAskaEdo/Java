package com.pdf.parser.model;

public class Konsolidasi
{
	private String NPWP;
	private String nama;
	private String alamat;
	private String pabeanAsal;
	private String pabeanEkspor;
	private String negaraTujuan;
	private String sarana;
	private String noFlight;

	public void setNPWP(String nPWP)
	{
		NPWP = nPWP;
	}

	public void setNama(String nama) 
	{
		this.nama = nama;
	}

	public void setAlamat(String alamat)
	{
		this.alamat = alamat;
	}

	public void setPabeanAsal(String pabeanAsal) 
	{
		this.pabeanAsal = pabeanAsal;
	}

	public void setPabeanEkspor(String pabeanEkspor) 
	{
		this.pabeanEkspor = pabeanEkspor;
	}

	public void setNegaraTujuan(String negaraTujuan) 
	{
		this.negaraTujuan = negaraTujuan;
	}

	public void setSarana(String sarana)
	{
		this.sarana = sarana;
	}

	public void setNoFlight(String noFlight)
	{
		this.noFlight = noFlight;
	}

	@Override
	public String toString()
	{
		return "Konsolidasi [NPWP=" + NPWP + ", nama=" + nama + ", alamat=" + alamat + ", pabeanAsal=" + pabeanAsal
				+ ", pabeanEkspor=" + pabeanEkspor + ", negaraTujuan=" + negaraTujuan + ", sarana=" + sarana
				+ ", noFlight=" + noFlight + "]";
	}
}