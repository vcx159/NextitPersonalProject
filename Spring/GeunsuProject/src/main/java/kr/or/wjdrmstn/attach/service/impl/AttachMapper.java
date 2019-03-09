package kr.or.wjdrmstn.attach.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.wjdrmstn.attach.service.AttachVo;

@Mapper
public interface AttachMapper {
	
	public List<AttachVo> selectAttachList(String refSeqNo) throws Exception;
	public AttachVo selectAttachInfo(HashMap<String, Object> params) throws Exception;
	public void insertAttachInfo(AttachVo attachVo) throws Exception;
}
