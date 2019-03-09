package kr.or.wjdrmstn.contract.service;

public interface ContractService {
	
	public ContractVo selectContractInfo(int employeeId) throws Exception;
	public void updateContractInfo(ContractVo contractVo) throws Exception;
	public void deleteContractInfo(int employeeId) throws Exception;

}
