package kr.or.allfix.fixregs.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import kr.or.allfix.attach.service.AttachVo;

public class FixRegsVo {

    private String seqNo;
    private String serviceType;
    private String cenNo;
    private String cenName;    
    private String cateNo;
    private String typeName;
    private String brandName;    
    private String productName;
    private String detail;
    private String price;
    private String status;
    private String refSeqNo;
    private String isDel;
    private String regUser;
    private String regDt;
    private String updUser;
    private String updDt;
    
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
