package kr.or.allfix.centers.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CentersVo {

    private String cenNo;
    private String cenName;
    private String addrZip;
    private String addrDetail1;
    private String addrDetail2;
    private String detail;
    private String phoneNumber;
    private String locationUrl;
    private String isDel;
    private String regUser;
    private String regDt;
    private String updUser;
    private String updDt;
    
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
	public String getAddrZip() {
		return addrZip;
	}
	public void setAddrZip(String addrZip) {
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocationUrl() {
		return locationUrl;
	}
	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
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
