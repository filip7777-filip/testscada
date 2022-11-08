package com.example.projekatglavni1.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.projekatglavni1.domain.EeoEntity;
import com.example.projekatglavni1.dto.EeoDTO;

public class EeoConverter {
	private ModelMapper modelMapper;
	
	@Autowired
	public EeoConverter(ModelMapper modelMapper) {
		this.modelMapper=modelMapper;
	}
	
	public List<EeoDTO> fromEeoToDto(List<EeoEntity> listOfEeo){
		try {
			List<EeoDTO> dtos = new ArrayList<>();
			listOfEeo.forEach(eeo -> {
				EeoDTO dto = this.modelMapper.map(eeo, EeoDTO.class);
				dtos.add(dto);
			});
			return dtos;
		} catch (Exception e) {
			throw e;
		}
	}
}
