package kr.or.allfix.contactreplys.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.contactreplys.service.ContactReplysVo;

@Mapper
public interface ContactReplysMapper {

	public List<ContactReplysVo> selectContactReplysListProc(String replySeqNo) throws Exception;
	public ContactReplysVo selectContactReplyInfoProc(String seqNo) throws Exception;
	public int insertContactReplyInfoProc(ContactReplysVo contactReplysVo) throws Exception;
	public int updateContactReplyInfoProc(ContactReplysVo contactReplysVo) throws Exception;
	public int deleteContactReplyInfoProc(HashMap<String, Object> params) throws Exception;
	
}
