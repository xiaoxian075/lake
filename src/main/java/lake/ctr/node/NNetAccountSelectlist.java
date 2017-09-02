package lake.ctr.node;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class NNetAccountSelectlist extends NNetPage implements Serializable{
	private static final long serialVersionUID = 4022277263077848988L;

	private String userName;
	private String identifyID;
	public NNetAccountSelectlist() {
		super();
	}
	public NNetAccountSelectlist(Integer pageNum, Integer pageSize, String userName, String identifyID) {
		super(pageNum, pageSize);
		this.userName = userName;
		this.identifyID = identifyID;
	}
	public String getUserName() {
		if (StringUtils.isBlank(userName) || userName.length()>32)
			return null;
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdentifyID() {
		if (StringUtils.isBlank(identifyID) || identifyID.length()>18)
			return null;
		return identifyID;
	}
	public void setIdentifyID(String identifyID) {
		this.identifyID = identifyID;
	}
}