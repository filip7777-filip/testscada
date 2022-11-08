package com.example.projekatglavni1.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.example.projekatglavni1.domain.CenterEntity;
import com.example.projekatglavni1.dto.CentarDTO;

@Component
public class CentarConverter {

	
	private ModelMapper modelMapper;

	
	@Autowired
	public CentarConverter(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}
	
	public List<CentarDTO> toCentarDTOs(List<CenterEntity> centerList){
		try {
			List<CentarDTO> listCenterDTOs = new ArrayList<>();
			centerList.forEach(center -> {
				CentarDTO centarDTO = this.modelMapper.map(center, CentarDTO.class);
				listCenterDTOs.add(centarDTO);
			});
			return listCenterDTOs;
		} catch (Exception e) {
			throw e;
		}
	}
}
