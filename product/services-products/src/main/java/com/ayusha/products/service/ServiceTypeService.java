package com.ayusha.products.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.products.data.models.ModelDataModel;
import com.ayusha.products.data.models.ServiceTypeDataModel;
import com.ayusha.products.service.entity.ServiceTypeEntity;
import com.ayusha.products.service.repository.IServiceTypeRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;
/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Product Service Methods
 */
@Service
public class ServiceTypeService implements IServiceTypeService{
	
	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(ServiceTypeService.class);
	
	/** iServiceTypeRepository **/
	@Autowired
	private IServiceTypeRepository iServiceTypeRepository;
	/**
	 * default constructor
	 */
	public ServiceTypeService() {
		
	}

	/**
	 * getServiceType
	 */
	public ServiceTypeDataModel getServiceType(String id) throws DataPersistenceOperationException,InvalidServiceRequestException{
		ServiceTypeDataModel serviceTypeDataModel = new ServiceTypeDataModel();
		Optional<ServiceTypeEntity> modelEntity=  iServiceTypeRepository.findById(Integer.parseInt(id));
		
		serviceTypeDataModel.setId(Integer.parseInt(""+modelEntity.get().getId()));
		serviceTypeDataModel.setDescription(modelEntity.get().getDescription());
		serviceTypeDataModel.setType(modelEntity.get().getType());
		serviceTypeDataModel.setServiceTypeId(modelEntity.get().getServiceTypeId());
		return serviceTypeDataModel;
	}
	/**
	 * updateServiceType
	 */
	public  ServiceTypeDataModel updateServiceType(ServiceTypeDataModel modelDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException{
		ServiceTypeEntity modelEntity = new ServiceTypeEntity();
		
		modelEntity.setId(modelDataModel.getId());
		modelEntity.setDescription(modelDataModel.getDescription());
		modelEntity.setType(modelDataModel.getType());
		modelEntity.setServiceTypeId(modelDataModel.getServiceTypeId());
		iServiceTypeRepository.save(modelEntity);
		return modelDataModel;
	}
	/**
	 * add
	 */
	public ServiceTypeDataModel add(ServiceTypeDataModel serviceType) throws DataPersistenceOperationException,InvalidServiceRequestException{
		
		ServiceTypeEntity modelEntity = new ServiceTypeEntity();
		
		modelEntity.setId(serviceType.getId());
		modelEntity.setType(serviceType.getType());
		modelEntity.setServiceTypeId(serviceType.getServiceTypeId());
		iServiceTypeRepository.save(modelEntity);
		return serviceType;
	}
}
