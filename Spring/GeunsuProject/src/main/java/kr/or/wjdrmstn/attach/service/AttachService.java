package kr.or.wjdrmstn.attach.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AttachService {

	public List<AttachVo> selectAttachList(String refSeqNo) throws Exception;
	public AttachVo selectAttachInfo(HashMap<String, Object> params) throws Exception;
	public String insertAttachList(List<MultipartFile> attachMultipartFile, String serviceType) throws Exception;
}
