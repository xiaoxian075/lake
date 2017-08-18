package lake.ctr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.NPageInfo;
import com.node.ReqMsg;
import com.util.JsonUtil;

import lake.entity.NAccount;
import lake.service.IAccountService;

@Controller
@RequestMapping("account")
public class AccountCtr {
	
	@Autowired
	private IAccountService iAccount;

	@RequestMapping("selectlist")
	@ResponseBody
	public String selectlist(HttpServletRequest request,Model model){
		NPageInfo<NAccount> info = iAccount.selectListByPage(1,10);
		return JsonUtil.toString(new ReqMsg(0,"succ",info));
	}
}
