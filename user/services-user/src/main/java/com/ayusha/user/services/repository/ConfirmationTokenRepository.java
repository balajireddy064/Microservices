package com.ayusha.user.services.repository;

import org.springframework.data.repository.CrudRepository;

import com.ayusha.user.services.entity.ConfirmationToken;
/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * User Service methods
 *
 */
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);

}
