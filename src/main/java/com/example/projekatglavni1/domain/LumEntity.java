package com.example.projekatglavni1.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lum")
public class LumEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private long id;
	@Basic
	@Column(name = "name", nullable =false )
	private String name;
	
	public LumEntity() {
		// TODO Auto-generated constructor stub
	}
	public LumEntity(long id, String naziv) {
		super();
		this.id = id;
		this.name = naziv;
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

	public void setName(String naziv) {
		this.name = naziv;
	}
	
	
}
