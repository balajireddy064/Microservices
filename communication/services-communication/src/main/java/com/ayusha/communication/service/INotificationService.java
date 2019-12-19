package com.ayusha.communication.service;

import com.ayusha.communication.core.NotificationOperationException;
import com.ayusha.communication.data.models.CommunicationModel;
import com.ayusha.communication.data.models.NotificationDataModel;
import com.ayusha.communication.data.models.NotificationMessageDataModel;
import com.ayusha.login.commons.Response;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface INotificationService {

	/** save **/

	public CommunicationModel save(CommunicationModel notificationDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, NotificationOperationException;

	/** find By jobCode and otp **/
	public CommunicationModel validationOtp(String jobCode, int otp) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** check this method phone number Exists or Not **/
	public boolean checkPhoneNumberExists(String phone);

	/** otp validation method **/
	public Response validationOtp(String otp, long id) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	public CommunicationModel sendOtp(CommunicationModel communicationModel) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

}
