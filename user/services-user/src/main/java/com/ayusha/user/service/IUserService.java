package com.ayusha.user.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.NoContentException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.user.commons.Response;
import com.ayusha.user.data.models.LocationsUsersDataModel;
import com.ayusha.user.data.models.UserDataModel;
import com.ayusha.user.data.models.UserLocationsDataModel;
import com.ayusha.user.data.models.UserLoginDataModel;
import com.ayusha.user.services.entity.UserEntity;
import com.ayusha.user.services.entity.UserLocationEntity;
import com.ayusha.user.specification.UserSpecification;

/**
 * 
 * @author Finch Date : 01-Aug-2019 User Service Methods
 *
 */
public interface IUserService {

	public Response save(UserDataModel userDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, IOException, ParseException;

	public Page<UserDataModel> getAllUsers(Pageable pageable) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	public UserDataModel getUserByUserId(String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	public LocationsUsersDataModel getUsersServingLocation(String locationid) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	public UserDataModel login(String email) throws ResourceNotFoundException, Exception;

	public UserDataModel resetPassword(String email, UserDataModel userDataModel);

	public UserLoginDataModel login(UserDataModel body) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, NoContentException, Exception;

	public List<UserLocationEntity> getAllUserLocation() throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, NoContentException, Exception;

	public Page<UserDataModel> searchDetails(UserSpecification userSpecification);

	public String getAllUsersList() throws DataPersistenceOperationException, InvalidServiceRequestException,
			ResourceNotFoundException, NoContentException, Exception;

	public Response emailSendToAcount(UserEntity user);

	public Response saveImages(MultipartFile file, String userId)
			throws DataPersistenceOperationException, InvalidServiceRequestException, IOException;

	public UserLocationsDataModel getLocations(String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	public UserDataModel updatepassword(UserDataModel userDataModel);

	public UserDataModel updatePartially(UserDataModel userDataModel, String userId)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception;

	/** Demo GetAllUsers **/
	public List<UserDataModel> getAllUsersDemo() throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

}
