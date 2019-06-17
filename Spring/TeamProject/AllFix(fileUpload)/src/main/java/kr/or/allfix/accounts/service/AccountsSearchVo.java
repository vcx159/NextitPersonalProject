package kr.or.allfix.accounts.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import kr.or.allfix.com.utils.PagingVo;

public class AccountsSearchVo extends PagingVo {

	private String seqNo;
	private String searchType;
	private String searchText;
	
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
