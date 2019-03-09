package kr.or.wjdrmstn.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.wjdrmstn.account.service.AccountService;
import kr.or.wjdrmstn.account.service.AccountVo;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountMapper accountMapper;
	
	public AccountVo selectAccountInfo(AccountVo loginVoParam) throws Exception {
		
		return accountMapper.selectAccountInfo(loginVoParam);
	}
}
