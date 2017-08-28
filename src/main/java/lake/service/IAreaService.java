package lake.service;

import com.node.NPageInfo;

import lake.entity.NArea;

public interface IAreaService {

	NPageInfo<NArea> selectList(int pageNum, int pageSize);

}
