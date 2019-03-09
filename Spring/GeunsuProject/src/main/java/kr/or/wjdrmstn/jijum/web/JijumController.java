package kr.or.wjdrmstn.jijum.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.wjdrmstn.jijum.service.JijumVo;

@Controller
public class JijumController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value={"/manager/jijumView.do"})
	public String selectJijumInfo() throws Exception {
		
		
		
		return "jijum/jijumView";
	}
}
