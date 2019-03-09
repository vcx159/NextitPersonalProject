package kr.or.wjdrmstn.contract.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ContractVo {

    private int seqNo;
    private int employeeId;
    private String hireDate;
    private String retireDate;
    private String isRegular;
    private String isDel;
    private String createDate;
    private String updateDate;
    
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getRetireDate() {
		return retireDate;
	}
	public void setRetireDate(String retireDate) {
		this.retireDate = retireDate;
	}
	public String getIsRegular() {
		return isRegular;
	}
	public void setIsRegular(String isRegular) {
		this.isRegular = isRegular;
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
