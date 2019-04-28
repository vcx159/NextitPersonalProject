package kr.or.allfix.attach.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;


import org.slf4j.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.allfix.attach.service.AttachService;
import kr.or.allfix.attach.service.AttachVo;

import kr.or.allfix.accounts.service.AccountsVo;

@Service("AttachService")
public class AttachServiceImpl implements AttachService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Resource(name="propertyService")
	private Properties propertyService; 
	


	
	@Override
	public String insertAttachList(
			List<MultipartFile> attachMultipartFile, 
			HashMap<String, Object> params
			) throws Exception {
		
		log.debug("params : {}" , params);
		
		
		
		String refSeqNo = UUID.randomUUID().toString();	//for문 밖에다 씀 
		
		String serviceType = (String)params.get("serviceType");
		String usrIp = (String) params.get("usrIp"); 
		
		// 첨부파일이 넘어온 List 수만큼 반복
		for(MultipartFile multipartFile : attachMultipartFile) {
			// 첨부파일이 추가 되었을 경우에만 파일 업로드
			if(multipartFile.getOriginalFilename() != null 
					&& !"".equals(multipartFile.getOriginalFilename())) {
				
				AttachVo attachVo = new AttachVo();
				attachVo.setRefSeqNo(refSeqNo);
				attachVo.setServiceType(serviceType);
				attachVo.setFileName(multipartFile.getOriginalFilename());
				attachVo.setSavePath(File.separator + serviceType + File.separator);
				attachVo.setSaveName(UUID.randomUUID().toString());
				attachVo.setSaveContentType(multipartFile.getContentType());
				attachVo.setSaveSize(multipartFile.getSize());
				attachVo.setUsrIp(usrIp);
				
				// 컨트롤러에서 세션값을 params 변수에 추가 해주었음
				attachVo.setRegUser((String)params.get("regUser"));
				attachVo.setUpdUser((String)params.get("updUser"));
				
				
				log.debug("getSubmittedFileName : {}", multipartFile.getOriginalFilename());
				log.debug("getContentType : {}", multipartFile.getContentType());
				log.debug("getSize : {}", multipartFile.getSize());
				
				//서버에 저장 될 경로 지정 
				StringBuilder pathFileName = new StringBuilder();
				pathFileName.append(propertyService.getProperty("server.root.dir"));
				pathFileName.append(attachVo.getSavePath());
				pathFileName.append(attachVo.getSaveName());
				
				log.debug("path + FileName : {}", pathFileName.toString());
				
				File targetFile = new File(pathFileName.toString());
				
				//part 파일로 넘어온 inputStream 객체를 파일로 생성
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), targetFile);
				
				attachMapper.insertAttachs(attachVo);
			}
		}
		
		return refSeqNo;
	}
	
	@Override
	public List<AttachVo> insertAttachList(
			List<MultipartFile> attachPart,
			AccountsVo accountsVo,
			String serviceType,
			String usrIp
			) throws Exception {
		
		List<AttachVo> attachResult = new ArrayList<>();
		
		String refSeqNo = UUID.randomUUID().toString();	//for문 밖에다 씀 
		
		for(MultipartFile multipartFile : attachPart) {
			
			if(multipartFile.getOriginalFilename() != null && !"".equals(multipartFile.getOriginalFilename())) {
				
				AttachVo attachVo = new AttachVo();
				attachVo.setRefSeqNo(refSeqNo);
				attachVo.setServiceType(serviceType);
				attachVo.setFileName(multipartFile.getOriginalFilename());
				attachVo.setSavePath(File.separator + serviceType + File.separator);
				attachVo.setSaveName(UUID.randomUUID().toString());
				attachVo.setSaveContentType(multipartFile.getContentType());
				attachVo.setSaveSize(multipartFile.getSize());
				attachVo.setUsrIp(usrIp);
				attachVo.setRegUser(accountsVo.getAccId());
				attachVo.setUpdUser(accountsVo.getAccId());
				
				attachResult.add(attachVo);
				
				log.debug("getSubmittedFileName : {}", multipartFile.getOriginalFilename());
				log.debug("getContentType : {}", multipartFile.getContentType());
				log.debug("getSize : {}", multipartFile.getSize());
				
				//서버에 저장 될 경로 지정 
				StringBuilder pathFileName = new StringBuilder();
				pathFileName.append(propertyService.getProperty("server.root.dir"));
				pathFileName.append(attachVo.getSavePath());
				pathFileName.append(attachVo.getSaveName());
				
				log.debug("path + FileName : {}", pathFileName.toString());
				
				File targetFile = new File(pathFileName.toString());
				
				//part 파일로 넘어온 inputStream 객체를 파일로 생성
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), targetFile);
				
				attachMapper.insertAttachs(attachVo);
			}
		}
		
		return attachResult;
	}



	@Override
	public List<AttachVo> selectAttachList(String refSeqNo) throws Exception {
	
		return attachMapper.selectAttachList(refSeqNo);
		
		
	}

	@Override
	public AttachVo selectAttachInfo(HashMap<String, Object> params) throws Exception {

		return attachMapper.selectAttachInfo(params);
	}

	@Override
	public void deleteAttachList(HashMap<String, Object> params) throws Exception {
		
		attachMapper.deleteAttachList(params);
		
	}
	
}
