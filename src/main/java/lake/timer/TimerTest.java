package lake.timer;

import org.springframework.beans.factory.annotation.Autowired;

import com.node.NPageInfo;

import lake.ctr.node.NNetArea;
import lake.entity.NArea;
import lake.service.IAreaService;

public class TimerTest {
//	@Autowired
//	private IAccountService iAccount;
//	
	@Autowired
	private IAreaService iArea;

	
	public void run() {
		NPageInfo<NArea> info = iArea.selectList(new NNetArea(1,5,null,null));
		System.out.println(info);
	}
}
