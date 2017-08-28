package lake.ctr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.NPageInfo;
import com.util.JsonUtil;

import lake.entity.NAccount;
import lake.service.IAccountService;

@Controller
//@RequestMapping("test")
public class TestCtr {
	
	@Autowired
	private IAccountService iAccountService;
	
	@RequestMapping("testt.do")
	@ResponseBody
	public String selectlist(HttpServletRequest request,Model model){
		String spageNum = request.getParameter("pageNum");
		String spageSize = request.getParameter("pageSize");
		int pageNum = Integer.valueOf(spageNum);
		int pageSize = Integer.valueOf(spageSize);
		
		NPageInfo<NAccount> info = iAccountService.selectList(pageNum,pageSize);
		return JsonUtil.toSucc(info);
	}
	
	@RequestMapping("test.do")
	@ResponseBody
	public String test(HttpServletRequest request,Model model){
		String spage = request.getParameter("page");
		int page = Integer.valueOf(spage);
		
		int pageIndex = page;//当前页
		int pageSize =5;//设置每页要展示的数据数量(根据项目需求灵活设置)
		//int rowCount = 0;
		
		NPageInfo<NAccount> info = iAccountService.selectList(pageIndex,pageSize);
		
		List<NAccount> listData = info.getListT();
		String strlist = JsonUtil.toString(listData);
		String json_data = "{\"pageCount\":"+info.getTotal()+",\"CurrentPage\":"+pageIndex+",\"list\":" + strlist + "}";
		return json_data;
	}

//		int pageIndex = page;//当前页
//	        int pageSize =5;//设置每页要展示的数据数量(根据项目需求灵活设置)
//	        int rowCount = 0 ;
//	       List<Map> listExamine=userService.queryUserInfo();//获取总数据量
//	        try {
//	                rowCount=listExamine.size();//总条数
//	               //通过计算，得到分页应该需要分几页，其中不满一页的数据按一页计算
//	               if(rowCount%pageSize!=0)
//	               {
//	                   rowCount = rowCount / pageSize + 1;
//	               }
//	               else
//	               {
//	                   rowCount = rowCount / pageSize;
//	               }
//	           } catch (Exception e) {
//	       }
//	         List<Map> showList=userService.queryShowUserInfo(pageIndex,pageSize);//根据pageIndex和pageSize获取需要展示的该页数据
//	       //转成Json格式
//	       String json_data = "{\"pageCount\":"+rowCount+",\"CurrentPage\":"+pageIndex+",\"list\":" + JSONArray.fromObject(showList) + "}";
//	       boolean success=true;
//	       //之下的两行代码为本人项目中封装的返回json数据的方法，各位只需要用自己的方法将json_data字符串返回前台即可
//	       ConvertToJson(success, json_data);
//	       return "jsonData";
//	}
}
