package kr.or.wjdrmstn.attach.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AttachVo {

    private String refSeqNo;
    private String seqNo;
    private String serviceType;
    private String fileName;
    private String savePath;
    private String saveName;
    private String saveContentType;
    private long saveSize;
    private String isDel;
    private String createDate;
    private String updateDate;
    
	public String getRefSeqNo() {
		return refSeqNo;
	}
	public void setRefSeqNo(String refSeqNo) {
		this.refSeqNo = refSeqNo;
	}
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getSaveContentType() {
		return saveContentType;
	}
	public void setSaveContentType(String saveContentType) {
		this.saveContentType = saveContentType;
	}
	public long getSaveSize() {
		return saveSize;
	}
	public void setSaveSize(long saveSize) {
		this.saveSize = saveSize;
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
