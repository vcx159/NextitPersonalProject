package kr.or.allfix.contactreplys.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.contactreplys.service.ContactReplysVo;

@Mapper
public interface ContactReplysMapper {

	public List<ContactReplysVo> selectContactReplysListProc(String replySeqNo) throws Exception;
	public void insertContactReplyInfo(ContactReplysVo contactReplysVo) throws Exception;
	
}
