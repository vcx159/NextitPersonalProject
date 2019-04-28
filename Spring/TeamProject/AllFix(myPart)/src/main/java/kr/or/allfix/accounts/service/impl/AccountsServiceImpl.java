package kr.or.allfix.accounts.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.allfix.accounts.service.AccountsSearchVo;
import kr.or.allfix.accounts.service.AccountsService;
import kr.or.allfix.accounts.service.AccountsVo;

@Service("AccountsService")
public class AccountsServiceImpl implements AccountsService  {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountsMapper accountsMapper;
	
	@Override
	public void insertAccountsProc(AccountsVo accountsVo) throws Exception {
		// TODO Auto-generated method stub
		
		accountsMapper.insertAccountsProc(accountsVo);
		
	}
	
	@Override
	public List<AccountsVo> selectAccountsList(
			AccountsSearchVo searchVo
			) throws Exception {
		
		List<AccountsVo> accountsListResult = accountsMapper.selectAccountsList(searchVo);
		
		return accountsListResult;
	}

	@Override
	public AccountsVo selectAccountsInfo(HashMap<String, Object> params) throws Exception {

		return accountsMapper.selectAccountsInfo(params);
	}

	@Override
	public void updateAccountsInfo(AccountsVo accountsVo) throws Exception {
		
		accountsMapper.updateAccountsInfo(accountsVo);
		
	}

	@Override
	public AccountsVo loginInfo(AccountsVo loginVo) throws Exception {
		
		AccountsVo loginResult = accountsMapper.loginInfo(loginVo);
		
		return loginResult;
	}

	@Override
	public boolean selectIdCheck(HashMap<String, Object> params) throws Exception {
		
		int idCount = accountsMapper.selectIdCheck(params);
		
		if(idCount == 0) {
			return true;
		}else {
			throw new Exception("이미 등록된 아이디 입니다.");
		}
		
		
	}

	@Override
	public void deleteAccountsInfo(HashMap<String, Object> params) throws Exception {
		
		accountsMapper.deleteAccountsInfo(params);
	}

	@Override
	public int selectAccountsListCnt(AccountsSearchVo searchVo) throws Exception {
		
		return accountsMapper.selectAccountsListCnt(searchVo);
	}
	
}
