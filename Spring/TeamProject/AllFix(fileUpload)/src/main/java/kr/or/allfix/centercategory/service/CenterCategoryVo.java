package kr.or.allfix.centercategory.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CenterCategoryVo {

    private String cencateNo;
    private String cenNo;
    private String cenName;
    private String cateNo;
    private String isDel;
    private String regUser;
    private String regDt;
    private String updUser;
    private String updDt;
    
	public String getCencateNo() {
		return cencateNo;
	}
	public void setCencateNo(String cencateNo) {
		this.cencateNo = cencateNo;
	}
	public String getCenNo() {
		return cenNo;
	}
	public void setCenNo(String cenNo) {
		this.cenNo = cenNo;
	}
	public String getCenName() {
		return cenName;
	}
	public void setCenName(String cenName) {
		this.cenName = cenName;
	}
	public String getCateNo() {
		return cateNo;
	}
	public void setCateNo(String cateNo) {
		this.cateNo = cateNo;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getRegUser() {
		return regUser;
	}
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdUser() {
		return updUser;
	}
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
    
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
