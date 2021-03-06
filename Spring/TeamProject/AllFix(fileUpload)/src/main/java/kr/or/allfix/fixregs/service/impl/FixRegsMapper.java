package kr.or.allfix.fixregs.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.fixregs.service.FixRegsSearchVo;
import kr.or.allfix.fixregs.service.FixRegsVo;

@Mapper
public interface FixRegsMapper {

	public List<FixRegsVo> selectFixRegsListProc(FixRegsSearchVo searchVo) throws Exception;
	public int selectFixRegsListCnt(FixRegsSearchVo searchVo) throws Exception;
	public FixRegsVo selectFixRegInfoProc(String seqNo) throws Exception;
	public void insertFixRegInfoProc(FixRegsVo fixRegsVo) throws Exception;
	public void updateFixRegInfoProc(FixRegsVo fixRegsVo) throws Exception;
	public void deleteFixRegInfoProc(HashMap<String, Object> params) throws Exception;
}
