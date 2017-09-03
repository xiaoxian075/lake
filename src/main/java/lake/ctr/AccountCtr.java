package lake.ctr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.ConstDefine;
import com.node.NNetBase;
import com.node.NPageInfo;
import com.node.NRespone;

import lake.ctr.node.NNetAccountSelectlist;
import lake.entity.NAccount;
import lake.service.IAccountService;

@Controller
@RequestMapping("account")
public class AccountCtr {
	
	@Autowired
	private IAccountService iAccountService;

	@RequestMapping("selectlist.do")
	@ResponseBody
	public String selectlist(HttpServletRequest request,Model model){
		NNetBase<NNetAccountSelectlist> net = NNetBase.createNew(request,NNetAccountSelectlist.class);
		if (net.getCode()!=0) {
			return NRespone.toStr(ConstDefine.PARAM);
		}
		NPageInfo<NAccount> info = iAccountService.selectList(net.getT());
		return NRespone.toStr(info);
	}
}
