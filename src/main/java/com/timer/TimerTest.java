package com.timer;

import org.springframework.beans.factory.annotation.Autowired;

import com.node.NPageInfo;

import lake.entity.NAccount;
import lake.service.IAccountService;

public class TimerTest {
	@Autowired
	private IAccountService iAccount;

	
	public void run() {
		NPageInfo<NAccount> info = iAccount.selectListByPage(1,10);
		System.out.println(info);
	}
}
