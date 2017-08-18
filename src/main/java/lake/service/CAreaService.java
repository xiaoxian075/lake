package lake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;

import lake.db.IAreaDB;
import lake.entity.NArea;

@Service("iArea")
public class CAreaService implements IAreaService{

	@Autowired
	private IAreaDB iArea;
	
	@Override
	public NPageInfo<NArea> selectListByPage(int pageNum, int pageSize) {
		return iArea.selectListByPage(pageNum,pageSize);
	}

}
