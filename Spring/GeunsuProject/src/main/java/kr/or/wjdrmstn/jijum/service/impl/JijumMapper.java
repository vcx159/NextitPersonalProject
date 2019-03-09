package kr.or.wjdrmstn.jijum.service.impl;

import org.apache.ibatis.annotations.Mapper;

import kr.or.wjdrmstn.jijum.service.JijumVo;

@Mapper
public interface JijumMapper {

	public JijumVo selectJijumInfo(String jijumId) throws Exception;
}
