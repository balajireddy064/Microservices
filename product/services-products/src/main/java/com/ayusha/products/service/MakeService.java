package com.ayusha.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.products.data.models.MakeDataModel;
import com.ayusha.products.data.models.MakeDataModels;
import com.ayusha.products.service.entity.MakeEntity;
import com.ayusha.products.service.repository.IMakeRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Product Service Methods
 */

@Service
public class MakeService implements IMakeService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(MakeService.class);

	/** iMakeRepository **/
	@Autowired
	private IMakeRepository iMakeRepository;

	/**
	 * default constructor
	 */
	public MakeService() {

	}

	/**
	 * getMake
	 */
	public MakeDataModel getMake(String id) throws DataPersistenceOperationException, InvalidServiceRequestException {
		MakeDataModel makeDataModel = new MakeDataModel();
		MakeEntity makeEntity = iMakeRepository.findMakeById(id);

		makeDataModel.setId(Integer.parseInt("" + makeEntity.getId()));
		makeDataModel.setDescription(makeEntity.getDescription());
		makeDataModel.setName(makeEntity.getName());
		makeDataModel.setMakeId(makeEntity.getMakeId());
		return makeDataModel;
	}

	/**
	 * getAllMake
	 */
	public List<MakeDataModel> getAllMake() throws DataPersistenceOperationException, InvalidServiceRequestException {
		MakeDataModel makeDataModel = new MakeDataModel();
		List<MakeEntity> lstMakeEntity = iMakeRepository.findAllMakes();
		List<MakeDataModel> lstMakeDataModel = new ArrayList();
		MakeEntity makeEntity = null;
		int size = 0;

		size = lstMakeEntity.size();
		for (int index = 0; index < size; index++) {
			makeDataModel = new MakeDataModel();
			makeEntity = lstMakeEntity.get(index);
			makeDataModel.setId(Integer.parseInt("" + makeEntity.getId()));
			makeDataModel.setDescription(makeEntity.getDescription());
			makeDataModel.setName(makeEntity.getName());
			makeDataModel.setMakeId(makeEntity.getMakeId());
			lstMakeDataModel.add(makeDataModel);
		}
		return lstMakeDataModel;
	}

	/**
	 * updateMake
	 */
	public MakeDataModel updateMake(MakeDataModel makeDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		MakeEntity makeEntity = new MakeEntity();

		makeEntity.setId(makeDataModel.getId());
		makeEntity.setDescription(makeDataModel.getDescription());
		makeEntity.setName(makeDataModel.getName());
		makeEntity.setMakeId(makeDataModel.getMakeId());
		iMakeRepository.save(makeEntity);
		return makeDataModel;
	}

	/**
	 * add
	 */
	public MakeDataModel add(MakeDataModel makeDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		MakeEntity makeEntity = new MakeEntity();

		makeEntity.setId(makeDataModel.getId());
		makeEntity.setDescription(makeDataModel.getDescription());
		makeEntity.setName(makeDataModel.getName());
		makeEntity.setMakeId(makeDataModel.getMakeId());
		iMakeRepository.save(makeEntity);
		return makeDataModel;
	}

	/**
	 * getBy makeName
	 */
	public MakeDataModel getByName(String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		MakeDataModel makeDataModel = new MakeDataModel();
		MakeEntity makeEntity = new MakeEntity();
		makeEntity = iMakeRepository.findByName(name);
		if (makeEntity == null) {
			throw new ResourceNotFoundException("make name is not present");
		}
		BeanUtils.copyProperties(makeEntity, makeDataModel);
		return makeDataModel;
	}
}
