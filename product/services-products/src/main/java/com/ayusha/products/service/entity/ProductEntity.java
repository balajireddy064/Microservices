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
@Table(name="product")
public class ProductEntity {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE) 
	/**id**/
	@Column(name="id")
    private long id; 
	

	/**productId  **/
	@Column(name="product_id")
	private String productId="";
	
	/**categoryId  **/
	@Column(name="category_id")
	private String categoryId="";
	
	/**subCategoryId  **/
	@Column(name="sub_cat_id")
	private String subCategoryId="";
	
	/** make_id **/
	@Column(name="brand_id")
	private String make_id="";
	
	/**modelId  **/
	@Column(name="model_id")
	private String modelId="";
	
	/**
	 * default constructor
	 */
	public ProductEntity() {
		
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
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the subCategoryId
	 */
	public String getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId the subCategoryId to set
	 */
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	/**
	 * @return the make_id
	 */
	public String getMake_id() {
		return make_id;
	}

	/**
	 * @param make_id the make_id to set
	 */
	public void setMake_id(String make_id) {
		this.make_id = make_id;
	}

	/**
	 * @return the modelId
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * @param modelId the modelId to set
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
