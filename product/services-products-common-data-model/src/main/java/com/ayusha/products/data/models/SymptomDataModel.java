package com.ayusha.products.data.models;


/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class SymptomDataModel {
	
	/** id **/
	private int id;
	
	/** symptom **/
	private String symptom="";
	
	/** description **/
	private String description="";
	
	/**symptomId  **/
	private String symptomId="";
	
	/**productId  **/
	private String productId="";
	
	/**
	 * default constructor
	 */
	public SymptomDataModel() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the symptom
	 */
	public String getSymptom() {
		return symptom;
	}

	/**
	 * @param symptom the symptom to set
	 */
	public void setSymptom(String symptom) {
		this.symptom = symptom;
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
