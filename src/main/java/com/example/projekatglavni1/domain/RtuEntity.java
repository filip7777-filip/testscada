package com.example.projekatglavni1.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.tool.schema.extract.internal.SequenceNameExtractorImpl;

@Entity
@Table(name ="rtu")
public class RtuEntity {
	@Id
	@SequenceGenerator(
			name ="rtu_sequence",
			sequenceName = "rtu_sequence",
			allocationSize = 1
			)
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
				generator = "rtu_sequence"
			)
	@Column(nullable = false, updatable = false)
	private long id;
	
	@Basic
	@Column(name = "name", nullable = false)
	private String name;
	
	
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
