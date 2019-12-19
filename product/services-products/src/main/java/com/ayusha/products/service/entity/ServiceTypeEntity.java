package com.ayusha.products.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
@Entity
@Table(name="servicetype")
public class ServiceTypeEntity {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE) 
	
	/**id**/
	@Column(name="id")
    private long id; 
	

	/**type  **/
	@Column(name="type")
	private String type="";
	
	/**description  **/
	@Column(name="description")
	private String description="";
	
	/**serviceTypeId  **/
	@Column(name="serviceTypeId")
	private String serviceTypeId="";
	
	/**
	 * default constructor
	 */
	public ServiceTypeEntity() {
		
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the serviceTypeId
	 */
	public String getServiceTypeId() {
		return serviceTypeId;
	}

	/**
	 * @param serviceTypeId the serviceTypeId to set
	 */
	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param name the name to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
