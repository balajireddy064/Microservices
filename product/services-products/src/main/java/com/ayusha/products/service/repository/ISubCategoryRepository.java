package com.ayusha.products.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ayusha.products.service.entity.SubCategoryEntity;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Product Model class Defines the methods for
 *         the product management persistance CRUD operations
 *
 */

@Component
@Repository
public interface ISubCategoryRepository extends JpaRepository<SubCategoryEntity, Integer> {

	@Query("SELECT t FROM SubCategoryEntity t WHERE t.id = ?1")
	SubCategoryEntity findSubCategoryById(String id);

	@Query("SELECT t FROM SubCategoryEntity t WHERE t.subCategoryId = ?1")
	SubCategoryEntity findSubCategoryBySubCatId(String id);

	@Query("select t FROM SubCategoryEntity t")
	List<SubCategoryEntity> findAllSubCategories();

	/** findByMakeIdAndCategoryId **/
	List<SubCategoryEntity> findByMakeIdAndCategoryId(String makeId, String categoryId);

	SubCategoryEntity findByName(String name);
}
