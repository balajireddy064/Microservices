package com.ayusha.products.service;


import com.ayusha.products.data.models.SymptomDataModel;
import com.ayusha.products.data.models.SymptomsDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Product Service Methods
 */
public interface ISymptomService {
	
	/**getSymptom  **/
	public SymptomDataModel getSymptom(String id) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/**updateSymptom  **/
	public SymptomDataModel updateSymptom(SymptomDataModel symptomDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/** add **/
	public SymptomDataModel add(SymptomDataModel SymptomDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException;

	/**getProductSymptom  **/
	public SymptomsDataModel getProductSymptom(String productId) throws DataPersistenceOperationException,InvalidServiceRequestException;
}
