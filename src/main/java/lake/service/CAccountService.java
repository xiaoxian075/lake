package lake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;

import lake.db.IAccountDB;
import lake.entity.NAccount;
import lake.service.IAccountService;

@Service
public class CAccountService implements IAccountService{

	@Autowired
	private IAccountDB iAccountDB;

	@Override
	public NPageInfo<NAccount> selectList(int pageNum, int pageSize) {
		return NPageInfo.selectList(pageNum,pageSize,iAccountDB, null);
	}
	

}
