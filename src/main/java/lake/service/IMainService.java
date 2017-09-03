package lake.service;

import com.node.NReturn;

import lake.ctr.node.NNetLogin;
import lake.entity.NAdmin;

public interface IMainService {

	NReturn<NAdmin> login(NNetLogin netLogin);

}
