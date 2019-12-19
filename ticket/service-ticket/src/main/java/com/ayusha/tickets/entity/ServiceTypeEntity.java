package com.ayusha.tickets.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Ticket Service Methods
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
    private int id; 
	
	/**type  **/
	@Column(name="stype")
	private String type="";
	
	/**description  **/
	@Column(name="description")
	private String description="";
	
	/** code **/
	@Column(name="code")
	private String code="";
	
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
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
}
	