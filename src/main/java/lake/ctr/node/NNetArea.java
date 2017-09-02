package lake.ctr.node;

import java.io.Serializable;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class NNetArea extends NNetPage implements Serializable{
	private static final long serialVersionUID = 515454652645237650L;
	
	private String areaId;
	private String areaName;
	public NNetArea() {
		super();
	}
	public NNetArea(Integer pageNum, Integer pageSize, String areaId, String areaName) {
		super(pageNum, pageSize);
		this.areaId = areaId;
		this.areaName = areaName;
	}
	public String getAreaId() {
		if (StringUtils.isBlank(areaId))
			return null;
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		if (StringUtils.isBlank(areaName) || areaName.length()>128)
			return null;
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public BigInteger getId() {
		if (StringUtils.isBlank(areaId))
			return BigInteger.ZERO;
		BigInteger bi = new BigInteger(areaId);
		return bi;
	}
}
