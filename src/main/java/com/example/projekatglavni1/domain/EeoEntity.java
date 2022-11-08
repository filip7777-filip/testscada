package com.example.projekatglavni1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="eeo")
public class EeoEntity {
	@Id
	@SequenceGenerator(
			sequenceName = "eeo",
			name ="eeo",
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "eeo"
			)
	@Column(updatable = false, nullable = false)
	private long id;
	private String naziv;
	private String naponPrikljucenja;
	private boolean sdu;
	private String adresa;
	private String nazivRtu;
	@ManyToOne
	@JoinColumn(name ="eeoentity_id", referencedColumnName = "id", nullable = true)
	private EeoTypeEntity type;
	@ManyToOne
	@JoinColumn(name="lum_id", referencedColumnName = "id", nullable = true)
	private LumEntity lum;
	@ManyToOne
	@JoinColumn(name = "centar_id", referencedColumnName = "id", nullable = true)
	private CenterEntity center;
	
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
	public EeoTypeEntity getType() {
		return type;
	}
	public void setType(EeoTypeEntity type) {
		this.type = type;
	}
	public LumEntity getLum() {
		return lum;
	}
	public void setLum(LumEntity lum) {
		this.lum = lum;
	}
	public CenterEntity getCenter() {
		return center;
	}
	public void setCenter(CenterEntity center) {
		this.center = center;
	}
	
	
}
