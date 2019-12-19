package com.ayusha.general.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ayusha.general.service.validations.BusinessClientDataValidation;
import com.ayusha.general.services.data.model.BusinessClientDataModel;
import com.ayusha.general.services.data.model.ClientDetailsDataModel;
import com.ayusha.general.services.entity.BusinessClientEntity;
import com.ayusha.general.services.entity.ClientContactEntity;
import com.ayusha.general.services.repository.IBusinessClientRepository;
import com.ayusha.general.services.repository.IClientContactRepositry;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;
import com.mysql.cj.log.Log;

@Service
public class BusinessClientService implements IBusinessClientService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(BusinessClientService.class);

	/** Repository **/
	@Autowired
	private IBusinessClientRepository iBusinessClientRepository;

	@Autowired
	private IClientContactRepositry iClinetContectRepoistry;

	public BusinessClientService() {
		LOG.info("Buisness client Service Constructor");
	}

	private int value = 000;

	/**
	 * to get the next sequence number
	 */
	private String getNextSequenceNumber() {
		value++;
		String seq_num = String.format("%03d", value);
		return seq_num;
	}

	/**
	 * this service is to save client details to db no other service dependency
	 */
	public ResponseEntity<?> save(BusinessClientDataModel businessClientDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception {

		LOG.info("Client creation start::::::");
		// for response
		org.apache.catalina.connector.Response response = new org.apache.catalina.connector.Response();

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyHHmmss-");
		LocalDateTime now = LocalDateTime.now();
		BusinessClientEntity businessClientEntity = null;
		businessClientDataModel.setClientId("ATAS-BC-" + dateFormat.format(now) + getNextSequenceNumber());

		ClientDetailsDataModel clientDetailsDataModel = new ClientDetailsDataModel();
		ClientContactEntity clientContectEntity = null;
		List<ClientDetailsDataModel> lstClientDetails = businessClientDataModel.getClientContactDetails();
		int size = lstClientDetails.size();
		BusinessClientDataValidation businessClientDataValidation = new BusinessClientDataValidation();
		boolean validateDetails = businessClientDataValidation.validateClientDetails(businessClientDataModel);

		// Validation of all mandatory fields
		if (validateDetails == true) {
			businessClientEntity = new BusinessClientEntity();
			businessClientEntity.setClientId(businessClientDataModel.getClientId());
			businessClientEntity.setClientName(businessClientDataModel.getClientName());
			businessClientEntity.setClientAddress(businessClientDataModel.getClientAddress());
			businessClientEntity.setCountry(businessClientDataModel.getCountry());
			businessClientEntity.setState(businessClientDataModel.getState());
			businessClientEntity.setCity(businessClientDataModel.getCity());
			businessClientEntity.setPinCode(businessClientDataModel.getPinCode());
			iBusinessClientRepository.save(businessClientEntity);
			LOG.info("client details saved successfully");
		}

		for (int i = 0; i < size; i++) {
			clientDetailsDataModel = new ClientDetailsDataModel();
			clientDetailsDataModel = lstClientDetails.get(i);
			clientContectEntity = new ClientContactEntity();
			System.out.println("size ::::" + lstClientDetails.get(i));
			boolean validateContacts = businessClientDataValidation.validateContactDetails(clientDetailsDataModel);
			List<ClientDetailsDataModel> clientDetailsDataModel1 = new ArrayList<ClientDetailsDataModel>();

			// Validation of all mandatory fields and checking if the contact details record
			// is not null
			if (validateContacts == true && businessClientDataModel.getClientContactDetails() != null) {
				clientDetailsDataModel1 = businessClientDataModel.getClientContactDetails();
				clientContectEntity.setContactDesignation(clientDetailsDataModel.getContactDesignation());
				clientContectEntity.setContactName(clientDetailsDataModel.getContactName());
				clientContectEntity.setContactEmail(clientDetailsDataModel.getContactEmail());
				clientContectEntity.setContactMobile(clientDetailsDataModel.getContactMobile());
				clientContectEntity.setContactLandline(clientDetailsDataModel.getContactLandline());
				clientContectEntity.setClientId(businessClientDataModel.getClientId());
				iClinetContectRepoistry.save(clientContectEntity);
				LOG.info("client contact details saved successfully");
			}
		}

		// success response
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "Client data saved successfully");
		result.put("status", "200");

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	/**
	 * This service is to get client by passing Client Id on request params No other
	 * servcie dependency
	 */
	@Override
	public BusinessClientDataModel getByClient(String clientId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		List<ClientDetailsDataModel> lsClientDetailsDataModels = new ArrayList<ClientDetailsDataModel>();

		List<BusinessClientEntity> lstBusinessClientEntities = iBusinessClientRepository.findByClientId(clientId);
		int size = 0;
		size = lstBusinessClientEntities.size();

		LOG.info("size of record:::" + size);

		// check if size is zero when invalid client id is passed
//		if (size == 0) {
//		//	throw new ResourceNotFoundException("Client details not available");
//		}

		BusinessClientEntity businessClientEntity = null;
		BusinessClientDataModel businessClientDataModel = null;
		ClientContactEntity clientContectEntity = null;
		ClientDetailsDataModel clientDetailsDataModel = null;

		for (int index = 0; index < size; index++) {

			businessClientEntity = new BusinessClientEntity();

			businessClientEntity = lstBusinessClientEntities.get(index);
			businessClientDataModel = new BusinessClientDataModel();

			businessClientDataModel.setClientId(businessClientEntity.getClientId());
			businessClientDataModel.setClientName(businessClientEntity.getClientName());
			businessClientDataModel.setClientAddress(businessClientEntity.getClientAddress());
			businessClientDataModel.setCountry(businessClientEntity.getCountry());
			businessClientDataModel.setState(businessClientEntity.getState());
			businessClientDataModel.setCity(businessClientEntity.getCity());
			businessClientDataModel.setPinCode(businessClientEntity.getPinCode());
		}

		List<ClientContactEntity> lstClientContectEntities = iClinetContectRepoistry
				.findByClientId(businessClientEntity.getClientId());
		int size1 = 0;
		size1 = lstClientContectEntities.size();

		LOG.info("size of contact details record:::" + size1);

		// check if the contact details not available
//		if (size1 == 0) {
//			throw new ResourceNotFoundException("Client Conatct details not available");
//		}

		for (int index1 = 0; index1 < size1; index1++) {
			System.out.println("inner for loop");
			clientContectEntity = new ClientContactEntity();
			clientDetailsDataModel = new ClientDetailsDataModel();
			clientContectEntity = lstClientContectEntities.get(index1);

			clientDetailsDataModel.setContactDesignation(clientContectEntity.getContactDesignation());
			clientDetailsDataModel.setContactName(clientContectEntity.getContactName());
			clientDetailsDataModel.setContactEmail(clientContectEntity.getContactEmail());
			clientDetailsDataModel.setContactMobile(clientContectEntity.getContactMobile());
			clientDetailsDataModel.setContactLandline(clientContectEntity.getContactLandline());
			clientDetailsDataModel.setClientId(businessClientDataModel.getClientId());
			businessClientDataModel.setClientContactDetails(lsClientDetailsDataModels);
			lsClientDetailsDataModels.add(clientDetailsDataModel);

		}

		LOG.info("Client details retrieved successfully");

		return businessClientDataModel;
	}

	/**
	 * This service is to get all clients from db No other service dependency
	 */
	public Page<BusinessClientDataModel> getAllClients(Pageable pageable) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		// pageable to print set of pages of record
		Pageable paging = PageRequest.of(0, Integer.MAX_VALUE);

		List<BusinessClientEntity> lstBusinessClientEntities = iBusinessClientRepository.findAllClients(pageable);

		List array = new ArrayList<>();

		int size = 0;
		size = lstBusinessClientEntities.size();
		LOG.info("size of record::::" + size);

		for (int index = 0; index < size; index++) {
			List<ClientDetailsDataModel> lsClientDetailsDataModels = new ArrayList<ClientDetailsDataModel>();
			BusinessClientEntity businessClientEntity = null;
			BusinessClientDataModel businessClientDataModel = null;

			ClientContactEntity clientContectEntity = null;
			ClientDetailsDataModel clientDetailsDataModel = null;

			businessClientEntity = new BusinessClientEntity();

			businessClientEntity = lstBusinessClientEntities.get(index);
			businessClientDataModel = new BusinessClientDataModel();

			businessClientDataModel.setClientId(businessClientEntity.getClientId());
			businessClientDataModel.setClientName(businessClientEntity.getClientName());
			businessClientDataModel.setClientAddress(businessClientEntity.getClientAddress());
			businessClientDataModel.setCountry(businessClientEntity.getCountry());
			businessClientDataModel.setState(businessClientEntity.getState());
			businessClientDataModel.setCity(businessClientEntity.getCity());
			businessClientDataModel.setPinCode(businessClientEntity.getPinCode());

			List<ClientContactEntity> lstClientContactEntities = iClinetContectRepoistry
					.findByClientId(businessClientEntity.getClientId());

			int size1 = 0;
			size1 = lstClientContactEntities.size();
			LOG.info("size of contact details:::" + size1);

			businessClientDataModel.setClientContactDetails(lsClientDetailsDataModels);

			for (int index1 = 0; index1 < size1; index1++) {
				System.out.println("inner for loop");
				clientContectEntity = new ClientContactEntity();
				clientDetailsDataModel = new ClientDetailsDataModel();

				clientContectEntity = lstClientContactEntities.get(index1);

				clientDetailsDataModel.setContactDesignation(clientContectEntity.getContactDesignation());
				clientDetailsDataModel.setContactName(clientContectEntity.getContactName());
				clientDetailsDataModel.setContactEmail(clientContectEntity.getContactEmail());
				clientDetailsDataModel.setContactMobile(clientContectEntity.getContactMobile());
				clientDetailsDataModel.setContactLandline(clientContectEntity.getContactLandline());
				clientDetailsDataModel.setClientId(businessClientDataModel.getClientId());

				lsClientDetailsDataModels.add(clientDetailsDataModel);

			}
			// add data to data model
			array.add(businessClientDataModel);
		}

		// display set of records
		Page<BusinessClientDataModel> result = new PageImpl<BusinessClientDataModel>(array);

		return result;

	}

	/**
	 * This service to update the client details no other service dependency
	 */
	@Override
	public ResponseEntity<?> updateClientRequest(String clientId, BusinessClientDataModel businessClientDataModel)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {

		ClientDetailsDataModel clientDetailsDataModel = new ClientDetailsDataModel();
		ClientContactEntity clientContectEntity = null;

		// response entity
		org.apache.catalina.connector.Response response = new org.apache.catalina.connector.Response();

		BusinessClientDataModel business = new BusinessClientDataModel();
		BusinessClientEntity businessClientEntity = iBusinessClientRepository.findById(clientId);

		// Check for invalid client id
		if (businessClientEntity == null) {
			throw new ResourceNotFoundException("Client Id doesn't exist");
		}

		businessClientEntity.setClientName(businessClientDataModel.getClientName());
		businessClientEntity.setClientAddress(businessClientDataModel.getClientAddress());
		businessClientEntity.setCountry(businessClientDataModel.getCountry());
		businessClientEntity.setState(businessClientDataModel.getState());
		businessClientEntity.setCity(businessClientDataModel.getCity());
		businessClientEntity.setPinCode(businessClientDataModel.getPinCode());
		iBusinessClientRepository.save(businessClientEntity);

		LOG.info("client details saved successfully");

		List<ClientContactEntity> contactEntity = iClinetContectRepoistry.findByClientId(clientId);
		ClientContactEntity list;

		// check if the size of contact details record is 0
		if (contactEntity.size() == 0) {
			List<ClientDetailsDataModel> lstClientDetails = businessClientDataModel.getClientContactDetails();
			int size = lstClientDetails.size();
			for (int i = 0; i < size; i++) {
				clientDetailsDataModel = new ClientDetailsDataModel();
				clientDetailsDataModel = lstClientDetails.get(i);
				clientContectEntity = new ClientContactEntity();
				List<ClientDetailsDataModel> clientDetailsDataModel1 = new ArrayList<ClientDetailsDataModel>();

				// Validation of all mandatory fields and checking if the contact details record
				// is not null
				if (businessClientDataModel.getClientContactDetails() != null) {
					clientDetailsDataModel1 = businessClientDataModel.getClientContactDetails();
					clientContectEntity.setContactDesignation(clientDetailsDataModel.getContactDesignation());
					clientContectEntity.setContactName(clientDetailsDataModel.getContactName());
					clientContectEntity.setContactEmail(clientDetailsDataModel.getContactEmail());
					clientContectEntity.setContactMobile(clientDetailsDataModel.getContactMobile());
					clientContectEntity.setContactLandline(clientDetailsDataModel.getContactLandline());
					clientContectEntity.setClientId(businessClientDataModel.getClientId());
					iClinetContectRepoistry.save(clientContectEntity);
					LOG.info("client contact details saved successfully");
				}
			}

			// throw new ResourceNotFoundException("Client contact details not available");

		}

		for (int i = 0; i < contactEntity.size(); i++) {
			list = iClinetContectRepoistry.findIndividualClientId(contactEntity.get(i).getId());
			list.setClientId(clientId);
			list.setContactName(businessClientDataModel.getClientContactDetails().get(i).getContactName());
			list.setContactEmail(businessClientDataModel.getClientContactDetails().get(i).getContactEmail());
			list.setContactLandline(businessClientDataModel.getClientContactDetails().get(i).getContactLandline());
			list.setContactDesignation(
					businessClientDataModel.getClientContactDetails().get(i).getContactDesignation());
			list.setContactMobile(businessClientDataModel.getClientContactDetails().get(i).getContactMobile());
			iClinetContectRepoistry.save(list);

			LOG.info("client contact details saved successfully");
		}
		business.setClientId(clientId);
		business.setClientName(businessClientEntity.getClientName());
		business.setClientAddress(businessClientEntity.getClientAddress());
		business.setCountry(businessClientEntity.getCountry());
		business.setState(businessClientEntity.getCity());
		business.setCity(businessClientEntity.getCity());
		business.setPinCode(businessClientEntity.getPinCode());
		business.setClientContactDetails(businessClientDataModel.getClientContactDetails());

		// response body
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "Client data updated  successfully");
		result.put("status", "200");

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}