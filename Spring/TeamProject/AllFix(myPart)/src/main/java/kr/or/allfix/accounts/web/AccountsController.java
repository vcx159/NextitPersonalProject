package kr.or.allfix.accounts.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.allfix.accounts.service.AccountsSearchVo;
import kr.or.allfix.accounts.service.AccountsService;
import kr.or.allfix.accounts.service.AccountsVo;
import kr.or.allfix.com.utils.CookieBox;


@Controller
@RequestMapping(value="/accounts")
public class AccountsController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("AccountsService")
	private AccountsService accountsService;
	
	/**
	 * 로그인
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginForm.do")
	public String loginForm(
			Model model,
			HttpServletRequest request
			) throws Exception {
			
		CookieBox cookieBox = new CookieBox(request);
		
		model.addAttribute("reaccountsValue", cookieBox.getValue("reaccounts"));
		model.addAttribute("reaccountsId", cookieBox.getValue("reaccountsId"));
		
		
		return "accounts/loginForm";
	}
	
	@RequestMapping(value= "/loginProc.do")
	public String loginProc(
			@ModelAttribute AccountsVo accountsVo, 
			@RequestParam(name="reaccounts", required=false) String isSave,
			@RequestParam HashMap<String, Object> params,
			HttpSession session, 
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		/*log.debug("modelAttribute params : {}", accountsVo);*/
		
		CookieBox cookieBox = new CookieBox(request);
		
		
		
		
		if ("isSave".equals(isSave)) {
			Cookie reaccounts = cookieBox.createCookie("reaccounts", "checked=\"checked\"", "/", 60 * 60 * 24);
			response.addCookie(reaccounts);
			
			Cookie reaccountsId = cookieBox.createCookie("reaccountsId", accountsVo.getAccId(),"/", 60*60*24);
			response.addCookie(reaccountsId);
		} else {

			Cookie reaccounts = cookieBox.createCookie("reaccounts", "", "/", 0);
			response.addCookie(reaccounts);
			
			Cookie reaccountsId = cookieBox.createCookie("reaccountsId", "", "/", 0);
			response.addCookie(reaccountsId);
		}
		
		
		AccountsVo resultLoginVo = accountsService.loginInfo(accountsVo);
		
		if (resultLoginVo != null && resultLoginVo.getAccId().equals(accountsVo.getAccId())) {
			// 로그인 성공..
			session.setAttribute("userInfo", resultLoginVo);
		
			return "redirect:/";
		
		} else {
			// 로그인 실패
			
			session.removeAttribute("userInfo");
			
			return "redirect:/accounts/loginForm.do";
		}
	
	}
	
	@RequestMapping(value= "/logoutProc.do")
	public String logoutProc(
			HttpSession session
			) throws Exception {
		
		session.invalidate();
		
		return "redirect:/accounts/loginForm.do";
	}
	
	
	/**
	 * 회원 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/accountsList.do")
	public String accountsList() throws Exception{
		
		return "accounts/accountsList";
	}
	
	@RequestMapping(value="/accountsListProc.json")
	@ResponseBody
	public HashMap<String, Object> selectAccountsList(
			@ModelAttribute AccountsSearchVo searchVo
			) throws Exception{
		
		HashMap<String, Object> accountsList = new HashMap<>();
		
		
		try {
			
			int totalCnt = accountsService.selectAccountsListCnt(searchVo);
			
			searchVo.setTotalCount(totalCnt);
			searchVo.setPageBlockSize(3);
			searchVo.setScreenSize(10);
			searchVo.pageSetting();
			
			List<AccountsVo> accountsItemList = accountsService.selectAccountsList(searchVo); 
			accountsList.put("items", accountsItemList);
			accountsList.put("pagingVo", searchVo);
			accountsList.put("status", true);
			accountsList.put("message", "회원 목록");
		} catch (Exception e) {

			accountsList.put("status", false);
			accountsList.put("message", e.getMessage());
			
			log.debug(e.getMessage());
		}

		
		return accountsList;
		
	}
	
	/**
	 * 회원 등록
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertAccountsForm.do")
	public String insertAccountsForm() throws Exception {
		
		return "accounts/insertAccountsForm";
		
	}
	

	
	@RequestMapping(value= {"/insertAccountsInfoProc.json"})
	@ResponseBody
	public HashMap<String, Object> insertAccountsProc(
			@ModelAttribute AccountsVo accountsVo
			) throws Exception {
		
/*		String email_id = (String) params.get("e_id");
		String email_url = (String) params.get("url");
		
		StringBuilder str = new StringBuilder();
		str.append(email_id).append("@").append(email_url);
		
		accountsVo.setEmail(str.toString());*/
		
		HashMap<String, Object> result = new HashMap<>();

		try {
			accountsService.insertAccountsProc(accountsVo);
			
			result.put("status", true);
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", e.getMessage());
			
			log.error(e.getMessage(), e);
		}
		
		return result;
	}

	
	/**
	 * ID 체크
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/idCheck.json")
	@ResponseBody
	public HashMap<String, Object> idCheckProc(
			@RequestParam HashMap<String, Object> params
			) throws Exception {
		
		HashMap<String, Object> result = new HashMap<>();
		
		String userId = (String)params.get("accId");
		
		try {
			if(StringUtils.isEmpty(userId)) {
				throw new Exception("ID를 입력해 주세요");
			}
			
			boolean resultStatus = accountsService.selectIdCheck(params);
			
			
			
			params.put("status", resultStatus);
			params.put("checkId", userId);
			result.put("items", params);
			
			
			result.put("status", true);
			result.put("message", "사용 가능한 ID 입니다.");
		} catch (Exception e) {
			
			result.put("status", false);
			result.put("message", e.getMessage());
			
			params.put("status", false);
			params.put("checkId", "");
			result.put("items", params);
		}
		
		return result;
	}
	
	
	/**
	 * 회원 정보 상세보기
	 * 
	 * @param model
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/accountsView.do")
	public String accountsView(
			Model model,
			@RequestParam HashMap<String, Object> params
			) throws Exception {
		
		AccountsVo accountsInfo = accountsService.selectAccountsInfo(params);
		
		model.addAttribute("accountsInfo",accountsInfo);
		
		return "accounts/accountsView";
	}
	
	@RequestMapping(value="/updProfile.do")
	public String updProfile(
			Model model,
			@RequestParam HashMap<String, Object> params
			) throws Exception {
		
		AccountsVo accountsInfo = accountsService.selectAccountsInfo(params);
		
		model.addAttribute("accountsInfo",accountsInfo);
		
		return "accounts/updProfile";
	}
	
	/**
	 * 관리자가 회원 정보 수정 페이지
	 * <사용 안함>
	 * 
	 * @param accountsVo
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAccountsProc.json")
	@ResponseBody
	public HashMap<String, Object> updateAccountsProc(
			@ModelAttribute AccountsVo accountsVo,
			@RequestParam HashMap<String, Object> params
			) throws Exception {
		
		HashMap<String, Object> updateResult = new HashMap<>();
		
		try {
			
			accountsService.updateAccountsInfo(accountsVo);
			
			updateResult.put("status", true);
			updateResult.put("message","수정이 완료 되었습니다.");
		} catch (Exception e) {
			updateResult.put("status", false);
			updateResult.put("message", e.getMessage());
			
			log.error(e.getMessage(), e);
		}
		
		return updateResult;

	}
	
	/**
	 * 회원 탈퇴
	 * isDel => Y (update)
	 * 
	 * @param params
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAccountsProc.json")
	@ResponseBody
	public HashMap<String, Object> deleteAccountsProc(
			@RequestParam HashMap<String, Object> params,
			HttpSession session
			) throws Exception {
		
		HashMap<String, Object> deleteResult = new HashMap<>();
		
		try {
			accountsService.deleteAccountsInfo(params);
			
			deleteResult.put("status", true);
			deleteResult.put("message", "탈퇴가 완료되었습니다.");
			session.invalidate();
			
		} catch (Exception e) {
			deleteResult.put("status", false);
			deleteResult.put("message", e.getMessage());
			
			log.error(e.getMessage());
		}
		
		
		
		
		return deleteResult;
		
	}
	
	
}
