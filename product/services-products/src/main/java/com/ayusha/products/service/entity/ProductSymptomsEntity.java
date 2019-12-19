package com.ayusha.products.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */

@Entity
@Table(name="product_symptoms")
public class ProductSymptomsEntity {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE) 
	/**id**/
	@Column(name="id")
    private long id; 
	

	/** productId **/
	@Column(name="product_id")
	private String productId="";
	
	
	
	/**symptomId  **/
	@Column(name="symptom_id")
	private String symptomId=""; 
	
	/**
	 * default constructor
	 */
	public ProductSymptomsEntity() {
		
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the symptomId
	 */
	public String getSymptomId() {
		return symptomId;
	}

	/**
	 * @param symptomId the symptomId to set
	 */
	public void setSymptomId(String symptomId) {
		this.symptomId = symptomId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
