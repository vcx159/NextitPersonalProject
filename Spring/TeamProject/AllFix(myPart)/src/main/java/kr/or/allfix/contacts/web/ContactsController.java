package kr.or.allfix.contacts.web;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
import kr.or.allfix.contacts.service.ContactsSearchVo;
import kr.or.allfix.contacts.service.ContactsService;
import kr.or.allfix.contacts.service.ContactsVo;

@Controller
@RequestMapping(value={"/contacts"})
public class ContactsController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private ContactsService contactsService;
	
	@Autowired
	private ContactReplysService contactReplysSerivce;
	
	@RequestMapping(value={"/selectContactsList.do"})
	public String selectContactsList() throws Exception {
		return "contacts/selectContactsList";
	}
	
	@RequestMapping(value={"/selectContactsListProc.json"})
	public @ResponseBody HashMap<String, Object> selectContactsListProc(
			@ModelAttribute ContactsSearchVo searchVo,
			HttpSession session) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			searchVo.setTotalCount(contactsService.selectContactsListCnt(searchVo));
			searchVo.setScreenSize(10);
			searchVo.setPageBlockSize(5);
			searchVo.pageSetting();
			
			List<ContactsVo> contactList = contactsService.selectContactsListProc(searchVo);
			
			result.put("contactsItem", contactList);
			result.put("pagingVo", searchVo);
			result.put("status",true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value={"/selectContactInfoForm.do"})
	public String selectContactInfoForm(Model model, @RequestParam("seqNo") String seqNo) throws Exception {
		
		ContactsVo contactInfo = contactsService.selectContactInfoProc(seqNo);
		List<ContactReplysVo> contactReplysList = contactReplysSerivce.selectContactReplysListProc(contactInfo.getReplySeqNo());
		
		model.addAttribute("contactInfo", contactInfo);
		
		for(ContactReplysVo crv : contactReplysList) {
			if(crv.getRefSeqNo() != null) {
				List<AttachVo> attachVoList = attachService.selectAttachList(crv.getRefSeqNo());
				crv.setAttachList(attachVoList);
			}
			
		}
		
		model.addAttribute("contactReplysList", contactReplysList);
		
		return "contacts/selectContactsInfoForm";
	}
	
	@RequestMapping(value={"/insertContactInfoForm.do"})
	public String insertContactInfoForm() throws Exception {
		return "contacts/insertContactsInfoForm";
	}
	
	@RequestMapping(value={"/insertContactInfoProc.json"})
	public @ResponseBody HashMap<String, Object> insertContactInfoProc(
			@ModelAttribute ContactsVo contactsVo,
			@ModelAttribute ContactReplysVo contactReplysVo,
			@RequestParam("attachFiles") List<MultipartFile> attachFiles,
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
			attachParams.put("serviceType", "CONTACTREPLY");			
			String refSeqNo = attachService.insertAttachList(attachFiles, attachParams);
			
			String replySeqNo = "R"+UUID.randomUUID().toString();
			
			contactsVo.setReplySeqNo(replySeqNo);
			contactsVo.setRegUser(accountsVo.getAccId());
			contactsVo.setUpdUser(accountsVo.getAccId());
			contactsVo.setUsrIp(request.getRemoteAddr());
			contactReplysVo.setReplySeqNo(replySeqNo);
			contactReplysVo.setRegUser(accountsVo.getAccId());
			contactReplysVo.setUpdUser(accountsVo.getAccId());
			contactReplysVo.setUsrIp(request.getRemoteAddr());
			contactReplysVo.setRefSeqNo(refSeqNo);
			
			contactsService.insertContactInfoProc(contactsVo);
			contactReplysSerivce.insertContactReplyInfoProc(contactReplysVo);
			
			result.put("status",true);
			result.put("message", "입력완료");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			result.put("status",false);
			result.put("message",e.getMessage());
		}
		
		return result;
		
	}
	
	@RequestMapping(value={"/deleteContactInfoProc.json"})
	public @ResponseBody HashMap<String, Object> deleteContactInfoProc(
			@RequestParam HashMap<String, Object> params,
			HttpServletRequest request,
			HttpSession session) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			AccountsVo accountsVo = (AccountsVo) session.getAttribute("userInfo");
			
			params.put("updUser",accountsVo.getAccId());
			params.put("usrIp",request.getRemoteAddr());
			
			contactsService.deleteContactInfoProc(params);
			
			List<ContactReplysVo> replyList = contactReplysSerivce.selectContactReplysListProc((String)params.get("replySeqNo"));
			
			for(ContactReplysVo arr : replyList) {
				params.put("seqNo", arr.getSeqNo());
				contactReplysSerivce.deleteContactReplyInfoProc(params);
				if(arr.getRefSeqNo() != null) {
					params.put("refSeqNo", arr.getRefSeqNo());
					attachService.deleteAttachList(params);
				}
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
