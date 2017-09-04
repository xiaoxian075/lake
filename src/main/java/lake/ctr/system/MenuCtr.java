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
import lake.ctr.node.NNetMenu;
import lake.entity.NMenu;
import lake.service.IMenuService;

@Controller
@RequestMapping("system/menu")
public class MenuCtr {

	@Autowired
	private IMenuService iMenuService;

	@RequestMapping("selectlist.do")
	@ResponseBody
	public String selectlist(HttpServletRequest request,Model model){
		NNetBase<NNetMenu> net = NNetBase.createNew(request,NNetMenu.class);
		if (net.getCode()!=0) {
			return NRespone.toStr(ConstDefine.PARAM);
		}
		
		NPageInfo<NMenu> info = iMenuService.selectList(net.getT());
		return NRespone.toStr(info);
	}
}
