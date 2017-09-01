package lake.ctr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.util.JsonUtil;


@Controller
@RequestMapping("login")
public class LoginCtr {
	
	@RequestMapping("login.do")
	@ResponseBody
	public String login(HttpServletRequest request,Model model){
		String loginUser = request.getParameter("loginUser");
		String loginPwd = request.getParameter("loginPwd");
		if (StringUtil.isEmpty(loginUser)) {
			return JsonUtil.toString(1,"参数错误",null);
		}
		if (StringUtil.isEmpty(loginPwd)) {
			return JsonUtil.toString(1,"参数错误",null);
		}
		
		if (!loginUser.equals("admin")) {
			return JsonUtil.toString(2,"用户名不存在",null);
		}
		if (!loginPwd.equals("123456")) {
			return JsonUtil.toString(3,"密码不对",null);
		}
		
		request.getSession().setAttribute("user", loginUser);
		
		return JsonUtil.toSucc(null);
	}
}
