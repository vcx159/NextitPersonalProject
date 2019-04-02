package kr.or.allfix.attach.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.allfix.attach.service.AttachVo;

@Mapper
public interface AttachMapper {

	public List<AttachVo> selectAttachList(
			String refSeqNo
			) throws Exception;
	
	public void insertAttachs(
			AttachVo attachVo
			) throws Exception;
	
	public AttachVo selectAttachInfo(
			HashMap<String, Object> params
			) throws Exception;
	
	public void deleteAttachList(HashMap<String, Object> params) throws Exception;
}
