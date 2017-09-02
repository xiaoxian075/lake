package lake.service;

import com.node.NPageInfo;

import lake.ctr.node.NNetAccountSelectlist;
import lake.entity.NAccount;

public interface IAccountService {

	public NPageInfo<NAccount> selectList(NNetAccountSelectlist netParam);

}
