package lake.ctr.node;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class NNetLogin implements Serializable {
	private static final long serialVersionUID = 6128549852505522132L;
	
	private String loginUser;
	private String loginPwd;
	
	public NNetLogin() {
		super();
	}
	public NNetLogin(String loginUser, String loginPwd) {
		super();
		this.loginUser = loginUser;
		this.loginPwd = loginPwd;
	}
	public String getLoginUser() {
		if (StringUtils.isBlank(loginUser) || loginUser.length()>8)
			return null;
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getLoginPwd() {
		if (StringUtils.isBlank(loginPwd)|| loginPwd.length()>8)
			return null;
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	@Override
	public String toString() {
		return "NNetLogin [loginUser=" + loginUser + ", loginPwd=" + loginPwd + "]";
	}


}
