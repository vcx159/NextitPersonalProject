package kr.or.allfix.attach.web;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.allfix.attach.service.AttachService;
import kr.or.allfix.attach.service.AttachVo;

@Controller
@RequestMapping(value="/attach")
public class DownloadController {

private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttachService attachService;
	
	@Resource(name="propertyService")
	private Properties propertiesService;
	
	//단순 다운로드및 출력만 셋팅할 URL이라 jsp 페이지를 안만들었기 때문에 반환형이 void이다
	@RequestMapping(value="/download.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, 
								//@RequestParam("seqNo") String seqNo,
								//@RequestParam("serviceType") String serviceType,
								@RequestParam HashMap<String, Object> params) throws Exception {
		
		
		String seqNo = (String) params.get("seqNo");
		String serviceType = (String) params.get("serviceType");
		AttachVo attachResult = attachService.selectAttachInfo(seqNo, serviceType);
		
		log.debug("attachResult: {}", attachResult);
		
		if(attachResult != null) {
			StringBuilder filePath = new StringBuilder();
			filePath.append(propertiesService.getProperty("server.root.dir"));
			filePath.append(attachResult.getSavePath());
			filePath.append(attachResult.getSaveName());
			
			File file = new File(filePath.toString());
			
			log.debug("filePath: {}", filePath);
			
			//파일 다운로드 최신 표준
			byte fileByte[] = FileUtils.readFileToByteArray(file);
			
			log.debug("file Name: {}", attachResult.getFileName());
			
			response.setContentType("application/octet=stream");
			response.setContentLength(fileByte.length);
			response.setHeader("Content-Disposition", "attachment; fileName="+URLEncoder.encode(attachResult.getFileName(), "UTF-8")+";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		}
	}

}
