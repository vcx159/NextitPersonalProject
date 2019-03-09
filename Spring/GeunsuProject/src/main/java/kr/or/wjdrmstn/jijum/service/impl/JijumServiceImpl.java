package kr.or.wjdrmstn.jijum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.wjdrmstn.jijum.service.JijumService;
import kr.or.wjdrmstn.jijum.service.JijumVo;

@Service("JijumService")
public class JijumServiceImpl implements JijumService {

	@Autowired
	private JijumMapper jijumMapper;
	
	@Override
	public JijumVo selectJijumInfo(String jijumId) throws Exception {
		// TODO Auto-generated method stub
		
		return jijumMapper.selectJijumInfo(jijumId);
	}
}
