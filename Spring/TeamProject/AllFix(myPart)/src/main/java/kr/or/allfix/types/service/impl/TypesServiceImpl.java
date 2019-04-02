package kr.or.allfix.types.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.allfix.types.service.TypesService;
import kr.or.allfix.types.service.TypesVo;

@Service("TypesService")
public class TypesServiceImpl implements TypesService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TypesMapper typesMapper;
	
	@Override
	public List<TypesVo> selectTypesList() throws Exception {
		
		return typesMapper.selectTypesList();
	
	}
}
