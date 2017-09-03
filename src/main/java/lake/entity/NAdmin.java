package lake.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class NAdmin implements Serializable {
	private static final long serialVersionUID = -3288955997431169448L;
	
	private BigInteger id;
	private String loginName;
	private String password;
	private String userName;
	private Integer state;			//状态 0：无效 1：有效
	private Timestamp createTime;
	private Timestamp updateTime;
	public NAdmin() {
		super();
	}
	public NAdmin(BigInteger id, String loginName, String password, String userName, Integer state,
			Timestamp createTime, Timestamp updateTime) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.password = password;
		this.userName = userName;
		this.state = state;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}


}
