package kr.or.allfix.accounts.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.accounts.service.AccountsSearchVo;
import kr.or.allfix.accounts.service.AccountsVo;

@Mapper
public interface AccountsMapper {

	public void insertAccountsProc(
			AccountsVo accountsVo
			) throws Exception;
	
	public int selectIdCheck(
			HashMap<String, Object> params
			) throws Exception;
	
	public List<AccountsVo> selectAccountsList(
			AccountsSearchVo searchVo
			) throws Exception;
	
	public AccountsVo selectAccountsInfo(
			HashMap<String, Object> params
			) throws Exception;
	
	public void updateAccountsInfo(
			AccountsVo accountsVo
			) throws Exception;
	
	public AccountsVo loginInfo(
			AccountsVo accountsVo
			) throws Exception;
	
	public void deleteAccountsInfo(
			HashMap<String, Object> params
			) throws Exception;
	
	public int selectAccountsListCnt(
			AccountsSearchVo searchVo
			) throws Exception;
}
