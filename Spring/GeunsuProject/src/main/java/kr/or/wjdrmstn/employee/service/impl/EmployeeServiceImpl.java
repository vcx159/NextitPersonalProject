package kr.or.wjdrmstn.employee.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.wjdrmstn.contract.service.ContractVo;
import kr.or.wjdrmstn.contract.service.impl.ContractMapper;
import kr.or.wjdrmstn.employee.service.EmployeeSearchVo;
import kr.or.wjdrmstn.employee.service.EmployeeService;
import kr.or.wjdrmstn.employee.service.EmployeeVo;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private ContractMapper contractMapper;
	
	@Override
	public List<EmployeeVo> selectEmployeeList(EmployeeSearchVo searchVo) throws Exception {
		
		List<EmployeeVo> employeeList = employeeMapper.selectEmployeeList(searchVo);
		
		return employeeList;
	}
	
	@Override
	public int selectEmployeeListCnt(EmployeeSearchVo searchVo) throws Exception {
		
		return employeeMapper.selectEmployeeListCnt(searchVo);
	}
	
	@Override
	public EmployeeVo selectEmployeeInfoDetail(int employeeId) throws Exception {
		
		return employeeMapper.selectEmployeeInfoDetail(employeeId);
	}
	
	@Override
	public EmployeeVo selectEmployeeInfoUpdate(int employeeId) throws Exception {
		
		return employeeMapper.selectEmployeeInfoUpdate(employeeId);
	}
	
	@Override
	public void insertEmployeeInfo(EmployeeVo employeeVo, ContractVo contractVo) throws Exception {
		
		employeeMapper.insertEmployeeInfo(employeeVo);
		contractVo.setEmployeeId(employeeVo.getEmployeeId());
		contractMapper.insertContractInfo(contractVo);
	}
	
	@Override
	public void updateEmployeeInfo(EmployeeVo employeeVo) throws Exception {

		employeeMapper.updateEmployeeInfo(employeeVo);
	}
	
	@Override
	public void deleteEmployeeInfo(int employeeId) throws Exception {

		employeeMapper.deleteEmlpoyeeInfo(employeeId);
	}
}
