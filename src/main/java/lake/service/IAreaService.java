package lake.service;

import com.node.NPageInfo;

import lake.ctr.node.NNetArea;
import lake.entity.NArea;

public interface IAreaService {

	NPageInfo<NArea> selectList(NNetArea netParam);

}
