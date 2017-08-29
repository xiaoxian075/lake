package lake.ctr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//@RequestMapping(value = "test.do",produces="text/html;charset=UTF-8;")
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
		String json_data = "{\"pageCount\":"+info.getPages()+",\"CurrentPage\":"+pageIndex+",\"list\":" + strlist + "}";
		return json_data;
	}
	
	@RequestMapping("selectByFy.do")
	@ResponseBody
	public Map<String,Object> selectByFy(int pageSize,int pageNumber,String name,Integer age){


		
		NPageInfo<NAccount> info = iAccountService.selectList(pageNumber,pageSize);
		
		List<NAccount> rows = info.getListT();
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("total",info.getTotal());
        result.put("rows",rows);
        
        return result;
	}

}
