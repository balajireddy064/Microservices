package com.ayusha.products.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ayusha.products.service.entity.ModelEntity;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Product Model class Defines the methods for
 *         the product management persistance CRUD operations
 *
 */
@Component
@Repository
public interface IModelRepository extends JpaRepository<ModelEntity, Integer> {

	@Query("SELECT t FROM ModelEntity t WHERE t.makeId = ?1 and t.categoryId = ?1 and t.subCategoryId=?1 ")
	List<ModelEntity> findModelById(String makeId, String categoryId, String subCategoryId);

	@Query("SELECT t FROM ModelEntity t WHERE t.modelId = ?1")
	ModelEntity findModelByModelId(String modelId);

	@Query("select t FROM ModelEntity t")
	List<ModelEntity> findAllModels();

	List<ModelEntity> findModelByMakeIdAndCategoryIdAndSubCategoryId(String makeId, String categoryId,
			String subCategoryId);

	ModelEntity findByName(String name);

}
