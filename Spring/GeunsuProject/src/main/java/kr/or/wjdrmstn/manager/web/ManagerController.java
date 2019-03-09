package kr.or.wjdrmstn.manager.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/managerMain.do")
	public String managerMain(Model model) {
		
		model.addAttribute("activeMain", "class='active'");
		return "manager/managerMain";
	}
	@RequestMapping(value="/managerInfo.do")
	public String managerInfo(Model model) {
		
		model.addAttribute("activeInfo", "class='active'");
		return "manager/managerInfo";
	}
	@RequestMapping(value="/managerJijum.do")
	public String managerJijum(Model model) {
		
		model.addAttribute("activeMain", "class='active'");
		return "manager/managerJijum";
	}
	
}
