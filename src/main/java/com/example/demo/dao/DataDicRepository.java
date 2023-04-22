package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.DataDic;
import com.example.demo.entity.pk.DicEntityPk;

public interface DataDicRepository extends CrudRepository<DataDic, DicEntityPk> {
	
	List<DataDic> findByType(String type);

}
