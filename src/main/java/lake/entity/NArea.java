package lake.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class NArea implements Serializable {
	private static final long serialVersionUID = -869362610217074102L;
	
	private BigInteger id;
	private String area_name;
	private String en_name;
	private String word_index;
	private String parent_id;
	private int sort_no;
	private int area_level;
	private String is_city;
	private String region;
	private String level_area;
	private String state;
	private String post_code;
	private String xz_code;
	public NArea() {
		super();
	}
	public NArea(BigInteger id, String area_name, String en_name, String word_index, String parent_id, int sort_no,
			int area_level, String is_city, String region, String level_area, String state, String post_code,
			String xz_code) {
		super();
		this.id = id;
		this.area_name = area_name;
		this.en_name = en_name;
		this.word_index = word_index;
		this.parent_id = parent_id;
		this.sort_no = sort_no;
		this.area_level = area_level;
		this.is_city = is_city;
		this.region = region;
		this.level_area = level_area;
		this.state = state;
		this.post_code = post_code;
		this.xz_code = xz_code;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	public String getWord_index() {
		return word_index;
	}
	public void setWord_index(String word_index) {
		this.word_index = word_index;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public int getSort_no() {
		return sort_no;
	}
	public void setSort_no(int sort_no) {
		this.sort_no = sort_no;
	}
	public int getArea_level() {
		return area_level;
	}
	public void setArea_level(int area_level) {
		this.area_level = area_level;
	}
	public String getIs_city() {
		return is_city;
	}
	public void setIs_city(String is_city) {
		this.is_city = is_city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLevel_area() {
		return level_area;
	}
	public void setLevel_area(String level_area) {
		this.level_area = level_area;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getXz_code() {
		return xz_code;
	}
	public void setXz_code(String xz_code) {
		this.xz_code = xz_code;
	}
}
