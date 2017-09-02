package lake.ctr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.NAdmin;
import com.node.NNetBase;
import com.node.NRespone;

import lake.ConstDefine;
import lake.ctr.node.NNetLogin;


@Controller
@RequestMapping("login")
public class LoginCtr {
	
	
	@RequestMapping("login.do")
	@ResponseBody
	public String login(HttpServletRequest request) {
		
		NNetBase<NNetLogin> net = NNetBase.createNew(request,NNetLogin.class);
		
		NNetLogin netLogin = net.getT();
		String loginUser = netLogin.getLoginUser();
		String loginPwd = netLogin.getLoginPwd();
		if (loginUser==null || loginPwd==null) {
			return NRespone.toStr(1,"参数错误",null);
		}
		if (loginUser==null || !loginUser.equals("admin")) {
			return NRespone.toStr(2,"用户名不存在",null);
		}
		if (loginPwd==null || !loginPwd.equals("123456")) {
			return NRespone.toStr(3,"密码不对",null);
		}
		
		NAdmin admin = new NAdmin();
		admin.setUserName(loginUser);
		request.getSession().setAttribute(ConstDefine.ADMIN_ID, admin);
		
		return NRespone.toStr();
	}
	
	@RequestMapping("logout.do")
	@ResponseBody
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute(ConstDefine.ADMIN_ID, null);
		return NRespone.toStr(1,"登出",null);
	}
}
