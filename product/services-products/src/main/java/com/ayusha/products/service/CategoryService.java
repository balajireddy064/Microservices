package com.ayusha.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ayusha.products.data.models.CategoryDataModel;
import com.ayusha.products.data.models.CategoryDataModels;
import com.ayusha.products.service.entity.CategoryEntity;
import com.ayusha.products.service.repository.ICategoryRepository;
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
public class CategoryService implements ICategoryService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(CategoryService.class);

	/** iCategoryRepository **/
	@Autowired
	private ICategoryRepository iCategoryRepository;

	/**
	 * default constructor
	 */
	public CategoryService() {

	}

	/**
	 * getAllCategory
	 */
	public List<CategoryDataModel> getAllCategory()
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		List<CategoryEntity> lstCategoryModel = iCategoryRepository.findAllCategories();
		List<CategoryDataModel> lstCategoryDataModel = new ArrayList();
		CategoryDataModel categoryDataModel = null;
		CategoryEntity categoryEntity = null;
		int size = lstCategoryModel.size();

		for (int index = 0; index < size; index++) {
			categoryEntity = lstCategoryModel.get(index);
			categoryDataModel = new CategoryDataModel();
			categoryDataModel.setCategoryId(categoryEntity.getCategoryId());
			categoryDataModel.setName(categoryEntity.getName());
			lstCategoryDataModel.add(categoryDataModel);
		}

		return lstCategoryDataModel;
	}

	/**
	 * getCategory
	 */
	public CategoryDataModel getCategory(String id)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		CategoryDataModel categoryDataModel = new CategoryDataModel();
		CategoryEntity categoryEntity = iCategoryRepository.findCategoryById(id);

		categoryDataModel.setId(Integer.parseInt("" + categoryEntity.getId()));
		categoryDataModel.setDescription(categoryEntity.getDescription());
		categoryDataModel.setName(categoryEntity.getName());
		categoryDataModel.setCategoryId(categoryEntity.getCategoryId());
		return categoryDataModel;
	}

	/**
	 * updateCategory
	 */
	public CategoryDataModel updateCategory(CategoryDataModel categoryDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		CategoryEntity categoryEntity = new CategoryEntity();

		categoryEntity.setId(categoryDataModel.getId());
		categoryEntity.setDescription(categoryDataModel.getDescription());
		categoryEntity.setName(categoryDataModel.getName());
		categoryEntity.setCategoryId(categoryDataModel.getCategoryId());
		iCategoryRepository.save(categoryEntity);
		return categoryDataModel;
	}

	/**
	 * add
	 */
	public CategoryDataModel add(CategoryDataModel categoryDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		CategoryEntity categoryEntity = new CategoryEntity();

		categoryEntity.setId(categoryDataModel.getId());
		categoryEntity.setDescription(categoryDataModel.getDescription());
		categoryEntity.setName(categoryDataModel.getName());
		categoryEntity.setCategoryId(categoryDataModel.getCategoryId());
		iCategoryRepository.save(categoryEntity);
		return categoryDataModel;
	}

	/**
	 * getByName
	 */
	public CategoryDataModel getByName(String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		CategoryDataModel categoryDataModel = new CategoryDataModel();
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity = iCategoryRepository.findByName(name);
		BeanUtils.copyProperties(categoryEntity, categoryDataModel);
		return categoryDataModel;

	}

}
