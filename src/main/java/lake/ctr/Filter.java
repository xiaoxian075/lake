package lake.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.util.DateUtil;

import lake.com.ConstDefine;
import lake.com.NAdminSession;


public class Filter implements HandlerInterceptor/*extends OncePerRequestFilter*/ {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
	    //request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String _url = request.getRequestURI();
		String path = request.getContextPath();
		String url = _url.replace(path, "");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		String loginPath = basePath+"admin/login";
		
		List<String> arrKey = new ArrayList<String>();
		arrKey.add("/admin/login");
		arrKey.add("/admin/error");
		arrKey.add("/main/login.do");
		arrKey.add("/main/logout.do");
		
		if (arrKey.contains(url))
			return true;
		
		NAdminSession admin = (NAdminSession)request.getSession().getAttribute(ConstDefine.ADMIN_ID);
		if (admin==null) {
			//response.getWriter().write(NRespone.toStr(1, "登出", null));
			response.sendRedirect(loginPath);
			return false;
		}
		
		long curTime = DateUtil.getCurrentTime();
		long timestamp = admin.getTimestamp();
		if (timestamp+100000<curTime) {
			request.getSession().setAttribute(ConstDefine.ADMIN_ID,null);
			//response.getWriter().write(NRespone.toStr(1, "登出", null));
			response.sendRedirect(loginPath);
			return false;
		}
		
		admin.setTimestamp(curTime);
		
		return true;
	}
}    
//    HttpServletRequest request = (HttpServletRequest) servletRequest;
//    HttpServletResponse response = (HttpServletResponse) servletResponse;
//    //设置request字符编码
//    request.setCharacterEncoding("UTF-8");
//    //设置response字符编码
//    response.setContentType("text/html;charset=UTF-8");
//    String path = request.getContextPath();
//    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//    String loginUrl=basePath+"/rest/admin/login";
//
//    String url = request.getRequestURI();
//    String [] arrKey = {"login","doLogin","logout"};
//    if(request.getRequestURI().indexOf("/admin/")!=-1 && checkExistence(url,arrKey) == 1) {
//        String requestType = request.getHeader("X-Requested-With");
//        if (StringUtils.isEmpty(request.getSession().getAttribute("userInfo"))) {
//            response.getWriter().write(
//                    "<script language='javascript'>"
//                            + "window.top.location.href='"
//                            + loginUrl
//                            + "';</script>"
//            );
//            response.getWriter().flush();
//            response.getWriter().close();
//            request.getInputStream().available();
//            return;
//
//        }
//        if (request.getRequestURI().indexOf("index")!=-1){
//            String menuId=request.getParameter("menuId");
//            String menuPid=request.getParameter("menuPid");
//            if(!StringUtils.isEmpty(menuId)&&!StringUtils.isEmpty(menuPid)) {
//                request.getSession().setAttribute("menuId", menuId);
//                request.getSession().setAttribute("menuPid", menuPid);
//            }
//        }
//        /**
//         * 过滤每个连接 , 检查是否有对应权限
//         */
//
//        if(checkLink(request) == 0){
//            AppFunUtil.getReturnOutStr(response,"{\"message\":\"您无权限操作,请与管理员联系!\",\"statusCode\":0,\"success\":false,\"data\":\"\"}","UTF-8");
//            return ;
//        }
//    }
