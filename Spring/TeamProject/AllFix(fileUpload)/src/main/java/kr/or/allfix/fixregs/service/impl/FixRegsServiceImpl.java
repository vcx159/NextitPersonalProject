package kr.or.allfix.fixregs.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.allfix.fixregs.service.FixRegsSearchVo;
import kr.or.allfix.fixregs.service.FixRegsService;
import kr.or.allfix.fixregs.service.FixRegsVo;

@Service("FixRegsService")
public class FixRegsServiceImpl implements FixRegsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FixRegsMapper fixRegsMapper;
	
	@Override
	public List<FixRegsVo> selectFixRegsListProc(FixRegsSearchVo searchVo) throws Exception {
		
		return fixRegsMapper.selectFixRegsListProc(searchVo);
	}
	
	@Override
	public int selectFixRegsListCnt(FixRegsSearchVo searchVo) throws Exception {
		return fixRegsMapper.selectFixRegsListCnt(searchVo);
	}
	
	@Override
	public FixRegsVo selectFixRegInfoProc(String seqNo) throws Exception {
		return fixRegsMapper.selectFixRegInfoProc(seqNo);
	}
	
	@Override
	public void insertFixRegInfoProc(FixRegsVo fixRegsVo) throws Exception {
		
		fixRegsMapper.insertFixRegInfoProc(fixRegsVo);
		
	}
	
	@Override
	public void updateFixRegInfoProc(FixRegsVo fixRegsVo) throws Exception {

		fixRegsMapper.updateFixRegInfoProc(fixRegsVo);
	}
	
	@Override
	public void deleteFixRegInfoProc(HashMap<String, Object> params) throws Exception {

		fixRegsMapper.deleteFixRegInfoProc(params);
	}
}
