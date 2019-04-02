package kr.or.allfix.attach.web;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.allfix.attach.service.AttachService;
import kr.or.allfix.attach.service.AttachVo;

import kr.or.allfix.accounts.service.AccountsVo;

@Controller
@RequestMapping(value = "/attach")
public class AttachController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="propertyService")
	private Properties propertyService; 
	
}


