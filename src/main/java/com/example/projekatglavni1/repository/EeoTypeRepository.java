package com.example.projekatglavni1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projekatglavni1.domain.EeoTypeEntity;

@Repository
public interface EeoTypeRepository  extends JpaRepository<EeoTypeEntity,Long>{

}
