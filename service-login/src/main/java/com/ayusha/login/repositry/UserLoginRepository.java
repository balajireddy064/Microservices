package com.ayusha.login.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ayusha.login.entity.UserLogin;

@Repository("userRepository")
public interface UserLoginRepository extends CrudRepository<UserLogin, String> {
	UserLogin findByEmailIdIgnoreCase(String emailId);

	UserLogin findByEmailId(String emailId);

}
