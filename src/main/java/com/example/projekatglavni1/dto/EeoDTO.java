package com.example.projekatglavni1.dto;

public class EeoDTO {
	private long id;
	private String naziv;
	private String naponPrikljucenja;
	private boolean sdu;
	private String adresa;
	private String nazivRtu;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getNaponPrikljucenja() {
		return naponPrikljucenja;
	}
	public void setNaponPrikljucenja(String naponPrikljucenja) {
		this.naponPrikljucenja = naponPrikljucenja;
	}
	public boolean isSdu() {
		return sdu;
	}
	public void setSdu(boolean sdu) {
		this.sdu = sdu;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getNazivRtu() {
		return nazivRtu;
	}
	public void setNazivRtu(String nazivRtu) {
		this.nazivRtu = nazivRtu;
	}
	
	
}
