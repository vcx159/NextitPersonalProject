package kr.or.wjdrmstn.employee.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EmployeeVo {

    private int employeeId;
    private String employeeName;
    private String gender;
    private String juminNumber;
    private String phoneNumber;
    private String email;
    private int addrZip;
    private String addrDetail1;
    private String addrDetail2;
    private String salary;
    private String jijumId;
    private String jobId;
    private String refSeqNo;
    private String isDel;
    private String createDate;
    private String updateDate;

    public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJuminNumber() {
		return juminNumber;
	}

	public void setJuminNumber(String juminNumber) {
		this.juminNumber = juminNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAddrZip() {
		return addrZip;
	}

	public void setAddrZip(int addrZip) {
		this.addrZip = addrZip;
	}

	public String getAddrDetail1() {
		return addrDetail1;
	}

	public void setAddrDetail1(String addrDetail1) {
		this.addrDetail1 = addrDetail1;
	}

	public String getAddrDetail2() {
		return addrDetail2;
	}

	public void setAddrDetail2(String addrDetail2) {
		this.addrDetail2 = addrDetail2;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getJijumId() {
		return jijumId;
	}

	public void setJijumId(String jijumId) {
		this.jijumId = jijumId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getRefSeqNo() {
		return refSeqNo;
	}

	public void setRefSeqNo(String refSeqNo) {
		this.refSeqNo = refSeqNo;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
