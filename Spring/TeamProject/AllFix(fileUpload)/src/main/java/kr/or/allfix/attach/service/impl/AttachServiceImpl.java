package kr.or.allfix.attach.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.allfix.accounts.service.AccountsVo;
import kr.or.allfix.attach.service.AttachService;
import kr.or.allfix.attach.service.AttachVo;

@Service("AttachService")
public class AttachServiceImpl implements AttachService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Resource(name="propertyService")
	private Properties propertiesService;


	@Override
	public List<AttachVo> selectAttachList(String refSeqNo) throws Exception {
		return attachMapper.selectAttachList(refSeqNo);
	}

	@Override
	public AttachVo selectAttachInfo(String seqNo, String serviceType) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		params.put("seqNo", seqNo);
		params.put("serviceType", serviceType);
		
		//mapper의 param 값으로 HashMap 객체를 넘긴다
		return attachMapper.selectAttachInfo(params);

	}

	@Override
	public List<AttachVo> insertAttachList(List<MultipartFile> attachMultipartFile, HashMap<String, Object> params)
			throws Exception {
		log.debug("params: {}", params);
		
		List<AttachVo> attachList = new ArrayList<>();
		
		String refSeqNo = UUID.randomUUID().toString();
		String serviceType = (String) params.get("serviceType");
		String usrIp = (String) params.get("usrIp");
		
		// 첨부파일이 넘어온 List 수만큼 반복
		for(MultipartFile multipartFile :  attachMultipartFile) {
			// 첨부파일이 추가되었을 경우만 업로드
			if(multipartFile.getOriginalFilename() != null && !"".equals(multipartFile.getOriginalFilename())) {
				AttachVo attachVo = new AttachVo();
				attachVo.setRefSeqNo(refSeqNo);
				attachVo.setServiceType(serviceType);
				attachVo.setFileName(multipartFile.getOriginalFilename());
				//시스템 환경에 따른 구분자 출력 리눅스 '/' 윈도우 '\'  File.separator
				attachVo.setSavePath(File.separator+serviceType+File.separator);
				attachVo.setSaveName(UUID.randomUUID().toString());  //파일이 덮어 씌워지는거 방지
				attachVo.setSaveContentType(multipartFile.getContentType());
				attachVo.setSaveSize(multipartFile.getSize());
				attachVo.setUsrIp(usrIp);
				
				//컨트롤러에서 세션값을 params 변수에 추가해줬음
				attachVo.setRegUser((String)params.get("regUser"));
				attachVo.setUpdUser((String)params.get("updUser"));				
				
				//서버에 저장될 경로 지정
				StringBuilder pathFileName = new StringBuilder();
				pathFileName.append(propertiesService.getProperty("server.root.dir"));
				pathFileName.append(attachVo.getSavePath());
				pathFileName.append(attachVo.getSaveName());
				
				File targetFile = new File(pathFileName.toString());
				
				//part 파일로 넘어온 inputStream 객체를 파일로 생성
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), targetFile);
				
				attachMapper.insertAttachs(attachVo);
				attachVo.setSeqNo(attachVo.getSeqNo());
				attachList.add(attachVo);
			}
		}

		return attachList;

	}

	@Override
	public void deleteAttach(String seqNo) throws Exception {
		attachMapper.deleteAttach(seqNo);
		
	}
}
