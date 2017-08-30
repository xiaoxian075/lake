package lake.service;

import com.node.NPageInfo;

import lake.entity.NAccount;

public interface IAccountService {

	public NPageInfo<NAccount> selectList(int pageNum, int pageSize, String username, String identifyid);

}
