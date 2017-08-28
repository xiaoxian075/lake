package lake.db;

import org.springframework.stereotype.Repository;

import lake.entity.NAccount;

@Repository
public interface IAccountDB extends IBaseDB<NAccount> {

	//NPageInfo<NAccount> selectListByPage(int pageNum, int pageSize);

}
