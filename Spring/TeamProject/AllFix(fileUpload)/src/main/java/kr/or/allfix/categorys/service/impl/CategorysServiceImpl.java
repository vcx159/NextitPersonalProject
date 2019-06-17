package kr.or.allfix.categorys.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.allfix.brands.service.BrandsVo;
import kr.or.allfix.categorys.service.CategorysService;
import kr.or.allfix.categorys.service.CategorysVo;

@Service("CategorysService")
public class CategorysServiceImpl implements CategorysService{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategorysMapper categorysMapper;
	
	@Override
	public List<CategorysVo> selectCategorysList(String typeId) throws Exception {
		
		return categorysMapper.selectCategorysList(typeId);
	}
	
	@Override
	public CategorysVo selectCategoryInfo(HashMap<String, Object> params) throws Exception {
		
		return categorysMapper.selectCategoryInfo(params);
	}
}
