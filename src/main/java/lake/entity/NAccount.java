package lake.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class NAccount implements Serializable{
	private static final long serialVersionUID = 2494682630124862474L;
	
	private BigInteger id;
	private String loginName;
	private String password;
	private String userName;
	private String nickName;
	private String identityID;
	private String identityName;
	private Date birth;
	private Timestamp createTime;
	public NAccount() {
		super();
	}
	public NAccount(BigInteger id, String loginName, String password, String userName, String nickName,
			String identityID, String identityName, Date birth, Timestamp createTime) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.password = password;
		this.userName = userName;
		this.nickName = nickName;
		this.identityID = identityID;
		this.identityName = identityName;
		this.birth = birth;
		this.createTime = createTime;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIdentityID() {
		return identityID;
	}
	public void setIdentityID(String identityID) {
		this.identityID = identityID;
	}
	public String getIdentityName() {
		return identityName;
	}
	public void setIdentityName(String identityName) {
		this.identityName = identityName;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "NAccount [id=" + id + ", loginName=" + loginName + ", password=" + password + ", userName=" + userName
				+ ", nickName=" + nickName + ", identityID=" + identityID + ", identityName=" + identityName
				+ ", birth=" + birth + ", createTime=" + createTime + "]";
	}
}

//DROP TABLE IF EXISTS `account`;
//create TABLE `account` (
//	`id` BIGINT NOT NULL auto_increment,
//	`loginName` CHAR(64) NOT NULL COMMENT '登入名',
//	`password` CHAR(128) NOT NULL COMMENT '密码',
//	`userName` CHAR(64) NOT NULL COMMENT '用户名',
//	`nickName` CHAR(64) NOT NULL COMMENT '昵称',
//	`identityID` CHAR(18) NOT NULL COMMENT '身份证号',
//	`identityName` CHAR(64) NOT NULL COMMENT '身份证上的姓名',
//	`birth` DATE NOT NULL COMMENT '出生年月日',
//	`createTime` TIMESTAMP NOT NULL COMMENT '创建日期',
//	PRIMARY KEY (`id`)
//);
