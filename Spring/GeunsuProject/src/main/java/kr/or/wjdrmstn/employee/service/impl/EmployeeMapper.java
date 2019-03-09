package kr.or.wjdrmstn.employee.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.wjdrmstn.employee.service.EmployeeSearchVo;
import kr.or.wjdrmstn.employee.service.EmployeeVo;

@Mapper
public interface EmployeeMapper {

	public List<EmployeeVo> selectEmployeeList(EmployeeSearchVo searchVo) throws Exception;
	public int selectEmployeeListCnt(EmployeeSearchVo searchVo) throws Exception;
	public EmployeeVo selectEmployeeInfoDetail(int employeeId) throws Exception;
	public EmployeeVo selectEmployeeInfoUpdate(int employeeId) throws Exception;
	public void insertEmployeeInfo(EmployeeVo employeeVo) throws Exception;
	public void updateEmployeeInfo(EmployeeVo employeeVo) throws Exception;
	public void deleteEmlpoyeeInfo(int employeeId) throws Exception;
	
}
