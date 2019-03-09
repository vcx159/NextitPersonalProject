package kr.or.wjdrmstn.account.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.wjdrmstn.account.service.AccountService;
import kr.or.wjdrmstn.account.service.AccountVo;
import kr.or.wjdrmstn.com.utils.CookieBox;
import kr.or.wjdrmstn.employee.service.EmployeeService;
import kr.or.wjdrmstn.employee.service.EmployeeVo;
import kr.or.wjdrmstn.jijum.service.JijumService;
import kr.or.wjdrmstn.jijum.service.JijumVo;

@Controller
public class AccountController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private JijumService jijumService;
	
	@RequestMapping(value = "/")
	public String loginForm(Model model, HttpServletRequest request) throws Exception {
		log.info("Welcome home! The client locale is kr");
		
		CookieBox cookieBox = new CookieBox(request);
		model.addAttribute("rememberValue", cookieBox.getValue("remember"));
		model.addAttribute("rememberId", cookieBox.getValue("rememberId"));
		
		return "login/loginForm";
	}
	
	@RequestMapping(value="/loginProc.do")
	public String loginProc(Model model, @ModelAttribute AccountVo loginVoParam, 
							@RequestParam(name="idSave", required=false) String idSave,
							HttpServletRequest request,
							HttpServletResponse response,
							HttpSession session) throws Exception {
		
		log.debug("{}",idSave);
		CookieBox cookieBox = new CookieBox(request);
		if("remember".equals(idSave)) {
			//쿠키 생성
			Cookie remember = cookieBox.createCookie("remember", "checked='checked'","/",60*60*24);
			response.addCookie(remember);
			
			Cookie rememberId = cookieBox.createCookie("rememberId", loginVoParam.getAccId(),"/",60*60*24);
			response.addCookie(rememberId);
			
		}else {
			//쿠키 삭제
			Cookie remember = cookieBox.createCookie("remember", "", "/", 0);
			response.addCookie(remember);
			
			Cookie rememberId = cookieBox.createCookie("rememberId", "", "/", 0);
			response.addCookie(rememberId);
		}
		
		AccountVo account = accountService.selectAccountInfo(loginVoParam);
		
		if(account != null) {
			EmployeeVo employee = employeeService.selectEmployeeInfoDetail(account.getEmployeeId());
			
			JijumVo jijum = jijumService.selectJijumInfo(employee.getJijumId());
			
			session.setAttribute("userInfo", employee);
			session.setAttribute("jijumInfo", jijum);			
			
			log.debug("관리자 정보 {}", employee);
			log.debug("지점 정보 {}", jijum);
			
			if(account.getAuthorId().equals("JI_MANAGER")) {
				return "redirect:manager/managerMain.do";
			}else {
				return "redirect:/";
			}
		}else {
			model.addAttribute("message","등록된 계정이 아닙니다");
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/logoutProc.do")
	public String logoutProc(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
}
