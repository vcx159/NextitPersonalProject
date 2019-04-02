package kr.or.allfix.contactreplys.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import kr.or.allfix.attach.service.AttachVo;

public class ContactReplysVo {

    private String replySeqNo;
    private String seqNo;
    private String contents;
    private String refSeqNo;
    private String usrIp;
    private String isDel;
    private String regUser;
    private String regDt;
    private String updUser;
    private String updDt;
    private List<AttachVo> attachList;
    
	public String getReplySeqNo() {
		return replySeqNo;
	}
	public void setReplySeqNo(String replySeqNo) {
		this.replySeqNo = replySeqNo;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRefSeqNo() {
		return refSeqNo;
	}
	public void setRefSeqNo(String refSeqNo) {
		this.refSeqNo = refSeqNo;
	}
	public String getUsrIp() {
		return usrIp;
	}
	public void setUsrIp(String usrIp) {
		this.usrIp = usrIp;
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
	public List<AttachVo> getAttachList() {
		return attachList;
	}
    public void setAttachList(List<AttachVo> attachList) {
		this.attachList = attachList;
	}
	
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
