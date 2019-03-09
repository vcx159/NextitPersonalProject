package kr.or.wjdrmstn.contract.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.wjdrmstn.contract.service.ContractService;
import kr.or.wjdrmstn.contract.service.ContractVo;

@Service("ContractService")
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractMapper contractMapper;
	
	@Override
	public ContractVo selectContractInfo(int employeeId) throws Exception {
		
		return contractMapper.selectContractInfo(employeeId);
	}
	@Override
	public void updateContractInfo(ContractVo contractVo) throws Exception {
		
		contractMapper.updateContractInfo(contractVo);
		
	}
	
	@Override
	public void deleteContractInfo(int employeeId) throws Exception {

		contractMapper.deleteContractInfo(employeeId);
	}
}
