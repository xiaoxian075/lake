package lake.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;

import lake.ctr.node.NNetArea;
import lake.db.IAreaDB;
import lake.entity.NArea;

@Service
public class CAreaService implements IAreaService{

	@Autowired
	private IAreaDB iAreaDB;
	
	@Override
	public NPageInfo<NArea> selectList(NNetArea netParam) {
		Map<String,Object> _params = new HashMap<String,Object>();
		BigInteger areaId = netParam.getId();
		/*String areaId = netParam.getAreaId();*/
		String areaName = netParam.getAreaName();
		if (areaId!=null && areaId.compareTo(BigInteger.ZERO)>0)
			_params.put("id", areaId);
		if (areaName!=null) {
			_params.put("area_name", areaName);
		}
		return NPageInfo.selectList(netParam.getPageNum(),netParam.getPageSize(),iAreaDB, _params);
	}

}
