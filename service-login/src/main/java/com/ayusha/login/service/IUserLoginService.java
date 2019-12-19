package com.ayusha.login.service;

import com.ayusha.login.commons.Response;
import com.ayusha.login.entity.UserLogin;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

public interface IUserLoginService {

	public Response updatepassword(UserLogin user);

	public Response emailSendToAcount(UserLogin user);

	public Response loginByAndr(UserLogin user);

	public Response loginByUser(UserLogin user)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;
}
