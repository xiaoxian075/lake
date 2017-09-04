package lake.ctr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.NNetBase;
import com.node.NRespone;
import com.node.NReturn;

import lake.com.ConstDefine;
import lake.com.NAdminSession;
import lake.ctr.node.NNetLogin;
import lake.entity.NAdmin;
import lake.service.IMainService;


@Controller
@RequestMapping("main")
public class MainCtr {
	
	@Autowired
	private IMainService iMainService;
	
	@RequestMapping(value="login.do",method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request) {
		
		NNetBase<NNetLogin> net = NNetBase.createNew(request,NNetLogin.class);
		
		NNetLogin netLogin = net.getT();
		if (netLogin.getLoginUser()==null || netLogin.getLoginPwd()==null) {
			return NRespone.toStr(ConstDefine.PARAM);
		}
		
		NReturn<NAdmin> nReturn = iMainService.login(netLogin);
		if (nReturn.isErr())
			return NRespone.toStr(nReturn);
		NAdmin nAdmin = nReturn.getT();
		
		
		NAdminSession session = new NAdminSession();
		session.setnAdmin(nAdmin);
		request.getSession().setAttribute(ConstDefine.ADMIN_ID, session);
		
		return NRespone.toStr(nReturn);
	}
	
	@RequestMapping(value="logout.do",method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute(ConstDefine.ADMIN_ID, null);
		return NRespone.toStr(ConstDefine.LOGOUT);
	}
	
	@RequestMapping(value="getAdmin.do",method = RequestMethod.POST)
	@ResponseBody
	public String getAdmin(HttpServletRequest request) {
		NAdminSession admin = (NAdminSession)request.getSession().getAttribute(ConstDefine.ADMIN_ID);
		return NRespone.toStr(admin.getUserName());
	}
	
}
