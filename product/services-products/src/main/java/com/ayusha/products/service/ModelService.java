package com.ayusha.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.products.data.models.CategoryDataModel;
import com.ayusha.products.data.models.ModelDataModel;
import com.ayusha.products.data.models.ModelsDataModel;
import com.ayusha.products.service.entity.CategoryEntity;
import com.ayusha.products.service.entity.MakeEntity;
import com.ayusha.products.service.entity.ModelEntity;
import com.ayusha.products.service.repository.IModelRepository;
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
public class ModelService implements IModelService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(ModelService.class);

	/** iModelRepository **/
	@Autowired
	private IModelRepository iModelRepository;

	/**
	 * default constructor
	 */
	public ModelService() {

	}

	/**
	 * getModels
	 */
	public List<ModelDataModel> getModels(String makeId, String categoryId, String subCategoryId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		ModelDataModel modelDataModel = null;
		List<ModelEntity> lstModelEntity = iModelRepository.findModelByMakeIdAndCategoryIdAndSubCategoryId(makeId,
				categoryId, subCategoryId);
		ModelEntity modelEntity = null;
		int size = lstModelEntity.size();

		List<ModelDataModel> lstModelDataModel = new ArrayList();

		ModelsDataModel modelsDataModel = new ModelsDataModel();
		modelsDataModel.setMakeId(makeId);

		for (int index = 0; index < size; index++) {
			modelDataModel = new ModelDataModel();
			modelEntity = lstModelEntity.get(index);

			modelDataModel.setId(Integer.parseInt("" + modelEntity.getId()));
			modelDataModel.setDescription(modelEntity.getDescription());
			modelDataModel.setName(modelEntity.getName());
			modelDataModel.setModelId(modelEntity.getModelId());
			modelDataModel.setMakeId(modelEntity.getMakeId());
			modelDataModel.setCategoryId(modelEntity.getCategoryId());
			modelDataModel.setSubCategoryId(modelEntity.getSubCategoryId());
			lstModelDataModel.add(modelDataModel);
			System.out.println("inner for loop");
		}

		return lstModelDataModel;
	}

	/**
	 * updateModel
	 */
	public ModelDataModel updateModel(ModelDataModel modelDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		ModelEntity modelEntity = new ModelEntity();

		modelEntity.setId(modelDataModel.getId());
		modelEntity.setDescription(modelDataModel.getDescription());
		modelEntity.setName(modelDataModel.getName());
		modelEntity.setModelId(modelDataModel.getModelId());
		modelEntity.setMakeId(modelDataModel.getMakeId());
		iModelRepository.save(modelEntity);

		return modelDataModel;
	}

	/**
	 * add
	 */
	public ModelDataModel add(ModelDataModel modelDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		ModelEntity modelEntity = new ModelEntity();

		modelEntity.setId(modelDataModel.getId());
		modelEntity.setDescription(modelDataModel.getDescription());
		modelEntity.setName(modelDataModel.getName());
		modelEntity.setModelId(modelDataModel.getModelId());
		modelEntity.setMakeId(modelDataModel.getMakeId());
		iModelRepository.save(modelEntity);
		return modelDataModel;
	}

	public ModelDataModel getByName(String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {

		ModelDataModel modelDataModel = new ModelDataModel();
		ModelEntity modelEntity = new ModelEntity();
		if (modelEntity != null) {

			modelEntity = iModelRepository.findByName(name);
			BeanUtils.copyProperties(modelEntity, modelDataModel);
			return modelDataModel;
		} else {
			throw new ResourceNotFoundException("model is not present ");
		}
	}

	public List<ModelDataModel> getAllModel() throws DataPersistenceOperationException, InvalidServiceRequestException {
		List<ModelEntity> lstModeldata = iModelRepository.findAllModels();
		List<ModelDataModel> lstModelDataModel = new ArrayList();
		ModelDataModel modelDataModell = null;
		ModelEntity modelEntity = null;
		int size = lstModeldata.size();

		for (int index = 0; index < size; index++) {
			modelEntity = lstModeldata.get(index);
			modelDataModell = new ModelDataModel();
			modelDataModell.setModelId(modelEntity.getModelId());
			modelDataModell.setName(modelEntity.getName());
			modelDataModell.setMakeId(modelEntity.getMakeId());
			modelDataModell.setCategoryId(modelEntity.getCategoryId());
			modelDataModell.setSubCategoryId(modelEntity.getSubCategoryId());

			lstModelDataModel.add(modelDataModell);
		}

		return lstModelDataModel;
	}
}
