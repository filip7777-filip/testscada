package com.example.projekatglavni1.service;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projekatglavni1.domain.CenterType;
import com.example.projekatglavni1.repository.CenterTypeRepository;



@Service
@Transactional
public class CenterTypeServiceImpl implements CenterTypeService{
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private CenterTypeRepository centerTypeRepository;
	
	@Autowired
	public CenterTypeServiceImpl(CenterTypeRepository centerTypeRepository) {
		super();
		this.centerTypeRepository = centerTypeRepository;
	}
	
	@Override
	public List<CenterType> findAll() {
		List<CenterType> list = centerTypeRepository.findAll();
		return list;
	}

	@Override
	public CenterType add(String name) throws Exception {
		checkCenterType(StringUtils.EMPTY, name);
		CenterType ct = new CenterType();
		ct.setName(name);
		centerTypeRepository.save(ct);
		LOGGER.info("Novi tip centra:"+ct.getName());
		return ct;
	}

	private void checkCenterType(String currentName, String name) throws Exception {
//		if(StringUtils.isNotBlank(currentName)) {
//			CenterType currentType = findCenterTypeByName(name);
//			if(currentType == null) {
//				throw new Exception("Ne postoji tip sa imenom:"+currentName);
//			}
//			CenterType newType = findCenterTypeByName(name);
//			if(newType != null && newType.getId()==currentType.getId()) {
//				throw new Exception("Postoji tip sa ovim imenom!");
//			}
//			return currentType;
//		}else {
//			CenterType newTypeCenter = findCenterTypeByName(name);
//			if(name != null) {
//				throw new Exception("Postoji tip sa ovim imenom!");
//			}
//			return null;
//		}
		
		List<CenterType> list = centerTypeRepository.findAll();
		
		for(CenterType t : list) {
			if(currentName.equals(t.getName())) {throw new Exception("Greska 1!");
			}
		}
		for(CenterType type : list) {
			if(name.equals(type.getName())) {throw new Exception("Greska!");}
		}
		
		LOGGER.info("ODRADIO");
	}

	@Override
	public CenterType findCenterTypeByName(String name) {
		return centerTypeRepository.findByName(name);
	}

	

	

	

}
