package kr.or.allfix.contacts.service;

import java.util.List;

public interface ContactsService {

	public List<ContactsVo> selectContactsListProc(ContactsSearchVo searchVo) throws Exception;
	public int selectContactsListCnt(ContactsSearchVo searchVo) throws Exception;
	public ContactsVo selectContactInfoProc(String seqNo) throws Exception;
	public void insertContactInfoProc(ContactsVo contactsVo) throws Exception;
}
