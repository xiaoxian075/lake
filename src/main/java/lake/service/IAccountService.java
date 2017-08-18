package lake.service;

import com.node.NPageInfo;

import lake.entity.NAccount;

public interface IAccountService {

	public NPageInfo<NAccount> selectListByPage(int pageNum, int pageSize);

}
