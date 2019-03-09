package kr.or.wjdrmstn.attach.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.wjdrmstn.attach.service.AttachService;
import kr.or.wjdrmstn.attach.service.AttachVo;

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
	public AttachVo selectAttachInfo(HashMap<String, Object> params) throws Exception {
		
		return attachMapper.selectAttachInfo(params);
	}
	
	@Override
	public String insertAttachList(List<MultipartFile> attachMultipartFile, String serviceType) throws Exception {
		
		String refSeqNo = UUID.randomUUID().toString();
		
		for(MultipartFile multipartFile : attachMultipartFile) {
			if(multipartFile.getOriginalFilename() != null && !"".equals(multipartFile.getOriginalFilename())) {
				AttachVo attachVo = new AttachVo();
				attachVo.setRefSeqNo(refSeqNo);
				attachVo.setServiceType(serviceType);
				attachVo.setFileName(multipartFile.getOriginalFilename());
				attachVo.setSavePath(File.separator+serviceType+File.separator);
				attachVo.setSaveName(UUID.randomUUID().toString());
				attachVo.setSaveContentType(multipartFile.getContentType());
				attachVo.setSaveSize(multipartFile.getSize());
				
				StringBuilder pathFileName = new StringBuilder();
				pathFileName.append(propertiesService.getProperty("server.root.dir"));
				pathFileName.append(attachVo.getSavePath());
				pathFileName.append(attachVo.getSaveName());
				
				File targetFile = new File(pathFileName.toString());
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), targetFile);
				
				attachMapper.insertAttachInfo(attachVo);
				
			}
		}
		
		return refSeqNo;
	}
	
}
