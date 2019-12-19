package com.ayusha.job.service.validations;

import org.springframework.stereotype.Component;

import com.ayusha.job.data.models.JobDataModel;
import com.ayusha.job.services.entity.JobEntity;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Job Model class Defines the job service data
 *         validation methods
 *
 */
@Component
public class JobDataValidation {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(JobDataValidation.class);

	/**
	 * default constructor
	 */
	public JobDataValidation() {

	}

	/**
	 * validate
	 */
	public boolean validate(JobDataModel jobDataModel) throws InvalidServiceRequestException {

		if (jobDataModel == null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		} else if (jobDataModel.getJobId() == null
				|| (jobDataModel.getJobId() != null && jobDataModel.getJobId().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		} else if (jobDataModel.getTicketId() == null
				|| (jobDataModel.getTicketId() != null && jobDataModel.getTicketId().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid ticket request data");
		} else if (jobDataModel.getCustomerId() == null
				|| (jobDataModel.getCustomerId() != null && jobDataModel.getCustomerId().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid customer request data");
		} else if (jobDataModel.getStatus() == null
				|| (jobDataModel.getStatus() != null && jobDataModel.getStatus().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid status request data");
		}
		return true;
	}

	public boolean updatevalidate(JobEntity jobDataModel) throws InvalidServiceRequestException {

		if (jobDataModel == null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		} else if (jobDataModel.getJobId() == null
				|| (jobDataModel.getJobId() != null && jobDataModel.getJobId().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		} else if (jobDataModel.getTicketId() == null
				|| (jobDataModel.getTicketId() != null && jobDataModel.getTicketId().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid ticket request data");
		} else if (jobDataModel.getCustomerId() == null
				|| (jobDataModel.getCustomerId() != null && jobDataModel.getCustomerId().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid customer request data");
		} else if (jobDataModel.getStatus() == null
				|| (jobDataModel.getStatus() != null && jobDataModel.getStatus().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid status request data");
		}
		return true;
	}

}
