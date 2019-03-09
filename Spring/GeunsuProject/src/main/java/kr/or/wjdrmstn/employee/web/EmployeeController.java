package kr.or.wjdrmstn.employee.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.wjdrmstn.attach.service.AttachService;
import kr.or.wjdrmstn.attach.service.AttachVo;
import kr.or.wjdrmstn.contract.service.ContractService;
import kr.or.wjdrmstn.contract.service.ContractVo;
import kr.or.wjdrmstn.employee.service.EmployeeSearchVo;
import kr.or.wjdrmstn.employee.service.EmployeeService;
import kr.or.wjdrmstn.employee.service.EmployeeVo;
import kr.or.wjdrmstn.jijum.service.JijumVo;

@Controller
@RequestMapping(value={"/manager"})
public class EmployeeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private AttachService attachService;
		
	@RequestMapping(value={"/empAjaxList.do"})
	public String selectEmployeeList()throws Exception{
		
		return "employee/empAjaxList";
	}
	
	@RequestMapping(value={"/empAjaxList.json"})
	public @ResponseBody HashMap<String, Object> selectAjaxEmployeeList(@ModelAttribute EmployeeSearchVo searchVo, HttpSession session) throws Exception {
		
		HashMap<String, Object> empMap = new HashMap<>();
		
		try {
			
			JijumVo jijumVo = (JijumVo) session.getAttribute("jijumInfo");
			
			searchVo.setJijumId(jijumVo.getJijumId());
			
			searchVo.setTotalCount(employeeService.selectEmployeeListCnt(searchVo));
			searchVo.setScreenSize(10);
			searchVo.setPageBlockSize(5);
			searchVo.pageSetting();
			
			List<EmployeeVo> empList = employeeService.selectEmployeeList(searchVo);
			
			empMap.put("empItems", empList);
			empMap.put("pagingVo", searchVo);
			empMap.put("status", true);
			empMap.put("message", "정상적으로 처리되었습니다");
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			
			empMap.put("status", false);
			empMap.put("message", e.getMessage());			
		}
		
		return empMap;
	}
	
	@RequestMapping(value= {"/employeeView.do"}) 
	public String selectEmployeeInfo(Model model, @RequestParam("employeeId") int employeeId) throws Exception{
		
		EmployeeVo employeeVo = employeeService.selectEmployeeInfoDetail(employeeId);
		ContractVo contractVo = contractService.selectContractInfo(employeeId);
		List<AttachVo> attachVoList = attachService.selectAttachList(employeeVo.getRefSeqNo());
		
		model.addAttribute("employeeVo", employeeVo);
		model.addAttribute("contractVo", contractVo);
		model.addAttribute("attachVoList", attachVoList);
		return "employee/employeeView";
	}
	
	@RequestMapping(value={"/insertEmployeeForm.do"})
	public String insertEmployeeInfo() throws Exception {
		

		return "employee/insertEmployeeForm";
	}
	
	@RequestMapping(value={"/insertEmployeeProc.do"})
	public String insertEmployeeProc(@ModelAttribute EmployeeVo employeeVo,
										@ModelAttribute ContractVo contractVo, 
										@RequestPart("attachFiles") List<MultipartFile> attachMultiPart,
										HttpSession session) throws Exception{
		JijumVo jijumVo = (JijumVo) session.getAttribute("jijumInfo");

		try {
			
			String refSeqNo = attachService.insertAttachList(attachMultiPart, "EMPLOYEE");
			
			employeeVo.setRefSeqNo(refSeqNo);
			
			employeeVo.setJijumId(jijumVo.getJijumId());
			
			employeeService.insertEmployeeInfo(employeeVo, contractVo);
			
			return "redirect:/manager/empAjaxList.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "employee/insertEmployeeForm";
		}
	}
	
	@RequestMapping(value={"/updateEmployeeForm.do"})
	public String updateEmployeeInfo(Model model, @RequestParam("employeeId") int employeeId) throws Exception{
		
		EmployeeVo employeeVo = employeeService.selectEmployeeInfoUpdate(employeeId);
		ContractVo contractVo = contractService.selectContractInfo(employeeId);
		
		model.addAttribute("employeeVo", employeeVo);
		model.addAttribute("contractVo", contractVo);
		
		return "employee/updateEmployeeForm";
	}
	
	@RequestMapping(value={"/updateEmployeeProc.do"})
	public String updateEmployeeInfoProc(Model model, @ModelAttribute EmployeeVo employeeVo, @ModelAttribute ContractVo contractVo) throws Exception{
		
		try {
			employeeService.updateEmployeeInfo(employeeVo);
			contractService.updateContractInfo(contractVo);
			return "redirect:/manager/employeeView.do?employeeId="+employeeVo.getEmployeeId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "redirect:/manager/updateEmployeeForm.do?employeeId="+employeeVo.getEmployeeId();
		}
	}
	
	@RequestMapping(value={"/deleteEmployeeProc.do"})
	public String deleteEmployeeProc(@RequestParam("employeeId") int employeeId) throws Exception {
		
		try {
			employeeService.deleteEmployeeInfo(employeeId);
			contractService.deleteContractInfo(employeeId);
			return "redirect:/manager/empAjaxList.do";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "redirect:/manager/employeeView.do?employeeId="+employeeId;
		}
	}
	
}
