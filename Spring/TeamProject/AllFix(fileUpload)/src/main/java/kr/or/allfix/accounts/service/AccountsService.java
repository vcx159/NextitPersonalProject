package kr.or.allfix.accounts.service;

import java.util.HashMap;
import java.util.List;

public interface AccountsService {
	
	public AccountsVo loginInfo(
			AccountsVo loginVo
			) throws Exception;

	public void insertAccountsProc(
			AccountsVo accountsVo
			) throws Exception;
	
	public boolean selectIdCheck(
			HashMap<String, Object> params
			) throws Exception;
	
	public List<AccountsVo> selectAccountsList(
			AccountsSearchVo searchVo
			) throws Exception;
	
	public int selectAccountsListCnt(
			AccountsSearchVo searchVo
			) throws Exception;
	
	public AccountsVo selectAccountsInfo(
			HashMap<String, Object> params
			) throws Exception;
	
	public void updateAccountsInfo(
			AccountsVo accountsVo
			) throws Exception;
	
	public void deleteAccountsInfo(
			HashMap<String, Object> params
			) throws Exception;
}
