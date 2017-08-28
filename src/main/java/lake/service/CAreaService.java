package lake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;

import lake.db.IAreaDB;
import lake.entity.NArea;

@Service
public class CAreaService implements IAreaService{

	@Autowired
	private IAreaDB iAreaDB;
	
	@Override
	public NPageInfo<NArea> selectList(int pageNum, int pageSize) {
		return NPageInfo.selectList(pageNum,pageSize,iAreaDB, null);
	}

}
