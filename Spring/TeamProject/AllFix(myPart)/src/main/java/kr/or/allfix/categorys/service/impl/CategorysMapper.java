package kr.or.allfix.categorys.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.brands.service.BrandsVo;
import kr.or.allfix.categorys.service.CategorysVo;

@Mapper
public interface CategorysMapper {

	public List<CategorysVo> selectCategorysList(String typeId) throws Exception;
	public CategorysVo selectCategoryInfo(HashMap<String, Object> params) throws Exception;
}
