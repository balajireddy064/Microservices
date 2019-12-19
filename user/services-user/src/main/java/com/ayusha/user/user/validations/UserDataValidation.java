package com.ayusha.user.user.validations;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.user.data.models.UserDataModel;
import com.ayusha.user.data.models.UserEducationDetailsDataModels;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 User Service Methods
 *
 */
@Component
public class UserDataValidation {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(UserDataValidation.class);

	/**
	 * default constructor
	 */
	public UserDataValidation() {

	}

	/**
	 * validattion inserting
	 */
	public boolean validate(UserDataModel userDataModel) throws InvalidServiceRequestException {

		if (userDataModel == null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		} else if (userDataModel.getFirstName() == null || userDataModel.getFirstName().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid FirstName");
		} else if (userDataModel.getLastName() == "" || userDataModel.getLastName().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid LastName ");
		} else if (userDataModel.getDateOfBirth() == "" || userDataModel.getDateOfBirth().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid DateOfBirth");
		} else if (userDataModel.getEmail() == "" || userDataModel.getEmail().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid EmailId");
		} else if (userDataModel.getPhoneNumber() == "" || userDataModel.getPhoneNumber().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid PhoneNumber");
		} else if (userDataModel.getRole() == "" || userDataModel.getRole().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid Role");
		} else if ( userDataModel.getDateOfJoining().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid DateOfJoining");
		} else if (userDataModel.getExpertiseLevel() == "" || userDataModel.getExpertiseLevel().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid ExpertiseLevel");
		} else if (userDataModel.getSkills() == "" || userDataModel.getSkills().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid Skills");
		} else if (userDataModel.getSalary() == "" || userDataModel.getSalary().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid salary");
		} else if (userDataModel.getState() == "" || userDataModel.getState().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid State");
		} else if (userDataModel.getCity() == "" || userDataModel.getCity().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid city");
		} else if (userDataModel.getPinCode() == "" || userDataModel.getPinCode().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid pincode");
		}
		return true;
	}

	/**
	 * 
	 * @param userDataModel
	 * @return true
	 * @throws InvalidServiceRequestException
	 */
	public boolean validationEducation(UserDataModel userDataModel) throws InvalidServiceRequestException {

		List<UserEducationDetailsDataModels> lstUserEducationDetailsDataModels = userDataModel
				.getUserEducationDetailsDataModels();
		int size = 0;
		size = lstUserEducationDetailsDataModels.size();
		for (int i = 0; i < size; i++) {
			UserEducationDetailsDataModels userEducationDetailsDataModels = new UserEducationDetailsDataModels();
			userEducationDetailsDataModels = lstUserEducationDetailsDataModels.get(i);
			if (userEducationDetailsDataModels.getQualification() == ""
					|| userEducationDetailsDataModels.getQualification() == null) {
				throw new InvalidServiceRequestException("Invalid Qualification");
			} else if (userEducationDetailsDataModels.getPassOut() == ""
					|| userEducationDetailsDataModels.getPassOut() == null) {
				throw new InvalidServiceRequestException("Invalid passout");
			}
		}
		return true;
	}

	/**
	 * 
	 * @param userDataModel
	 * @return true
	 * @throws InvalidServiceRequestException
	 */
	public boolean validationUpdate(UserDataModel userDataModel) throws InvalidServiceRequestException {

		if (userDataModel == null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		} else if (userDataModel.getPhoneNumber() == "" || userDataModel.getPhoneNumber() == null) {
			throw new InvalidServiceRequestException("Invalid PhoneNumber");
		} else if (userDataModel.getRole() == "" || userDataModel.getRole() == null) {
			throw new InvalidServiceRequestException("Invalid Role");
		} else if (userDataModel.getDateOfJoining() == null) {
			throw new InvalidServiceRequestException("Invalid DateOfJoining");
		} else if (userDataModel.getExpertiseLevel() == "" || userDataModel.getExpertiseLevel() == null) {
			throw new InvalidServiceRequestException("Invalid ExpertiseLevel");
		} else if (userDataModel.getSkills() == "" || userDataModel.getSkills() == null) {
			throw new InvalidServiceRequestException("Invalid Skills");
		} else if (userDataModel.getSalary() == "" || userDataModel.getSalary() == null) {
			throw new InvalidServiceRequestException("Invalid salary");
		} else if (userDataModel.getState() == "" || userDataModel.getState() == null) {
			throw new InvalidServiceRequestException("Invalid State");
		} else if (userDataModel.getCity() == "" || userDataModel.getCity() == null) {
			throw new InvalidServiceRequestException("Invalid city");
		} else if (userDataModel.getPinCode() == "" || userDataModel.getPinCode() == null) {
			throw new InvalidServiceRequestException("Invalid pincode");
		} else if (userDataModel.getDateOfBirth() == "" || userDataModel.getDateOfBirth() == null) {
			throw new InvalidServiceRequestException("Invalid DateOfBirth");
		}
		return true;
	}

	/**
	 * @param userDataModel
	 * @return true
	 * @throws InvalidServiceRequestException
	 */
	public boolean validationUpdateEducation(UserDataModel userDataModel) throws InvalidServiceRequestException {

		List<UserEducationDetailsDataModels> lstUserEducationDetailsDataModels = userDataModel
				.getUserEducationDetailsDataModels();
		int size = 0;
		size = lstUserEducationDetailsDataModels.size();
		for (int i = 0; i < size; i++) {
			UserEducationDetailsDataModels userEducationDetailsDataModels = new UserEducationDetailsDataModels();
			userEducationDetailsDataModels = lstUserEducationDetailsDataModels.get(i);
			if (userEducationDetailsDataModels.getQualification() == ""
					|| userEducationDetailsDataModels.getQualification() == null) {
				throw new InvalidServiceRequestException("Invalid Qualification");
			} else if (userEducationDetailsDataModels.getPassOut() == ""
					|| userEducationDetailsDataModels.getPassOut() == null) {
				throw new InvalidServiceRequestException("Invalid passout");
			}
		}
		return true;
	}
}
