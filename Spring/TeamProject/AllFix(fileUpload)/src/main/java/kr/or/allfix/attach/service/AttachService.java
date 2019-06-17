package kr.or.allfix.attach.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.codec.multipart.Part;
import org.springframework.web.multipart.MultipartFile;

import kr.or.allfix.accounts.service.AccountsVo;

public interface AttachService {

	
	public List<AttachVo> selectAttachList(String refSeqNo) throws Exception;
	
	/**
	 * 파일 고유 seqNo 값으로 레코드를 가지고 옴
	 * @param seqNo
	 * @return
	 * @throws Exception
	 */
	public AttachVo selectAttachInfo(String seqNo, String serviceType) throws Exception;
	
	public List<AttachVo> insertAttachList(List<MultipartFile> attachMultipartFile, HashMap<String, Object> params) throws Exception;

	public void deleteAttach(String seqNo) throws Exception;
}
