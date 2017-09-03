package lake.ctr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class ViewCtr {
	
	@RequestMapping("*")
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping("error")
	public String error() {
		return "admin/error";
	}
	
	@RequestMapping("first")
	public String first() {
		return "admin/src/first";
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
	
	@RequestMapping("player")
	public String player() {
		return "admin/src/account/player";
	}
	
	@RequestMapping("area")
	public String area() {
		return "admin/src/system/area";
	}
}
