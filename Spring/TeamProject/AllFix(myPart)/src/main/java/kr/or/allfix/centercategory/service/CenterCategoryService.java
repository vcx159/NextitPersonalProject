package kr.or.allfix.centercategory.service;

import java.util.HashMap;
import java.util.List;

import kr.or.allfix.centers.service.CentersVo;

public interface CenterCategoryService {

	public List<CenterCategoryVo> selectCenterCategoryList(HashMap<String, Object> params) throws Exception;
}
