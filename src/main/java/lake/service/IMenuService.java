package lake.service;

import com.node.NPageInfo;

import lake.ctr.node.NNetMenu;
import lake.entity.NMenu;

public interface IMenuService {

	NPageInfo<NMenu> selectList(NNetMenu netParam);

}
