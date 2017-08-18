package lake.db;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;
import com.util.PageUtil;
import com.util.RedisUtil;

import lake.db.IAccountDB;
import lake.entity.NAccount;
import lake.mapper.IAccountMB;

@Service("iAccountDB")
public class CAccountDB implements IAccountDB {
	
	@Autowired
	private IAccountMB iAccount;

	public NPageInfo<NAccount> selectListByPage(int pageNum, int pageSize) {
		if (pageNum<1 || pageSize<1)
			return new NPageInfo<NAccount>(0,0,pageNum,pageSize,0,null);
		List<NAccount> listAccount = loadDataFromDbToRedis();
		if (listAccount==null) {
			return new NPageInfo<NAccount>(0,0,pageNum,pageSize,0,null);
		}

		return PageUtil.pageInfo(pageNum, pageSize, listAccount);
	}
	
	private List<NAccount> loadDataFromDbToRedis() {
		List<NAccount> info = null;
		System.out.println("start"+new Date().getTime());
		if (RedisUtil.exists("accountList")) {
			info = RedisUtil.getObj("accountList");
		} else {
			info = iAccount.selectList();
			RedisUtil.setObj("accountList",info,10);
		}
		System.out.println(" end "+new Date().getTime());
		return info;
	}
}

