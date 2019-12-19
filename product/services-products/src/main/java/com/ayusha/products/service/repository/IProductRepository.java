package com.ayusha.products.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ayusha.products.service.entity.ProductEntity;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Product Model class Defines the methods for
 *         the product management persistance CRUD operations
 *
 */
@Component
@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Integer> {

	@Query("SELECT t FROM ProductEntity t WHERE t.productId = ?1")
	ProductEntity findProductByProductId(String productId);

	@Query("SELECT t FROM ProductEntity t WHERE t.categoryId=:categoryId and t.make_id=:makeId and t.subCategoryId=:subCategoryId")
	List<ProductEntity> findProduct(@Param("categoryId") String categoryId,
			@Param("subCategoryId") String subCategoryId, @Param("makeId") String makeId);

	@Query("SELECT t FROM ProductEntity t WHERE t.categoryId=:categoryId and t.make_id=:makeId and t.subCategoryId=:subCategoryId and t.modelId=:modelId")
	ProductEntity findProducts(@Param("categoryId") String categoryId, @Param("subCategoryId") String subCategoryId,
			@Param("makeId") String makeId, @Param("modelId") String modelId);
}
