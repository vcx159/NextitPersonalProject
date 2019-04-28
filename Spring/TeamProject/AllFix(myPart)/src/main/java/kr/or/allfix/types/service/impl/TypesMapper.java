package kr.or.allfix.types.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.types.service.TypesVo;

@Mapper
public interface TypesMapper {

	public List<TypesVo> selectTypesList() throws Exception;
}
