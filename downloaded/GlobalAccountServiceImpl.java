package com.chinatour.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinatour.entity.GlobalAccount;
import com.chinatour.persistence.AccountSubjectMapper;
import com.chinatour.persistence.GlobalAccountMapper;
import com.chinatour.service.GlobalAccountService;

@Service("globalAccountServiceImpl")
public class GlobalAccountServiceImpl  extends BaseServiceImpl<GlobalAccount, String> implements GlobalAccountService {
	@Autowired
	private GlobalAccountMapper globalAccountMapper;
	@Autowired
	public void setGlobalAccountMapper(GlobalAccountMapper globalAccountMapper){
		this.setBaseMapper(globalAccountMapper);
	}
	@Override
	public List<GlobalAccount> find(GlobalAccount globalAccount) {
		return globalAccountMapper.find(globalAccount);
	}

	@Override
	public List<GlobalAccount> queryNoChooseglobalAccount(GlobalAccount globalAccount) {
		return globalAccountMapper.queryNoChooseglobalAccount(globalAccount);
	}
	
}
