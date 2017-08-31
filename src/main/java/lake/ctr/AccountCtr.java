package lake.ctr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.node.NPageInfo;
import com.util.JsonUtil;

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
		String loginUser = (String) request.getSession().getAttribute("user");
		System.out.println(loginUser);
		
		int pageNum = 1, pageSize=10;
		
		String spageNum = request.getParameter("pageNum");
		
		if (StringUtil.isNotEmpty(spageNum)) {
			Integer _pageNum = Integer.valueOf(spageNum);
			if (_pageNum!=null && _pageNum>0) {
				pageNum = _pageNum;
			}
		}
		String spageSize = request.getParameter("pageSize");
		if (StringUtil.isNotEmpty(spageSize)) {
			Integer _pageSize = Integer.valueOf(spageSize);
			if (_pageSize!=null && _pageSize>0) {
				pageSize = _pageSize;
			}
		}
		
		String userName = request.getParameter("userName");
		String identifyID = request.getParameter("identifyID");
		if (StringUtil.isEmpty(userName)) {
			userName = null;
		}
		if (StringUtil.isEmpty(identifyID)) {
			identifyID = null;
		}
		

		NPageInfo<NAccount> info = iAccountService.selectList(pageNum,pageSize,userName,identifyID);
		return JsonUtil.toSucc(info);
	}
}
