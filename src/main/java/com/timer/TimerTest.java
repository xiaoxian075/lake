package com.timer;

import org.springframework.beans.factory.annotation.Autowired;

import com.node.NPageInfo;

import lake.entity.NAccount;
import lake.service.IAccountService;

public class TimerTest {
	@Autowired
	private IAccountService iAccount;
	
//	@Autowired
//	private IAreaService iArea;

	
	public void run() {
		NPageInfo<NAccount> info = iAccount.selectList(1,5,null,null);
		//NPageInfo<NArea> info = iArea.selectList(3,5);
		System.out.println(info);
	}
}
