package lake.db;

import lake.entity.NAdmin;

public interface IAdminDB {

	NAdmin selectByLoginName(String loginUser);

}
