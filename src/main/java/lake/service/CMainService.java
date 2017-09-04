package lake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NReturn;

import lake.com.ConstDefine;
import lake.ctr.node.NNetLogin;
import lake.db.IAdminDB;
import lake.entity.NAdmin;

@Service
public class CMainService implements IMainService{
	
	@Autowired
	private IAdminDB iAdminDB;
	
	@Override
	public NReturn<NAdmin> login(NNetLogin netLogin) {
		String loginUser = netLogin.getLoginUser();
		String loginPwd = netLogin.getLoginPwd();
		
		NAdmin admin = iAdminDB.selectByLoginName(loginUser);
		
		if (admin==null) {
			return NReturn.createNew(ConstDefine.LOGIN_NOT_EXIST, ConstDefine.getDesc(ConstDefine.LOGIN_NOT_EXIST));
		}
		if (loginPwd==null || !loginPwd.equals(admin.getPassword())) {
			return NReturn.createNew(ConstDefine.PASSWORD, ConstDefine.getDesc(ConstDefine.PASSWORD));
		}
		return NReturn.createNew(admin);
	}

}
