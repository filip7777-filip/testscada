package com.example.projekatglavni1.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="eeotype")
public class EeoTypeEntity {
	@Id
	@SequenceGenerator(
			name="eeotype_sequence",
			sequenceName="eeotype_sequence",
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "eeotype_sequence"
			)
	@Column(nullable = false, updatable = false)
	private long id;
	@Basic
	@Column(name ="name", nullable = false)
	private String name;
	public EeoTypeEntity() {
		// TODO Auto-generated constructor stub
	}
//	public EeoTypeEntity(long id, String name) {
//		super();
//		this.id = id;
//		this.name = name;
//	}
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
	
	
}
