package kr.or.allfix.accounts.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccountsVo {

	 private String seqNo;
	 private String accId;
	 private String accPw;
	 private String accName;
	 private String gender;
	 private String birth;
	 private String phoneNumber;
	 private String email;
	 private String addrZip;
	 private String addrDetail1;
	 private String addrDetail2;
	 private String refSeqNo;
	 private String authorId;
	 private String isDel;
	 private String regUser;
	 private String regDt;
	 private String updUser;
	 private String updDt;
	 private String accPwRe;
	 
	 private String reaccounts;
	 
	 
	 
	 @Override
	public String toString() {
		// TODO Auto-generated method stub
		 return ToStringBuilder.reflectionToString(this);
	}
	 
	 
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
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
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
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
	public String getRefSeqNo() {
		return refSeqNo;
	}
	public void setRefSeqNo(String refSeqNo) {
		this.refSeqNo = refSeqNo;
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


	public String getReaccounts() {
		return reaccounts;
	}


	public void setReaccounts(String reaccounts) {
		this.reaccounts = reaccounts;
	}


	public String getAccPwRe() {
		return accPwRe;
	}


	public void setAccPwRe(String accPwRe) {
		this.accPwRe = accPwRe;
	}
	
	 
}
