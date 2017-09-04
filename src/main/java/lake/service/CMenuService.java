package lake.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;

import lake.ctr.node.NNetMenu;
import lake.db.IMenuDB;
import lake.entity.NMenu;

@Service
public class CMenuService implements IMenuService{
	
	@Autowired
	private IMenuDB iMenuDB;
	
	@Override
	public NPageInfo<NMenu> selectList(NNetMenu netParam) {
		Map<String,Object> _params = new HashMap<String,Object>();
		String url = netParam.getUrl();
		String name = netParam.getName();
		if (url!=null)
			_params.put("url", url);
		if (name!=null) {
			_params.put("name", name);
		}
		return NPageInfo.selectList(netParam.getPageNum(),netParam.getPageSize(), iMenuDB, _params);
	}

}
