package kr.or.wjdrmstn.jijum.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class JijumVo {

    private String jijumId;
    private String jijumName;
    private int addrZip;
    private String addrDetail;
    private String phoneNumber;
    private String faxNumber;
    private String refSeqNo;
    private String openDate;
    private String closeDate;
    private String isDel;
    private String createDate;
    private String updateDate;
    
	public String getJijumId() {
		return jijumId;
	}
	public void setJijumId(String jijumId) {
		this.jijumId = jijumId;
	}
	public String getJijumName() {
		return jijumName;
	}
	public void setJijumName(String jijumName) {
		this.jijumName = jijumName;
	}
	public int getAddrZip() {
		return addrZip;
	}
	public void setAddrZip(int addrZip) {
		this.addrZip = addrZip;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getRefSeqNo() {
		return refSeqNo;
	}
	public void setRefSeqNo(String refSeqNo) {
		this.refSeqNo = refSeqNo;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
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
