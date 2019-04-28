package kr.or.allfix.brands.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.brands.service.BrandsVo;

@Mapper
public interface BrandsMapper {

	public List<BrandsVo> selectBrandsList() throws Exception;
}
