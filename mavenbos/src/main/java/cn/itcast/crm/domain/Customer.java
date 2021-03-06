package cn.itcast.crm.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 客户
@Entity
@Table(name="customers",schema="sh1208")
public class Customer {
	@Id
	private Integer id ; 
	private String name ; 
	private String station ;
	private String telephone ;
	private String address ; 
	
	// 客户关联定区
	private String decidedZoneId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDecidedZoneId() {
		return decidedZoneId;
	}

	public void setDecidedZoneId(String decidedZoneId) {
		this.decidedZoneId = decidedZoneId;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", station=" + station + ", telephone=" + telephone + ", address=" + address + ", decidedZoneId=" + decidedZoneId + "]";
	}
	
	
}
