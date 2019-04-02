package kr.or.allfix.centercategory.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.centercategory.service.CenterCategoryVo;

@Mapper
public interface CenterCategoryMapper {

	public List<CenterCategoryVo> selectCenterCategoryList(HashMap<String, Object> params) throws Exception;
}
