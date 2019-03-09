package kr.or.wjdrmstn.employee.service;

import java.util.HashMap;
import java.util.List;

import kr.or.wjdrmstn.contract.service.ContractVo;

public interface EmployeeService {

	public List<EmployeeVo> selectEmployeeList(EmployeeSearchVo searchVo) throws Exception;
	public int selectEmployeeListCnt(EmployeeSearchVo searchVo) throws Exception;
	public EmployeeVo selectEmployeeInfoDetail(int employeeId) throws Exception;
	public EmployeeVo selectEmployeeInfoUpdate(int employeeId) throws Exception;
	public void insertEmployeeInfo(EmployeeVo employeeVo, ContractVo contractVo) throws Exception;
	public void updateEmployeeInfo(EmployeeVo employeeVo) throws Exception;
	public void deleteEmployeeInfo(int employeeId) throws Exception;
	
}
