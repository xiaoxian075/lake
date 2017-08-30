package lake.service;

import java.util.HashMap;
import java.util.Map;

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
	public NPageInfo<NAccount> selectList(int pageNum, int pageSize, String userName, String identityID) {
		Map<String,Object> params = new HashMap<String,Object>();
		if (userName!=null)
			params.put("userName", userName);
		if (identityID!=null)
			params.put("identityID", identityID);
		return NPageInfo.selectList(pageNum,pageSize,iAccountDB, params);
	}
	

}
