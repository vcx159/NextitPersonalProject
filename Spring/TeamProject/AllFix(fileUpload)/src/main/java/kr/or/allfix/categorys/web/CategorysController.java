package kr.or.allfix.categorys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.allfix.brands.service.BrandsVo;
import kr.or.allfix.categorys.service.CategorysService;
import kr.or.allfix.categorys.service.CategorysVo;

@Controller
@RequestMapping(value={"/categorys"})
public class CategorysController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategorysService categorysService;
	
	@RequestMapping(value={"/selectCategorysList.json"})
	public @ResponseBody HashMap<String, Object> selectCategorysList(
			@RequestParam("typeId") String typeId
			) {
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			List<CategorysVo> brandList = categorysService.selectCategorysList(typeId);
			result.put("brandItems", brandList);
			result.put("status", true);
		} catch (Exception e) {
			result.put("status",false);
			result.put("message",e.getMessage());
			
			log.error(e.getMessage(), e);
		}
		
		return result;
	}
}
