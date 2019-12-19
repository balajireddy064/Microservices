package com.ayusha.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ayusha.products.data.models.CategoryDataModel;
import com.ayusha.products.data.models.ModelDataModel;
import com.ayusha.products.data.models.SubCategoryDataModel;
import com.ayusha.products.data.models.SubCategorysDataModel;
import com.ayusha.products.service.entity.ModelEntity;
import com.ayusha.products.service.entity.SubCategoryEntity;
import com.ayusha.products.service.repository.ISubCategoryRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Product Service Methods
 */
@Component
@Service
public class SubCategoryService implements ISubCategoryService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(SubCategoryService.class);

	/** iSubCategoryRepository **/
	@Autowired
	private ISubCategoryRepository iSubCategoryRepository;

	/**
	 * default constructor
	 */
	public SubCategoryService() {

	}

	/**
	 * getSubCategory
	 */
	public List<SubCategoryDataModel> getSubCategory(String makeId, String categoryId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		CategoryDataModel categoryDataModel = new CategoryDataModel();
		List<SubCategoryEntity> lstSubCategoryEntity = iSubCategoryRepository.findByMakeIdAndCategoryId(makeId,
				categoryId);
		List<SubCategoryDataModel> lstSubCategoryDataModel = new ArrayList();
		SubCategoryEntity subCategoryEntity = null;
		SubCategoryDataModel subCategoryDataModel = null;

		int size = 0;

		size = lstSubCategoryEntity.size();
		for (int index = 0; index < size; index++) {
			subCategoryDataModel = new SubCategoryDataModel();
			subCategoryEntity = lstSubCategoryEntity.get(index);

			subCategoryDataModel.setCategoryId(subCategoryEntity.getCategoryId());
			subCategoryDataModel.setId(Integer.parseInt("" + subCategoryEntity.getId()));
			subCategoryDataModel.setName(subCategoryEntity.getName());
			subCategoryDataModel.setMakeId(subCategoryEntity.getMakeId());
			subCategoryDataModel.setSubCategoryId(subCategoryEntity.getSubCategoryId());

			lstSubCategoryDataModel.add(subCategoryDataModel);
		}
		return lstSubCategoryDataModel;

	}

	/**
	 * updateSubCategory
	 */
	public SubCategoryDataModel updateSubCategory(SubCategoryDataModel subCategoryDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		SubCategoryEntity subCategoryEntity = new SubCategoryEntity();

		subCategoryEntity.setId(subCategoryDataModel.getId());
		subCategoryEntity.setCategoryId(subCategoryDataModel.getCategoryId());
		subCategoryEntity.setName(subCategoryDataModel.getName());
		subCategoryEntity.setSubCategoryId(subCategoryDataModel.getSubCategoryId());
		iSubCategoryRepository.save(subCategoryEntity);
		return subCategoryDataModel;
	}

	/**
	 * add
	 */
	public SubCategoryDataModel add(SubCategoryDataModel subCategoryDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		SubCategoryEntity subCategoryEntity = new SubCategoryEntity();

		subCategoryEntity.setId(subCategoryDataModel.getId());
		subCategoryEntity.setCategoryId(subCategoryDataModel.getCategoryId());
		subCategoryEntity.setName(subCategoryDataModel.getName());
		subCategoryEntity.setSubCategoryId(subCategoryDataModel.getSubCategoryId());
		iSubCategoryRepository.save(subCategoryEntity);
		return subCategoryDataModel;
	}

	public SubCategoryDataModel getByName(String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		SubCategoryDataModel subCategoryDataModel = new SubCategoryDataModel();
		SubCategoryEntity subCategoryEntity = new SubCategoryEntity();

		subCategoryEntity = iSubCategoryRepository.findByName(name);
		BeanUtils.copyProperties(subCategoryEntity, subCategoryDataModel);
		return subCategoryDataModel;
	}
	
	public List<SubCategoryDataModel> getAllSubCategory()
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		List<SubCategoryEntity> lstSubCatEntity = iSubCategoryRepository.findAllSubCategories();
		List<SubCategoryDataModel> lstSubCategoryDataModels = new ArrayList();
		SubCategoryDataModel subCategoryDataModel = null;
		SubCategoryEntity sbCategoryEntity = null;
		int size = lstSubCatEntity.size();

		for (int index = 0; index < size; index++) {
			sbCategoryEntity = lstSubCatEntity.get(index);
			subCategoryDataModel = new SubCategoryDataModel();
			subCategoryDataModel.setName(sbCategoryEntity.getName());
			subCategoryDataModel.setSubCategoryId(sbCategoryEntity.getSubCategoryId());
			subCategoryDataModel.setMakeId(sbCategoryEntity.getMakeId());
			subCategoryDataModel.setCategoryId(sbCategoryEntity.getCategoryId());
			
			lstSubCategoryDataModels.add(subCategoryDataModel);
		}

		return lstSubCategoryDataModels;
	}

}
