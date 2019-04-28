package kr.or.allfix.centercategory.web;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.allfix.centercategory.service.CenterCategoryService;
import kr.or.allfix.centercategory.service.CenterCategoryVo;
import kr.or.allfix.centers.service.CentersVo;

@Controller
@RequestMapping(value={"/centerCategory"})
public class CenterCategoryController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CenterCategoryService centerCategoryService;
	
	@RequestMapping(value={"/selectCenterCategorysList.json"})
	public @ResponseBody HashMap<String, Object> selectCenterCategoryList(
			@RequestParam HashMap<String, Object> params
			) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			List<CenterCategoryVo> centerList = centerCategoryService.selectCenterCategoryList(params);
			
			result.put("centerItems", centerList);
			result.put("status",false);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
}
