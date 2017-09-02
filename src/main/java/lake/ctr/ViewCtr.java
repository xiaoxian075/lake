package lake.ctr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lake.ConstDefine;

@Controller
@RequestMapping("admin")
public class ViewCtr {
	
	@RequestMapping("login")
	public String login() {
		return "admin/login";
	}
	
//	@RequestMapping("logout")
//	public String logout(HttpServletRequest request) {
//		request.getSession().setAttribute(ConstDefine.ADMIN_ID, null);
//		return "admin/login";
//	}
	
	@RequestMapping("error")
	public String error() {
		return "admin/error";
	}
	
	@RequestMapping("main")
	public String main() {
		return "admin/main";
	}
	
	@RequestMapping("top")
	public String top() {
		return "admin/top";
	}
	
	@RequestMapping("left")
	public String left() {
		return "admin/left";
	}
	
	@RequestMapping("table")
	public String table() {
		return "admin/src/system/table";
	}
	
	@RequestMapping("area")
	public String area() {
		return "admin/src/system/area";
	}
}