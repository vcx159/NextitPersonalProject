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
@RequestMapping(value = "/attach")
public class DownloadController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AttachService attachService;
	
	@Resource(name="propertyService")
	private Properties propertyService; 
	
	

	@RequestMapping(value = "/download.do")
	public void fileDownload(
			HttpServletRequest request, 
			HttpServletResponse response,
//			@RequestParam("seqNo") String seqNo,
//			@RequestParam("serviceType") String serviceType,
			@RequestParam HashMap<String, Object> params
			) throws Exception {
		
		AttachVo attachResult = attachService.selectAttachInfo(params);
		
		log.debug("attachResult : {}", attachResult);
		
		if(attachResult != null) {
			StringBuilder filePath = new StringBuilder();
			filePath.append(propertyService.getProperty("server.root.dir"));
			filePath.append(attachResult.getSavePath());
			filePath.append(attachResult.getSaveName());
			
			File file = new File(filePath.toString());
			
			log.debug("filePath : {}", filePath);
			
			byte fileByte[] = FileUtils.readFileToByteArray(file);
			
			log.debug("file Name : {} " , attachResult.getFileName());
			
			response.setContentType("application/octet-stream");
			response.setContentLength(fileByte.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\""
					+ URLEncoder.encode(attachResult.getFileName(), "UTF-8") 
					+ "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
	}

}
