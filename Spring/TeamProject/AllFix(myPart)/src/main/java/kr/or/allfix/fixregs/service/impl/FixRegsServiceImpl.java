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
		
		int cnt = fixRegsMapper.insertFixRegInfoProc(fixRegsVo);
		
		if(cnt == 0) {
			throw new Exception("입력을 실패했습니다");
		}
	}
	
	@Override
	public void updateFixRegInfoProc(FixRegsVo fixRegsVo) throws Exception {

		int cnt = fixRegsMapper.updateFixRegInfoProc(fixRegsVo);
		
		if(cnt == 0) {
			throw new Exception("수정을 실패했습니다");
		}
	}
	
	@Override
	public void updateFixRegCancelProc(HashMap<String, Object> params) throws Exception {

		int cnt = fixRegsMapper.updateFixRegCancelProc(params);
		
		if(cnt == 0) {
			throw new Exception("수정을 실패했습니다");
		}
	}
	
	@Override
	public void deleteFixRegInfoProc(HashMap<String, Object> params) throws Exception {

		int cnt = fixRegsMapper.deleteFixRegInfoProc(params);
		
		if(cnt == 0) {
			throw new Exception("삭제를 실패했습니다");
		}
	}
}
