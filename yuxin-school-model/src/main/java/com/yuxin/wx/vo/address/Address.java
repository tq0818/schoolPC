package com.yuxin.wx.vo.address;

public class Address {
	public String ip;
	public String country;		//国家
	public String area;			//大区
	public String region;		//省（自治区或直辖市）
	public String city;			//城市
	public String county;		//区县
	public String isp;			//运营商
	
	
	public String country_id;		//国家id
	public String area_id;			//大区id
	public String region_id;		//省（自治区或直辖市）id
	public String city_id;			//城市id
	public String county_id;		//区县id
	public String isp_id;			//运营商id
	
	public Address() {
		super();
	}

	public Address(String ip, String country, String area, String region, String city, String county, String isp,
			String country_id, String area_id, String region_id, String city_id, String county_id, String isp_id) {
		super();
		this.ip = ip;
		this.country = country;
		this.area = area;
		this.region = region;
		this.city = city;
		this.county = county;
		this.isp = isp;
		this.country_id = country_id;
		this.area_id = area_id;
		this.region_id = region_id;
		this.city_id = city_id;
		this.county_id = county_id;
		this.isp_id = isp_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCounty_id() {
		return county_id;
	}

	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}

	public String getIsp_id() {
		return isp_id;
	}

	public void setIsp_id(String isp_id) {
		this.isp_id = isp_id;
	}

	@Override
	public String toString() {
		return "Address [ip=" + ip + ", country=" + country + ", area=" + area + ", region=" + region + ", city=" + city
				+ ", county=" + county + ", isp=" + isp + ", country_id=" + country_id + ", area_id=" + area_id
				+ ", region_id=" + region_id + ", city_id=" + city_id + ", county_id=" + county_id + ", isp_id="
				+ isp_id + "]";
	}
}
