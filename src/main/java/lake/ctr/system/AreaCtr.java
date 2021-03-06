package lake.ctr.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.NNetBase;
import com.node.NPageInfo;
import com.node.NRespone;

import lake.com.ConstDefine;
import lake.ctr.node.NNetArea;
import lake.entity.NArea;
import lake.service.IAreaService;

@Controller
@RequestMapping("system/area")
public class AreaCtr {

	@Autowired
	private IAreaService iAreaService;

	@RequestMapping("selectlist.do")
	@ResponseBody
	public String selectlist(HttpServletRequest request,Model model){
		NNetBase<NNetArea> net = NNetBase.createNew(request,NNetArea.class);
		if (net.getCode()!=0) {
			return NRespone.toStr(ConstDefine.PARAM);
		}
		
		NPageInfo<NArea> info = iAreaService.selectList(net.getT());
		return NRespone.toStr(info);
	}
}
