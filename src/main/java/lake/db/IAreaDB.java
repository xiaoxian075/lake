package lake.db;

import com.node.NPageInfo;

import lake.entity.NArea;

public interface IAreaDB {

	NPageInfo<NArea> selectListByPage(int pageNum, int pageSize);

}
