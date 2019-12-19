package com.ayusha.job.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ayusha.job.data.models.JobDataModel;
import com.ayusha.job.data.models.JobsDataModel;
import com.ayusha.job.services.entity.JobEntity;
import com.ayusha.job.services.entity.JobTicketEntity;
import com.ayusha.job.specification.JobSpecification;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.user.data.models.UserDataModel;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Job Model class Defines the job service
 *         methods
 *
 */
public interface IJobService {

	/** save all **/
	public JobDataModel save(JobDataModel jobDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get By JobId **/
	public JobDataModel getJob(String jobId) throws DataPersistenceOperationException, InvalidServiceRequestException,
			ResourceNotFoundException, Exception;

	/** job assign with web **/
	public JobDataModel jobassignwithWeb(TicketDataModel ticketDataModel)
			throws ServicesDataException, DataPersistenceOperationException;

	/** get UserId **/
	public JobsDataModel getJobsFor(String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** get TicketId **/
	public JobTicketEntity getByJobTicketId(String ticketId)
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException;

	/** get By TicketId **/
	public List<JobDataModel> getByTicketIdDemo(String ticketId) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException;

	/** get Byjob based on CustomerName ANf statusId **/
	public JobDataModel getJobsFor(String customerId, String statusId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** update **/
	public JobDataModel update(JobDataModel jobDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** update job status **/
	public JobDataModel updateJobStatus(String jobId, String statusId)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** updates Notes **/
	public JobDataModel updateJobNotes(String jobId, String notes)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** job assign **/
	public JobTicketEntity findJobId(String ticketId)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** assign **/
	public void assignJob(TicketDataModel ticketDataModel)
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException;

	/** update Based On status **/
	public JobEntity updateBasedOnStatus(String statusId, String ticketId, JobDataModel jobDataModel);

	/** update **/
	public JobEntity jobUpdate(JobEntity jobEntity)
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException;

	/** getAll Jobs **/
	public Page<JobEntity> getAllJob()
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException;

	/** searchParam **/
	public Page<JobDataModel> searchDetails(JobSpecification userSpecification)
			throws ResourceNotFoundException, Exception;

}
