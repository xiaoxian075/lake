package lake.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;

import lake.ctr.node.NNetAccountSelectlist;
import lake.db.IAccountDB;
import lake.entity.NAccount;
import lake.service.IAccountService;

@Service
public class CAccountService implements IAccountService{

	@Autowired
	private IAccountDB iAccountDB;

	@Override
	public NPageInfo<NAccount> selectList(NNetAccountSelectlist netParam) {
		String userName = netParam.getUserName();
		String identityID = netParam.getIdentifyID();
		Map<String,Object> _params = new HashMap<String,Object>();
		if (userName!=null)
			_params.put("userName", userName);
		if (identityID!=null)
			_params.put("identityID", identityID);
		return NPageInfo.selectList(netParam.getPageNum(),netParam.getPageSize(),iAccountDB, _params);
	}
	

}
