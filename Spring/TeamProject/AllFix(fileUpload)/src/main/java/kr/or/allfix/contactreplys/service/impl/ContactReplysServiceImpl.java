package kr.or.allfix.contactreplys.service.impl;

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
	public void insertContactReplyInfoProc(ContactReplysVo contactReplysVo) throws Exception {
		
		contactReplysMapper.insertContactReplyInfo(contactReplysVo);
	}
}
