package kr.or.allfix.attach.service;

import java.util.HashMap;
import java.util.List;



import org.springframework.web.multipart.MultipartFile;

import kr.or.allfix.accounts.service.AccountsVo;
import kr.or.allfix.attach.service.AttachVo;


public interface AttachService {

	public List<AttachVo> selectAttachList( 
			String refSeqNo
			) throws Exception;
	
	/**
	 * List<Part> 첨부파일 목록을 서버에 저장후 정상 처리가 완료 되었을 경우..
	 * 데이터 베이스에 저장 .. 
	 * @param attachPart
	 * @return
	 * @throws Exception
	 */
	public List<AttachVo> insertAttachList(
			List<MultipartFile> attachPart,
			AccountsVo accountsVo,
			String serviceType,
			String usrIp
			) throws Exception;
	
	
	public String insertAttachList(
			List<MultipartFile> attachMultipartFile,
			HashMap<String, Object> params
			) throws Exception;
	
	
	
	/**
	 * 파일 고유 seqNo 값으로 레코드를 가지고옴 
	 * @param seqNo
	 * @return
	 * @throws Exception
	 */
	public AttachVo selectAttachInfo(
			HashMap<String, Object> params
			) throws Exception;

	public void deleteAttachList(HashMap<String, Object> params) throws Exception;
}
