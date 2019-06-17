package kr.or.allfix.fixregs.service;

import java.util.HashMap;
import java.util.List;

public interface FixRegsService {

	public List<FixRegsVo> selectFixRegsListProc(FixRegsSearchVo searchVo) throws Exception;
	public int selectFixRegsListCnt(FixRegsSearchVo searchVo) throws Exception;
	public FixRegsVo selectFixRegInfoProc(String seqNo) throws Exception;
	public void insertFixRegInfoProc(FixRegsVo fixRegsVo) throws Exception;
	public void updateFixRegInfoProc(FixRegsVo fixRegsVo) throws Exception;
	public void deleteFixRegInfoProc(HashMap<String, Object> params) throws Exception;
}
