package lake.db;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NPageInfo;
import com.redis.RedisUtil;

import lake.entity.NArea;
import lake.mapper.IAreaMB;

@Service("iAreaDB")
public class CAreaDB implements IAreaDB {

	@Autowired
	private IAreaMB iArea;

	public NPageInfo<NArea> selectListByPage(int pageNum, int pageSize) {
		if (pageNum<1 || pageSize<1)
			return new NPageInfo<NArea>(0,0,pageNum,pageSize,0,null);
		List<NArea> listArea = loadDataFromDbToRedis();
		if (listArea==null) {
			return new NPageInfo<NArea>(0,0,pageNum,pageSize,0,null);
		}

		return NPageInfo.createNew(pageNum, pageSize, listArea);
	}
	
	private List<NArea> loadDataFromDbToRedis() {
		List<NArea> info = null;
		
		if (RedisUtil.exists("areaList")) {
			long start = new Date().getTime();
			info = RedisUtil.getObj("areaList");
			System.out.println("  get redis:"+(new Date().getTime()-start));
		} else {
			long start = new Date().getTime();
			info = iArea.selectList();
			long end = new Date().getTime();
			System.out.println("get mybatis:"+(end-start));
			RedisUtil.setObj("areaList",info,10);
			System.out.println("  set redis:"+(new Date().getTime()-end));
		}
		
		return info;
	}

}
