package com.ayusha.general.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ayusha.general.services.data.model.BusinessClientDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

public interface IBusinessClientService {

	public ResponseEntity<?> save(BusinessClientDataModel businessClientDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception;

	public BusinessClientDataModel getByClient(String clientId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	public Page<BusinessClientDataModel> getAllClients(Pageable pageable) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	public ResponseEntity<?> updateClientRequest(String clientId, BusinessClientDataModel businessClientDataModel)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException;
}
