package lake.ctr.node;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class NNetMenu extends NNetPage implements Serializable{
	private static final long serialVersionUID = -4005272568003939125L;
	
	private String url;
	private String name;
	public NNetMenu() {
		super();
	}
	public NNetMenu(Integer pageNum, Integer pageSize, String url, String name) {
		super(pageNum, pageSize);
		this.url = url;
		this.name = name;
	}
	public String getUrl() {
		if (StringUtils.isBlank(url) || url.length()>128)
			return null;
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		if (StringUtils.isBlank(name) || name.length()>16)
			return null;
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
