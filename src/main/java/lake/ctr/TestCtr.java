//package lake.ctr;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.github.pagehelper.util.StringUtil;
//import com.node.NPageInfo;
//import com.util.JsonUtil;
//
//import lake.entity.NAccount;
//import lake.service.IAccountService;
//
//@Controller
////@RequestMapping("test")
//public class TestCtr {
//	
//	@Autowired
//	private IAccountService iAccountService;
//	
//	@RequestMapping("testt.do")
//	@ResponseBody
//	public String selectlist(HttpServletRequest request,Model model){
//		String spageNum = request.getParameter("pageNum");
//		String spageSize = request.getParameter("pageSize");
//		int pageNum = Integer.valueOf(spageNum);
//		int pageSize = Integer.valueOf(spageSize);
//		
//		NPageInfo<NAccount> info = iAccountService.selectList(pageNum,pageSize);
//		return JsonUtil.toSucc(info);
//	}
//	
//	//@RequestMapping(value = "test.do",produces="text/html;charset=UTF-8;")
//	@RequestMapping("test.do")
//	@ResponseBody
//	public String test(HttpServletRequest request,Model model){
//		int pageNum = 1, pageSize=10;
//		
//		String spageNum = request.getParameter("pageNum");
//		
//		if (StringUtil.isNotEmpty(spageNum)) {
//			Integer _pageNum = Integer.valueOf(spageNum);
//			if (_pageNum!=null && _pageNum>0) {
//				pageNum = _pageNum;
//			}
//		}
//		String spageSize = request.getParameter("pageSize");
//		if (StringUtil.isNotEmpty(spageSize)) {
//			Integer _pageSize = Integer.valueOf(spageSize);
//			if (_pageSize!=null && _pageSize>0) {
//				pageSize = _pageSize;
//			}
//		}
//		
//		String username = request.getParameter("username");
//		String identifyid = request.getParameter("identifyid");
//		
//
//		NPageInfo<NAccount> info = iAccountService.selectList(pageNum,pageSize,username,identifyid);
//		return JsonUtil.toSucc(info);
////		List<NAccount> rows = info.getListT();
////		String strlist = JsonUtil.toString(rows);
////		String json_data = "{\"pageCount\":"+info.getPages()+",\"CurrentPage\":"+pageNum+",\"list\":" + strlist + "}";
////		return json_data;
//	}
//	
//	@RequestMapping("selectByFy.do")
//	@ResponseBody
//	public Map<String,Object> selectByFy(int pageSize,int pageNumber,String name,Integer age){
//
//
//		
//		NPageInfo<NAccount> info = iAccountService.selectList(pageNumber,pageSize);
//		
//		List<NAccount> rows = info.getListT();
//		
//		Map<String,Object> result = new HashMap<String,Object>();
//		result.put("total",info.getTotal());
//        result.put("rows",rows);
//        
//        return result;
//	}
//
//}
