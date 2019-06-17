package kr.or.allfix.attach.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.allfix.accounts.service.AccountsVo;
import kr.or.allfix.attach.service.AttachService;
import kr.or.allfix.attach.service.AttachVo;

@Controller
@RequestMapping(value="/attach")
public class AttachController {
private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("AttachService")
	private AttachService attachService;
	
	@Resource(name="propertyService")
	private Properties propertiesService;

	
	@RequestMapping(value="/fileUploadAjaxProc.json")
	public @ResponseBody HashMap<String, Object> fileUploadAjaxProc(
			@RequestParam HashMap<String, Object> params,
			@RequestParam("imageFiles") List<MultipartFile> imageFiles,
			HttpServletRequest reqeust) throws Exception{
		
		log.debug("params: {}", params);
		
		//params 변수에 사용자 아이피 추가
		params.put("usrIp", reqeust.getRemoteAddr());
		
		//사용자 세션 정보 가지고 옴
		AccountsVo loginInfo = (AccountsVo) reqeust.getSession().getAttribute("userInfo");
		
		if(loginInfo != null) {
			//로그인이 되어있으면
			params.put("regUser", loginInfo.getAccId());
			params.put("updUser", loginInfo.getAccId());
			if(imageFiles != null) {
				params.put("serviceType", "IMAGE");
			}

		}else {
			//로그인이 안 되어있으면
			params.put("regUser", "GUEST");
			params.put("updUser", "GUEST");
		}
		log.debug("params: {}", params);
		
		HashMap<String, Object> fileUploadResult = new HashMap<>();
		
		try {
			List<AttachVo> attachList = new ArrayList<>();
			if(imageFiles != null) {
				attachList = attachService.insertAttachList(imageFiles, params);
			}

			fileUploadResult.put("attachList", attachList);
			
			fileUploadResult.put("status", true);
			fileUploadResult.put("message","비동기 파일 업로드 테스트 중");
			
			log.debug("result: {}", fileUploadResult);
		} catch (Exception e) {
			fileUploadResult.put("status", false);
			fileUploadResult.put("message",e.getMessage());
			
			log.error(e.getMessage(), e);
		}
		return fileUploadResult;
	}
	
	@RequestMapping(value="/imageDeleteAjaxProc.json") 
	public @ResponseBody HashMap<String, Object> fileUploadAjaxProc(
			@RequestParam HashMap<String, Object> params,
			HttpServletRequest reqeust){

		
		//params 변수에 사용자 아이피 추가
		params.put("usrIp", reqeust.getRemoteAddr());
		
		//사용자 세션 정보 가지고 옴
		AccountsVo loginInfo = (AccountsVo) reqeust.getSession().getAttribute("userInfo");
		
		if(loginInfo != null) {
			//로그인이 되어있으면
			params.put("regUser", loginInfo.getAccId());
			params.put("updUser", loginInfo.getAccId());
			params.put("serviceType", "ATTACH");
		}else {
			//로그인이 안 되어있으면
			params.put("regUser", "GUEST");
			params.put("updUser", "GUEST");
		}
		
		HashMap<String, Object> result = new HashMap<>();
		try {
			
			String seqNo = (String) params.get("seqNo");
			
			attachService.deleteAttach(seqNo);
		}catch(Exception e) {
			result.put("status", false);
			result.put("message",e.getMessage());
			
			log.error(e.getMessage(), e);
		}
		
		return result;
	}
}
