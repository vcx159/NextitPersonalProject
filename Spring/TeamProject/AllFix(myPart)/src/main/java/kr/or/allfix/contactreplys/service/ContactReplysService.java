package kr.or.allfix.contactreplys.service;

import java.util.HashMap;
import java.util.List;

public interface ContactReplysService {

	public List<ContactReplysVo> selectContactReplysListProc(String replySeqNo) throws Exception;
	public ContactReplysVo selectContactReplyInfoProc(String seqNo) throws Exception;
	public void insertContactReplyInfoProc(ContactReplysVo contactReplysVo) throws Exception;
	public void updateContactReplyInfoProc(ContactReplysVo contactReplysVo) throws Exception;
	public void deleteContactReplyInfoProc(HashMap<String, Object> params) throws Exception;
}
