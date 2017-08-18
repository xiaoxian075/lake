package lake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;

import lake.db.IAccountDB;
import lake.entity.NAccount;
import lake.service.IAccountService;

@Service("iAccount")
public class CAccountService implements IAccountService{

	@Autowired
	private IAccountDB iAccount;
	
	
	public NPageInfo<NAccount> selectListByPage(int pageNum, int pageSize) {
		return iAccount.selectListByPage(pageNum,pageSize);
	}

}
