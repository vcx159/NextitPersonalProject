package kr.or.wjdrmstn.account.service.impl;

import org.apache.ibatis.annotations.Mapper;

import kr.or.wjdrmstn.account.service.AccountVo;

@Mapper
public interface AccountMapper {

	public AccountVo selectAccountInfo(AccountVo loginVoParam) throws Exception;
}
