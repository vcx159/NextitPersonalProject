package kr.or.allfix.centercategory.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.allfix.centercategory.service.CenterCategoryService;
import kr.or.allfix.centercategory.service.CenterCategoryVo;

@Service("CenterCategoryService")
public class CenterCategoryServiceImpl implements CenterCategoryService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CenterCategoryMapper centerCategoryMapper;
	
	@Override
	public List<CenterCategoryVo> selectCenterCategoryList(HashMap<String, Object> params) throws Exception {
		return centerCategoryMapper.selectCenterCategoryList(params);
	}

}
