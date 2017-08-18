package lake.db;

import com.node.NPageInfo;

import lake.entity.NAccount;

public interface IAccountDB {

	NPageInfo<NAccount> selectListByPage(int pageNum, int pageSize);

}
