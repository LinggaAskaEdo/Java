package com.pdf.parser.model;

public class Konsolidasi
{
	private String npwp;
	private String nama;
	private String alamat;
	private String pabeanAsal;
	private String pabeanEkspor;
	private String negaraTujuan;
	private String sarana;
	private String noFlight;

	public String getNpwp()
	{
		return npwp;
	}

	public void setNpwp(String npwp)
	{
		this.npwp = npwp;
	}

	public String getNama()
	{
		return nama;
	}

	public void setNama(String nama)
	{
		this.nama = nama;
	}

	public String getAlamat()
	{
		return alamat;
	}

	public void setAlamat(String alamat)
	{
		this.alamat = alamat;
	}

	public String getPabeanAsal()
	{
		return pabeanAsal;
	}

	public void setPabeanAsal(String pabeanAsal)
	{
		this.pabeanAsal = pabeanAsal;
	}

	public String getPabeanEkspor()
	{
		return pabeanEkspor;
	}

	public void setPabeanEkspor(String pabeanEkspor)
	{
		this.pabeanEkspor = pabeanEkspor;
	}

	public String getNegaraTujuan()
	{
		return negaraTujuan;
	}

	public void setNegaraTujuan(String negaraTujuan)
	{
		this.negaraTujuan = negaraTujuan;
	}

	public String getSarana()
	{
		return sarana;
	}

	public void setSarana(String sarana)
	{
		this.sarana = sarana;
	}

	public String getNoFlight()
	{
		return noFlight;
	}

	public void setNoFlight(String noFlight)
	{
		this.noFlight = noFlight;
	}

	@Override
	public String toString()
	{
		return "Konsolidasi [NPWP=" + npwp + ", nama=" + nama + ", alamat=" + alamat + ", pabeanAsal=" + pabeanAsal
				+ ", pabeanEkspor=" + pabeanEkspor + ", negaraTujuan=" + negaraTujuan + ", sarana=" + sarana
				+ ", noFlight=" + noFlight + "]";
	}
}