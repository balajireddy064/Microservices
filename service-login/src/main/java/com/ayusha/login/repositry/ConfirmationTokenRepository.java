package com.ayusha.login.repositry;

import org.springframework.data.repository.CrudRepository;

import com.ayusha.login.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
