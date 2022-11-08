package com.example.projekatglavni1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "center")
public class CenterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private long id;
	private String name;
	private String adress;
	private String code;
	private String phoneNumber;
	@ManyToOne
	@JoinColumn(name ="superiorcentar_id", referencedColumnName = "id", nullable = true)
	private CenterEntity superiorCenter;
	@ManyToOne
	@JoinColumn(name = "centertype_id", referencedColumnName = "id", nullable = false)
	private CenterType centerType;
	@ManyToOne
	@JoinColumn(name = "responsiveperson_id", referencedColumnName = "id", nullable = false)
	private OdgovornoLice responsivePerson;
	
	public CenterEntity() {
		// TODO Auto-generated constructor stub
	}

	

	public CenterEntity(long id, String name, String adress, String code, String phoneNumber,
			CenterEntity superiorCenter, CenterType centerType, OdgovornoLice responsivePerson) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.code = code;
		this.phoneNumber = phoneNumber;
		this.superiorCenter = superiorCenter;
		this.centerType = centerType;
		this.responsivePerson = responsivePerson;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public CenterEntity getSuperiorCenter() {
		return superiorCenter;
	}

	public void setSuperiorCenter(CenterEntity superiorCenter) {
		this.superiorCenter = superiorCenter;
	}



	public CenterType getCenterType() {
		return centerType;
	}



	public void setCenterType(CenterType centerType) {
		this.centerType = centerType;
	}



	public OdgovornoLice getResponsivePerson() {
		return responsivePerson;
	}



	public void setResponsivePerson(OdgovornoLice responsivePerson) {
		this.responsivePerson = responsivePerson;
	}

	
	
}
