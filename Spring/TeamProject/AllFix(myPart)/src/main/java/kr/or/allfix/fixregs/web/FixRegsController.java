package kr.or.allfix.fixregs.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.allfix.accounts.service.AccountsVo;
import kr.or.allfix.attach.service.AttachService;
import kr.or.allfix.attach.service.AttachVo;
import kr.or.allfix.categorys.service.CategorysService;
import kr.or.allfix.categorys.service.CategorysVo;
import kr.or.allfix.centercategory.service.CenterCategoryService;
import kr.or.allfix.centercategory.service.CenterCategoryVo;
import kr.or.allfix.fixregs.service.FixRegsSearchVo;
import kr.or.allfix.fixregs.service.FixRegsService;
import kr.or.allfix.fixregs.service.FixRegsVo;
import kr.or.allfix.types.service.TypesService;
import kr.or.allfix.types.service.TypesVo;

@Controller
@RequestMapping(value={"/fixRegs"})
public class FixRegsController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private FixRegsService fixRegsService;
	
	@Autowired
	private TypesService typesService;
	
	@Autowired
	private CategorysService categorysService;
	
	@Autowired
	private CenterCategoryService centerCategoryService;
	
	@RequestMapping(value={"/selectFixRegsList.do"})
	public String fixRegsList() throws Exception {
		
		return "fixRegs/selectFixRegsList";
	}
	
	@RequestMapping(value={"/selectFixRegsListProc.json"})
	public @ResponseBody HashMap<String, Object> selectFixRegsListProc(
			@ModelAttribute FixRegsSearchVo searchVo) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			searchVo.setTotalCount(fixRegsService.selectFixRegsListCnt(searchVo));
			searchVo.setScreenSize(10);
			searchVo.setPageBlockSize(5);
			searchVo.pageSetting();
			
			List<FixRegsVo> fixRegsList = fixRegsService.selectFixRegsListProc(searchVo);
			
			result.put("fixRegsItems", fixRegsList);
			result.put("pagingVo", searchVo);
			result.put("status",true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value={"/insertFixRegInfoForm.do"})
	public String insertFixRegInfoForm(Model model) throws Exception {
		
		List<TypesVo> typeList = typesService.selectTypesList();
		model.addAttribute("typeList", typeList);
		
		return "fixRegs/insertFixRegInfoForm";
	}
	
	@RequestMapping(value={"/insertFixRegInfoProc.json"})
	public @ResponseBody HashMap<String, Object> insertFixRegInfoProc(
			@ModelAttribute FixRegsVo fixRegsVo,
			@RequestParam("attachFiles") List<MultipartFile> attachFiles,
			@RequestParam HashMap<String, Object> params,
			HttpServletRequest request,
			HttpSession session
			) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			HashMap<String, Object> attachParams = new HashMap<>();
			attachParams.put("usrIp", request.getRemoteAddr());
			attachParams.put("regUser", accountsVo.getAccId());
			attachParams.put("updUser", accountsVo.getAccId());
			attachParams.put("serviceType", "FIXREGS");			
			String refSeqNo = attachService.insertAttachList(attachFiles, attachParams);
			
			CategorysVo categorysVo = categorysService.selectCategoryInfo(params);
			
			fixRegsVo.setRegUser(accountsVo.getAccId());
			fixRegsVo.setUpdUser(accountsVo.getAccId());
			fixRegsVo.setCateNo(categorysVo.getCateNo());
			fixRegsVo.setRefSeqNo(refSeqNo);
			
			fixRegsService.insertFixRegInfoProc(fixRegsVo);
			
			result.put("status",true);
			result.put("message","입력완료");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		
		return result;
	}
	
	@RequestMapping(value={"/selectFixRegInfoForm.do"})
	public String selectFixRegInfoForm(Model model, @RequestParam("seqNo") String seqNo) throws Exception {
		
		FixRegsVo fixRegInfo = fixRegsService.selectFixRegInfoProc(seqNo);
		model.addAttribute("fixRegInfo", fixRegInfo);
		
		List<AttachVo> attachVoList = attachService.selectAttachList(fixRegInfo.getRefSeqNo());
		model.addAttribute("attachVoList", attachVoList);
		
		return "fixRegs/selectFixRegsInfoForm";
	}
	
	@RequestMapping(value={"/updateFixRegInfoForm.do"})
	public String updateFixRegInfoForm(Model model, @RequestParam("seqNo") String seqNo) throws Exception {
		
		FixRegsVo fixRegInfo = fixRegsService.selectFixRegInfoProc(seqNo);
		model.addAttribute("fixRegInfo", fixRegInfo);
		
		List<TypesVo> typeList = typesService.selectTypesList();
		model.addAttribute("typeList", typeList);
		
		List<CategorysVo> brandList = categorysService.selectCategorysList(fixRegInfo.getTypeId());
		model.addAttribute("brandList", brandList);
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("typeId", fixRegInfo.getTypeId());
		params.put("brandId", fixRegInfo.getBrandId());
		List<CenterCategoryVo> centerList = centerCategoryService.selectCenterCategoryList(params);
		model.addAttribute("centerList", centerList);
		
		return "fixRegs/updateFixRegsInfoForm";
	}

	@RequestMapping(value={"/updateFixRegInfoProc.json"})
	public @ResponseBody HashMap<String, Object> updateFixRegInfoProc(
			@ModelAttribute FixRegsVo fixRegsVo,
			@RequestParam HashMap<String, Object> params,
			HttpSession session) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			CategorysVo categorysVo = categorysService.selectCategoryInfo(params);
			
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			fixRegsVo.setUpdUser(accountsVo.getAccId());
			fixRegsVo.setCateNo(categorysVo.getCateNo());
			fixRegsService.updateFixRegInfoProc(fixRegsVo);
			
			result.put("status",true);
			result.put("message","수정완료");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		return result;
		
	}
	
	@RequestMapping(value={"/updateFixRegCancelProc.json"})
	public @ResponseBody HashMap<String, Object> updateFixRegCancelProc(
			@RequestParam HashMap<String, Object> params,
			HttpSession session) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			params.put("updUser",accountsVo.getAccId());
			fixRegsService.updateFixRegCancelProc(params);
			
			result.put("status", true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		
		return result;
	}
	
	@RequestMapping(value={"/deleteFixRegInfoProc.json"})
	public @ResponseBody HashMap<String, Object> deleteFixRegInfoProc(
			@RequestParam HashMap<String, Object> params,
			HttpSession session) {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			params.put("updUser",accountsVo.getAccId());
			fixRegsService.deleteFixRegInfoProc(params);
			
			if(!"".equals((String)params.get("refSeqNo"))) {
				attachService.deleteAttachList(params);
			}
				
			result.put("status", true);
			result.put("message","삭제완료");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		return result;
		
	}

}
