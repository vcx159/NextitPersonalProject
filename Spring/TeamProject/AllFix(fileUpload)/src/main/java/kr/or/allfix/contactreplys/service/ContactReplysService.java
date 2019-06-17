package kr.or.allfix.contactreplys.service;

import java.util.List;

public interface ContactReplysService {

	public List<ContactReplysVo> selectContactReplysListProc(String replySeqNo) throws Exception;
	public void insertContactReplyInfoProc(ContactReplysVo contactReplysVo) throws Exception;
}
