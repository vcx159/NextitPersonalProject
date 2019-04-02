package kr.or.allfix.contactreplys.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.allfix.contactreplys.service.ContactReplysService;
import kr.or.allfix.contactreplys.service.ContactReplysVo;

@Service("ContactReplysService")
public class ContactReplysServiceImpl implements ContactReplysService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContactReplysMapper contactReplysMapper;
	
	@Override
	public List<ContactReplysVo> selectContactReplysListProc(String replySeqNo) throws Exception {
		return contactReplysMapper.selectContactReplysListProc(replySeqNo);
	}
	
	@Override
	public ContactReplysVo selectContactReplyInfoProc(String seqNo) throws Exception {
		return contactReplysMapper.selectContactReplyInfoProc(seqNo);
	}
	
	@Override
	public void insertContactReplyInfoProc(ContactReplysVo contactReplysVo) throws Exception {
		
		int cnt = contactReplysMapper.insertContactReplyInfoProc(contactReplysVo);
		
		if(cnt == 0) {
			throw new Exception("입력을 실패했습니다");
		}
	}
	
	@Override
	public void updateContactReplyInfoProc(ContactReplysVo contactReplysVo) throws Exception {
	
		int cnt = contactReplysMapper.updateContactReplyInfoProc(contactReplysVo);
		
		if(cnt == 0) {
			throw new Exception("수정을 실패했습니다");
		}
	}
	
	@Override
	public void deleteContactReplyInfoProc(HashMap<String, Object> params) throws Exception {
		
		int cnt = contactReplysMapper.deleteContactReplyInfoProc(params);
		
		if(cnt == 0) {
			throw new Exception("삭제를 실패했습니다");
		}
		
	}
}
