package com.ayusha.user.services.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ayusha.user.services.entity.UserEntity;

/**
 * 
 * @author Finch Date : 01-Aug-2019 User Service Methods
 *
 */
public interface IUserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

	UserEntity findByUserId(String userId);

	UserEntity findByEmail(String findByEmail);

	@Query("SELECT t FROM UserEntity t WHERE t.email=?1")
	UserEntity findUserByEmailId(String emailId);

	@Query("SELECT t FROM UserEntity t WHERE t.email=?1")
	UserEntity findByEmailIdIgnoreCase(String email);

	@Query("SELECT t FROM UserEntity t WHERE t.userId = ?1")
	UserEntity findUserByUserId(String userId);

	@Query("select t FROM UserEntity t")
	List<UserEntity> findAllUser(Pageable pageable);

	UserEntity findByEmailIgnoreCase(String email);

}
