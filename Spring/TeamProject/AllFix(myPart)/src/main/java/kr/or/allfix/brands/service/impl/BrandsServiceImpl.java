package kr.or.allfix.brands.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.allfix.brands.service.BrandsService;
import kr.or.allfix.brands.service.BrandsVo;

@Service("BrandsService")
public class BrandsServiceImpl implements BrandsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BrandsMapper brandsMapper;
	
	@Override
	public List<BrandsVo> selectBrandsList() throws Exception {
		
		return brandsMapper.selectBrandsList();
	}
	
}
