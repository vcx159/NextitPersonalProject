package kr.or.allfix.contacts.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.contacts.service.ContactsSearchVo;
import kr.or.allfix.contacts.service.ContactsVo;

@Mapper
public interface ContactsMapper {

	public List<ContactsVo> selectContactsListProc(ContactsSearchVo searchVo) throws Exception;
	public int selectContactsListCnt(ContactsSearchVo searchVo) throws Exception;
	public ContactsVo selectContactInfoProc(String seqNo) throws Exception;
	public int insertContactInfoProc(ContactsVo contactsVo) throws Exception;
	public int deleteContactInfoProc(HashMap<String, Object> params) throws Exception;
}
