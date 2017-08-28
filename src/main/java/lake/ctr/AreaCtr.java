package lake.ctr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.NPageInfo;
import com.util.JsonUtil;

import lake.entity.NArea;
import lake.service.IAreaService;

@Controller
@RequestMapping("area")
public class AreaCtr {

	@Autowired
	private IAreaService iAreaService;

	@RequestMapping("selectlist.do")
	@ResponseBody
	public String selectlist(HttpServletRequest request,Model model){
		String spageNum = request.getParameter("pageNum");
		String spageSize = request.getParameter("pageSize");
		int pageNum = Integer.valueOf(spageNum);
		int pageSize = Integer.valueOf(spageSize);
		
		NPageInfo<NArea> info = iAreaService.selectList(pageNum,pageSize);
		return JsonUtil.toSucc(info);
		//return JsonUtil.toString(new ReqMsg(0,"succ",info));
	}
}
