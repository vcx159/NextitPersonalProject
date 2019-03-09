package kr.or.wjdrmstn.contract.service.impl;

import org.apache.ibatis.annotations.Mapper;

import kr.or.wjdrmstn.contract.service.ContractVo;

@Mapper
public interface ContractMapper {

	public ContractVo selectContractInfo(int seqNo) throws Exception;
	public void insertContractInfo(ContractVo contractVo) throws Exception;
	public void updateContractInfo(ContractVo contractVo) throws Exception;
	public void deleteContractInfo(int employeeId) throws Exception;
	
}
