package kr.or.allfix.contacts.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.allfix.contacts.service.ContactsSearchVo;
import kr.or.allfix.contacts.service.ContactsService;
import kr.or.allfix.contacts.service.ContactsVo;

@Service("ContactsService")
public class ContactsServiceImpl implements ContactsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContactsMapper contactsMapper;
	
	@Override
	public List<ContactsVo> selectContactsListProc(ContactsSearchVo searchVo) throws Exception {
		return contactsMapper.selectContactsListProc(searchVo);
	}
	
	@Override
	public int selectContactsListCnt(ContactsSearchVo searchVo) throws Exception {
		return contactsMapper.selectContactsListCnt(searchVo);
	}
	
	@Override
	public ContactsVo selectContactInfoProc(String seqNo) throws Exception {
		return contactsMapper.selectContactInfoProc(seqNo);
	}
	
	@Override
	public void insertContactInfoProc(ContactsVo contactsVo) throws Exception {

		contactsMapper.insertContactInfoProc(contactsVo);
	}
}
