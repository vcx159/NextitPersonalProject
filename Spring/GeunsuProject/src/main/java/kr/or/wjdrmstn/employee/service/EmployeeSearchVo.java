package kr.or.wjdrmstn.employee.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import kr.or.wjdrmstn.com.utils.PagingVo;

public class EmployeeSearchVo extends PagingVo {

	private String employeeId;
	private String jijumId;
	private String searchType;
	private String searchText;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String empId) {
		this.employeeId = empId;
	}
	public String getJijumId() {
		return jijumId;
	}
	public void setJijumId(String jijumId) {
		this.jijumId = jijumId;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
