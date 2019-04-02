package kr.or.allfix.categorys.service;

import java.util.HashMap;
import java.util.List;

import kr.or.allfix.brands.service.BrandsVo;

public interface CategorysService {

	public List<CategorysVo> selectCategorysList(String typeId) throws Exception;
	public CategorysVo selectCategoryInfo(HashMap<String, Object> params) throws Exception;
}
