package kr.or.allfix.contactreplys.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.allfix.contactreplys.service.ContactReplysVo;

@Controller
@RequestMapping(value= {"/contactReplys"})
public class ContactReplysController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value={"/insertContactReplyInfoForm.do"})
	public String insertContactReplyInfoForm() throws Exception {
		return "";
	}
	
	@RequestMapping(value={"/updateContactReplyInfoForm.do"})
	public String updateContactReplyInfoForm() throws Exception {
		
		return "";
	}
}
