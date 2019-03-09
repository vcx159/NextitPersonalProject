package kr.or.wjdrmstn.account.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccountVo {

    private String accId;
    private String accPw;
    private int employeeId;
    private String authorId;
    private String isDel;
    private String create_date;
    private String update_date;
    
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public String getAccPw() {
		return accPw;
	}
	public void setAccPw(String accPw) {
		this.accPw = accPw;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
    
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
