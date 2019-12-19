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
 * @author Finch Date:01-Aug-2019 Product and supported service types
 *
 */
@Entity
@Table(name = "brand")
public class MakeEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	/** id **/
	@Column(name = "id")
	private long id;

	/** name **/
	@Column(name = "name")
	private String name = "";

	/** description **/
	@Column(name = "description")
	private String description = "";

	/** make_id **/
	@Column(name = "brand_id")
	private String makeId = "";

	/**
	 * default constructor
	 */
	public MakeEntity() {

	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the make_id
	 */
	public String getMakeId() {
		return makeId;
	}

	/**
	 * @param make_id the make_id to set
	 */
	public void setMakeId(String makeId) {
		this.makeId = makeId;
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
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
