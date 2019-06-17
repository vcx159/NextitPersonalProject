package kr.or.allfix.fixregs.web;

import java.util.ArrayList;
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
	private FixRegsService fixRegsService;
	
	@Autowired
	private TypesService typesService;
	
	@Autowired
	private CategorysService categorysService;
	
	@Autowired
	private AttachService attachService;
	
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
			HttpServletRequest reqeust,
			HttpSession session
			) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			CategorysVo categorysVo = categorysService.selectCategoryInfo(params);
			
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			List<AttachVo> attachList = new ArrayList<>();
			if(attachFiles != null) { 
				params.put("usrIp", reqeust.getRemoteAddr());
				params.put("serviceType", "ATTACH");
				params.put("regUser", accountsVo.getAccId());
				params.put("updUser", accountsVo.getAccId());
				attachList = attachService.insertAttachList(attachFiles, params);
				log.debug("{}", attachList);
				log.debug("{}", attachList);
				log.debug("{}", attachList);
				log.debug("{}", attachList);
				fixRegsVo.setRefSeqNo(attachList.get(0).getRefSeqNo());
			}
			
			
			fixRegsVo.setRegUser(accountsVo.getAccId());
			fixRegsVo.setUpdUser(accountsVo.getAccId());
			fixRegsVo.setCateNo(categorysVo.getCateNo());
			
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
		
		return "fixRegs/selectFixRegsInfoForm";
	}
	
	@RequestMapping(value={"/updateFixRegInfoForm.do"})
	public String updateFixRegInfoForm(Model model, @RequestParam("seqNo") String seqNo) throws Exception {
		
		FixRegsVo fixRegInfo = fixRegsService.selectFixRegInfoProc(seqNo);
		model.addAttribute("fixRegInfo", fixRegInfo);
		
		//여기서 게시글과 관련된 첨부파일들을 전부 불러와서 form에 보내고
		
		return "fixRegs/updateFixRegsInfoForm";
	}

	@RequestMapping(value={"/updateFixRegInfoProc.json"})
	public @ResponseBody HashMap<String, Object> updateFixRegInfoProc(
			@ModelAttribute FixRegsVo fixRegsVo,
			HttpSession session) throws Exception {
		
		//requestparam을 통하여 삭제할 첨부파일 시퀸스 번호와 새로 등록할 첨부파일을 분리하여
		//첨부파일 매퍼에 insert와 delete문 수행
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			fixRegsVo.setUpdUser(accountsVo.getAccId());
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
	
	@RequestMapping(value={"/deleteFixRegInfoProc.json"})
	public @ResponseBody HashMap<String, Object> deleteFixRegInfoProc(
			@RequestParam HashMap<String, Object> params,
			HttpSession session) {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			params.put("updUser",accountsVo.getAccId());
			fixRegsService.deleteFixRegInfoProc(params);
			
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
