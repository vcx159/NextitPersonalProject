package kr.or.allfix.contactreplys.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.allfix.accounts.service.AccountsVo;
import kr.or.allfix.attach.service.AttachService;
import kr.or.allfix.attach.service.AttachVo;
import kr.or.allfix.contactreplys.service.ContactReplysService;
import kr.or.allfix.contactreplys.service.ContactReplysVo;
import kr.or.allfix.contacts.service.ContactsVo;

@Controller
@RequestMapping(value= {"/contactReplys"})
public class ContactReplysController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private ContactReplysService contactReplysService;
	
	@RequestMapping(value={"/insertContactReplyInfoForm.do"})
	public String insertContactReplyInfoForm() throws Exception {
		return "contactReplys/insertContactReplyInfoForm";
	}
	
	@RequestMapping(value={"/insertContactReplyInfoProc.json"})
	public @ResponseBody HashMap<String, Object> insertContactReplyInfoProc(
			@ModelAttribute ContactReplysVo contactReplysVo,
			@RequestParam("attachFiles") List<MultipartFile> attachFiles,
			HttpServletRequest request,
			HttpSession session) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			HashMap<String, Object> attachParams = new HashMap<>();
			attachParams.put("usrIp", request.getRemoteAddr());
			attachParams.put("regUser", accountsVo.getAccId());
			attachParams.put("updUser", accountsVo.getAccId());
			attachParams.put("serviceType", "CONTACTREPLY");			
			String refSeqNo = attachService.insertAttachList(attachFiles, attachParams);
			
			contactReplysVo.setUsrIp(request.getRemoteAddr());
			contactReplysVo.setRefSeqNo(refSeqNo);
			
			contactReplysVo.setRegUser(accountsVo.getAccId());
			contactReplysVo.setUpdUser(accountsVo.getAccId());
			
			contactReplysService.insertContactReplyInfoProc(contactReplysVo);
			
			result.put("status",true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		
		return result;
	}
	
	@RequestMapping(value={"/updateContactReplyInfoForm.do"})
	public String updateContactReplyInfoForm(Model model, @RequestParam("seqNo") String seqNo) throws Exception {
		
		ContactReplysVo contactReplyInfo = contactReplysService.selectContactReplyInfoProc(seqNo);
		model.addAttribute("contactReplyInfo", contactReplyInfo);
		
		List<AttachVo> attachVoList = attachService.selectAttachList(contactReplyInfo.getRefSeqNo());
		model.addAttribute("attachVoList", attachVoList);
		
		return "contactReplys/updateContactReplyInfoForm";
	}
	
	@RequestMapping(value={"/updateContactReplyInfoProc.json"})
	public @ResponseBody HashMap<String, Object> updateContactReplyInfoProc(
			@ModelAttribute ContactReplysVo contactReplysVo, 
			HttpServletRequest request,
			HttpSession session) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			contactReplysVo.setUsrIp(request.getRemoteAddr());
			contactReplysVo.setUpdUser(accountsVo.getAccId());
			
			contactReplysService.updateContactReplyInfoProc(contactReplysVo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value={"/deleteContactReplyInfoProc.json"})
	public @ResponseBody HashMap<String, Object> deleteContactReplyInfoProc(
			@RequestParam HashMap<String, Object> params,
			HttpServletRequest request,
			HttpSession session) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			params.put("usrIp", request.getRemoteAddr());
			params.put("updUser", accountsVo.getAccId());			
			
			contactReplysService.deleteContactReplyInfoProc(params);
			
			if(!"".equals((String)params.get("refSeqNo"))) {
				attachService.deleteAttachList(params);
			}
			
			result.put("status",true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		
		return result;
	}
}
