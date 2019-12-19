package com.ayusha.job.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ayusha.job.data.models.JobDataModel;
import com.ayusha.job.data.models.JobsDataModel;
import com.ayusha.job.service.validations.ConstantPropertise;
import com.ayusha.job.service.validations.JobDataValidation;
import com.ayusha.job.services.entity.JobEntity;
import com.ayusha.job.services.entity.JobTicketEntity;
import com.ayusha.job.services.repository.IJobRepository;
import com.ayusha.job.services.repository.IJobTicketRepository;
import com.ayusha.job.specification.JobSpecification;
import com.ayusha.job.user.data.handler.UserLocationsDataHandler;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.ayusha.ticket.data.models.CustomerDataModel;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.user.data.models.LocationsUsersDataModel;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Job Service Methods
 * 
 *         1. Recording (single): a. Persisting in DB b. on Success - sending an
 *         email/sms to customer c. assigning service invoking
 * 
 *         2. Update: a. On change of status - sending an email/sms notification
 * 
 *         3. Batch Recording: a. Persisting in DB b. on Success - sending an
 *         email/sms to customer -seggregating and sending an single email c.
 *         assigning service invoking - Individually
 * 
 *         4. Search: a. search based on date, user, customer,logged date,
 *         issue,servicetype,serialnumber
 * 
 *         5. Sorting: a. sorting based on logged date,status,servicetype (ASC |
 *         DSC)
 */
@Service
public class JobService implements IJobService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(JobService.class);

	/** iJobRepositiory **/
	@Autowired
	private IJobRepository iJobRepositiory;

	/** iJobTicketRepository **/
	@Autowired
	private IJobTicketRepository iJobTicketRepository;

	/** dateForMate **/
	@Autowired
	private DateFormate dateForMate;

	/**
	 * service call to create job, dependent on ticket service
	 */
	public JobDataModel save(JobDataModel jobDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		JobEntity jobEntity = new JobEntity();

		LOG.info("entered JobAssigning 0000000000000000000000000 99999999999 " + jobDataModel.getCustomerName());
		populateJobEntity(jobEntity, jobDataModel);

		iJobRepositiory.save(jobEntity);
		return jobDataModel;
	}

	/**
	 * service call to get job by job id no other service dependency
	 */
	public JobDataModel getJob(String jobId) throws DataPersistenceOperationException, InvalidServiceRequestException,
			ResourceNotFoundException, Exception {
		LOG.info(" LOG FOUND ================ " + jobId);
		// JobEntity jobEntity = new JobEntity();
		JobDataModel jobDataModel = new JobDataModel();
		List<JobEntity> listEntity = iJobRepositiory.findJobByJobIdInExcel(jobId);

		for (JobEntity jobEntity : listEntity) {
			LOG.info(" LOG FOUND ===========<<jobEntity>>==== " + jobEntity);
			populateJobDataModel(jobDataModel, jobEntity);
			return jobDataModel;
		}
		return jobDataModel;
	}

	/**
	 * service call to update job no other service dependency
	 */
	public JobDataModel update(JobDataModel jobDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		JobEntity jobEntity = new JobEntity();

		populateJobEntity(jobEntity, jobDataModel);

		iJobRepositiory.save(jobEntity);
		return jobDataModel;
	}

	/**
	 * service call to update job status by job id and status, no other service
	 * dependency
	 */
	public JobDataModel updateJobStatus(String jobId, String statusId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		JobEntity jobEntity = new JobEntity();
		JobDataModel jobDataModel = new JobDataModel();
		jobEntity = iJobRepositiory.findJobByJobId(jobId);

		if (statusId != null && statusId.trim().length() > 0) {
			jobEntity.setStatus(statusId);
			iJobRepositiory.save(jobEntity);
			populateJobDataModel(jobDataModel, jobEntity);
		}
		return jobDataModel;
	}

	/**
	 * service call to update job noted by job id and notes, no other service
	 * dependency
	 */
	public JobDataModel updateJobNotes(String jobId, String notes)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		JobEntity jobEntity = new JobEntity();
		JobDataModel jobDataModel = new JobDataModel();
		jobEntity = iJobRepositiory.findJobByJobId(jobId);

		if (notes != null && notes.trim().length() > 0) {
			jobEntity.setNotes(notes);
			iJobRepositiory.save(jobEntity);
			populateJobDataModel(jobDataModel, jobEntity);
		}
		return jobDataModel;
	}

	/**
	 * populate job data from job entity to job data model
	 */
	private void populateJobEntity(JobEntity jobEntity, JobDataModel jobDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		// format date
		DateTimeFormatter dateForMate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		jobEntity.setActualStartDate(dateForMate.format(now));
		jobEntity.setActualEndDate(jobDataModel.getActualEndDate());
		jobEntity.setJobId(jobDataModel.getJobId());
		jobEntity.setLastUpdatedOn(dateForMate.format(now));
		jobEntity.setNotes(jobDataModel.getNotes());
		jobEntity.setStartDate(dateForMate.format(now));
		jobEntity.setStatus(jobDataModel.getStatus());
		jobEntity.setCustomerName(jobDataModel.getCustomerName());
		jobEntity.setUserId(jobDataModel.getUserId());
		jobEntity.setCustomerId(jobDataModel.getCustomerId());
		jobEntity.setTicketId(jobDataModel.getTicketId());
	}

	/**
	 * populate job data from model to entity
	 */
	private void populateJobDataModel(JobDataModel jobDataModel, JobEntity jobEntity)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		jobDataModel.setActualStartDate(jobEntity.getActualStartDate());
		jobDataModel.setActualEndDate(jobEntity.getActualEndDate());
		jobDataModel.setJobId(jobEntity.getJobId());
		jobDataModel.setLastUpdatedOn(jobEntity.getLastUpdatedOn());
		jobDataModel.setNotes(jobEntity.getNotes());
		jobDataModel.setStartDate(jobEntity.getStartDate());
		jobDataModel.setStatus(jobEntity.getStatus());
		jobDataModel.setUserId(jobEntity.getUserId());
		jobDataModel.setCustomerId(jobEntity.getCustomerId());
		jobDataModel.setCustomerName(jobEntity.getCustomerName());
		jobDataModel.setTicketId(jobEntity.getTicketId());

	}

	/**
	 * service call to get jobs by userId, no other service dependency
	 */
	public JobsDataModel getJobsFor(String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		JobsDataModel jobsDataModel = new JobsDataModel();
		List<JobEntity> lstJobEntity = iJobRepositiory.findJobByUserId(userId);
		List<JobDataModel> lstJobDataModel = new ArrayList();
		JobEntity jobEntity = null;
		JobDataModel jobDataModel = null;
		int size = 0;

		size = lstJobEntity.size();

		for (int index = 0; index < size; index++) {
			jobEntity = lstJobEntity.get(index);
			jobDataModel = new JobDataModel();
			populateJobDataModel(jobDataModel, jobEntity);
			lstJobDataModel.add(jobDataModel);
		}
		jobsDataModel.setLstJobModel(lstJobDataModel);
		return jobsDataModel;
	}

	/**
	 * service call to get jobs by customer id and status no other service
	 * dependency
	 */
	public JobDataModel getJobsFor(String customerId, String statusId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		JobDataModel jobDataModel = new JobDataModel();
		JobEntity jobEntity = iJobRepositiory.findJobBycustomerIdAndStatus(customerId, statusId);
		BeanUtils.copyProperties(jobEntity, jobDataModel);
		return jobDataModel;
	}

	/**
	 * service call to find job by ticket id, no other service dependency
	 */
	public JobTicketEntity findJobId(String ticketId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		return iJobTicketRepository.findJobByTicketId(ticketId);
	}

	/**
	 * service call to assign job, no other service dependency
	 */
	public void assignJob(TicketDataModel ticketDataModel)
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException {
		LOG.info("entered JobAssigning 123 - start");

		UserLocationsDataHandler userLocationsDataHandler = null;
		LocationsUsersDataModel locationsUsersDataModel = null;
		List<String> lstUserIds = null;
		JobTicketEntity jobTicketEntity = null;
		JobEntity jobEntity = null;
		LOG.info(" ticket data is ====>" + ticketDataModel);
		JobDataModel jobDataModel = buildJob("", "");
		LOG.info("ticket data is " + ticketDataModel.getTicketId());
		if (ticketDataModel != null && ticketDataModel.getTicketId() != null) {
			CustomerDataModel customerDataModel = ticketDataModel.getCustomerDataModel();
			TicketDataModel ticketDataModel2 = new TicketDataModel();
			RestTemplate restTemplate = new RestTemplate();

			ticketDataModel2 = restTemplate.getForObject(
					ConstantPropertise.GET_BY_TICKET_ID + ticketDataModel.getTicketId(), TicketDataModel.class);
			LOG.info("TICKET MESSAGE IS <<CUSTOMER MODEL>> " + customerDataModel.getPinCode());
			LOG.info("TICKET MESSAGE ISssssssssssssssssssssssssss <<CUSTOMERId>> " + ticketDataModel2.getCustomerId());
			if (customerDataModel != null && customerDataModel.getPinCode() != null) {
				LOG.info(" PIN NUMBER IS::::::: " + customerDataModel.getPinCode());
				userLocationsDataHandler = new UserLocationsDataHandler();
				locationsUsersDataModel = userLocationsDataHandler
						.getUsersForLocationId(customerDataModel.getPinCode());
				LOG.info(" PIN NUMBER IS <<locationsUsersDataModel>> " + locationsUsersDataModel);
				if (locationsUsersDataModel != null && locationsUsersDataModel.getLstUsers() != null) {
					lstUserIds = locationsUsersDataModel.getLstUsers();
					LOG.info(" PIN NUMBER IS <<lstUserIdaaaaaaaaaaaaaaaaaaaaa>> " + lstUserIds);
					if (lstUserIds != null && lstUserIds.size() == 1 || lstUserIds != null && lstUserIds.size() == 0) {
						LOG.info(" PIN NUMBER IS <<lstUserIdsssssssssssssssssss------1>> " + lstUserIds);
						jobTicketEntity = findJobId(ticketDataModel.getTicketId());
						LOG.info(" PIN NUMBER IS <<lstUserIdggggggggggggggggggggggg------1>> " + lstUserIds);
						if (jobTicketEntity != null && jobTicketEntity.getJobId() != null) {

							jobEntity = iJobRepositiory.findJobByJobId(jobTicketEntity.getJobId());

							jobEntity.setUserId(lstUserIds.get(0));
							LOG.info(" PIN NUMBER IS <<lstUserId>> " + lstUserIds);

						} else {
							if (lstUserIds.isEmpty()) {
								jobDataModel.setUserId(null);
								LOG.info("TICKET MESSAGE IS <<CUSTOMERId>> " + customerDataModel.getCustomerId());
								jobDataModel.setCustomerId(ticketDataModel2.getCustomerId());
								jobDataModel.setTicketId(ticketDataModel.getTicketId());

								jobDataModel.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());
								save(jobDataModel);
								jobTicketEntity = new JobTicketEntity();
								jobTicketEntity.setJobId(jobDataModel.getJobId());
								jobTicketEntity.setTicketId(ticketDataModel.getTicketId());
								jobTicketEntity.setUserId("");

								iJobTicketRepository.save(jobTicketEntity);
							} else {
								jobDataModel.setUserId(lstUserIds.get(0));
								jobDataModel.setCustomerId(ticketDataModel2.getCustomerId());
								jobDataModel.setTicketId(ticketDataModel.getTicketId());

								LOG.info("CustomerID " + ticketDataModel.getCustomerDataModel().getCustomerName());

								jobDataModel.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());
								save(jobDataModel);
								jobTicketEntity = new JobTicketEntity();
								jobTicketEntity.setJobId(jobDataModel.getJobId());
								jobTicketEntity.setTicketId(ticketDataModel.getTicketId());
								jobTicketEntity.setUserId(lstUserIds.get(0));

								iJobTicketRepository.save(jobTicketEntity);

							}
							LOG.info(" PIN NUMBER IS <<lstUserId------1>> " + lstUserIds);
						}
					} else if (lstUserIds != null && lstUserIds.size() > 1) {
						LOG.info(" PIN NUMBER IS <<lstUserId:::::::::::::>> " + lstUserIds);
						jobTicketEntity = findJobId(ticketDataModel.getTicketId());
						if (jobTicketEntity != null && jobTicketEntity.getJobId() != null) {
							LOG.info(
									" PIN NUMBER IS <<lstUserIdszzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz-----2222222222222222>> "
											+ lstUserIds);

							jobEntity = iJobRepositiory.findJobByJobId(jobTicketEntity.getJobId());
							jobEntity.setUserId(lstUserIds.get(0));
							jobEntity.setCustomerId(ticketDataModel2.getCustomerId());

							jobEntity.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());

							jobEntity.setTicketId(ticketDataModel.getTicketId());

							iJobRepositiory.save(jobEntity);
						} else {
							LOG.info(" PIN NUMBER IS <<lstUserIdsnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn-----2222222222222222>> "
									+ lstUserIds);

							jobDataModel.setUserId(lstUserIds.get(0));
							jobDataModel.setCustomerId(ticketDataModel2.getCustomerId());
							jobDataModel.setTicketId(ticketDataModel.getTicketId());
							jobDataModel.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());
							save(jobDataModel);
							jobTicketEntity = new JobTicketEntity();
							jobTicketEntity.setJobId(jobDataModel.getJobId());
							jobTicketEntity.setTicketId(ticketDataModel.getTicketId());
							jobTicketEntity.setUserId(lstUserIds.get(0));
							iJobTicketRepository.save(jobTicketEntity);
						}
					} else {
						iJobRepositiory.save(jobEntity);
					}
				}
				LOG.info(" << locationsUsersDataModel >> " + locationsUsersDataModel.getLstUsers());
			}
		}
	}

	/**
	 * service call to assign job no other service dependency
	 */
	public JobDataModel jobassignwithWeb(TicketDataModel ticketDataModel)
			throws ServicesDataException, DataPersistenceOperationException {

		DateTimeFormatter jobIdDateFormat = DateTimeFormatter.ofPattern("ddMMyyHHmmss-");

		DateTimeFormatter dateForMate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		TicketDataModel ticketDataModel2 = new TicketDataModel();
		RestTemplate restTemplate = new RestTemplate();

		ticketDataModel2 = restTemplate.getForObject(
				ConstantPropertise.GET_BY_TICKET_ID + ticketDataModel.getTicketId(), TicketDataModel.class);

		JobEntity jobEntity = new JobEntity();
		JobDataModel jobDataModel = null;
		boolean resultCust = checkEmailExist(ticketDataModel.getTicketId());
		if (resultCust == false) {

			jobDataModel = new JobDataModel();
			jobEntity.setActualEndDate(dateForMate.format(now));
			jobEntity.setActualStartDate(dateForMate.format(now));
			jobEntity.setStartDate(dateForMate.format(now));
			jobEntity.setJobId(("ATAS-JOB-" + jobIdDateFormat.format(now) + getNextSequenceNumber()));
			jobEntity.setLastUpdatedOn(dateForMate.format(now));
			jobEntity.setCustomerId(ticketDataModel2.getCustomerId());
			jobEntity.setTicketId(ticketDataModel.getTicketId());
			jobEntity.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());
			jobEntity.setStatus("OPEN");

			jobEntity.setUserId(ticketDataModel.getUserId());
			jobDataModel.setNotes("Ticket Opened");
			jobDataModel.setStatus("OPEN");

			iJobRepositiory.save(jobEntity);

			JobTicketEntity jobTicketEntity = new JobTicketEntity();
			jobTicketEntity.setJobId(jobDataModel.getJobId());
			jobTicketEntity.setTicketId(ticketDataModel.getTicketId());
			jobTicketEntity.setUserId(ticketDataModel.getUserId());
			jobTicketEntity.setJobId(jobEntity.getJobId());
			iJobTicketRepository.save(jobTicketEntity);
		} else {

			JobEntity checkTicketId1 = iJobRepositiory.checkTicketId(ticketDataModel.getTicketId());
			JobTicketEntity checkTicketExist1 = iJobTicketRepository.findJobByTicketId(ticketDataModel.getTicketId());

			jobDataModel = new JobDataModel();
			checkTicketId1.setActualStartDate(dateForMate.format(now));
			checkTicketId1.setStartDate(dateForMate.format(now));
			checkTicketId1.setLastUpdatedOn(dateForMate.format(now));
			checkTicketId1.setStatus("OPEN");
			checkTicketId1.setUserId(ticketDataModel.getUserId());
			checkTicketId1.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());
			iJobRepositiory.save(checkTicketId1);

			checkTicketExist1.setUserId(ticketDataModel.getUserId());
			iJobTicketRepository.save(checkTicketExist1);

		}
		return jobDataModel;
	}

	/** check to see if user exist or not **/
	public boolean checkEmailExist(String ticketId) {
		JobEntity jobEntity = null;
		jobEntity = iJobRepositiory.checkTicketId(ticketId);
		if (jobEntity == null) {
			return false;
		} else {
			return true;
		}
	}

	private long value = 000;

	// generate sequence numbers
	public String getNextSequenceNumber() {
		value++;
		String seq_num = String.format("%03d", value);
		return seq_num;
	}

	/**
	 * service call to build job no other service dependency
	 */
	private JobDataModel buildJob(String userId, String loggedBy) {

		DateTimeFormatter jobIdDateFormat = DateTimeFormatter.ofPattern("ddMMyyHHmmss-");

		DateTimeFormatter dateForMate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		Date date = new Date();
		JobDataModel jobDataModel = new JobDataModel();
		jobDataModel.setActualEndDate(dateForMate.format(now));
		jobDataModel.setActualStartDate(dateForMate.format(now));
		jobDataModel.setStartDate(dateForMate.format(now));
		jobDataModel.setJobId(("ATAS-JOB-" + jobIdDateFormat.format(now) + getNextSequenceNumber()));
		jobDataModel.setLastUpdatedOn(dateForMate.format(now));
		// jobDataModel.setLoggedBy(loggedBy);
		jobDataModel.setNotes("Ticket Opened");
		jobDataModel.setStatus("OPEN");
		jobDataModel.setUserId(userId);
		return jobDataModel;
	}

	/**
	 * service call to update job based on status no other service dependency
	 */
	public JobEntity updateBasedOnStatus(String statusId, String ticketId, JobDataModel jobDataModel) {
		JobEntity jobEntity = new JobEntity();
		boolean checkStatusExistOrNot = checkStutusIdExist(statusId, ticketId);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		JobTicketEntity findByjobId = iJobTicketRepository.findJobByTicketId(ticketId);

		if (checkStatusExistOrNot == false) {
			TicketDataModel ticketDataModel2 = new TicketDataModel();
			RestTemplate restTemplate = new RestTemplate();
			ticketDataModel2 = restTemplate.getForObject(ConstantPropertise.GET_BY_TICKET_ID + ticketId,
					TicketDataModel.class);

			jobEntity.setStatus(jobDataModel.getStatus());
			jobEntity.setActualStartDate(now.format(dtf));
			jobEntity.setLastUpdatedOn(now.format(dtf));
			jobEntity.setStartDate(now.format(dtf));
			jobEntity.setTicketId(ticketId);
			jobEntity.setCustomerId(ticketDataModel2.getCustomerId());
			jobEntity.setUserId(ticketDataModel2.getUserId());
			jobEntity.setNotes(jobDataModel.getNotes());
			jobEntity.setReason(jobDataModel.getReason());
			jobEntity.setJobId(findByjobId.getJobId());
			jobEntity.setReason(jobDataModel.getReason());
			jobEntity.setJobId(jobDataModel.getJobId());
			iJobRepositiory.save(jobEntity);
			return jobEntity;
		} else {
			JobEntity jobEntity1 = iJobRepositiory.findByStatus(jobDataModel.getStatus());
			jobEntity1.setStatus(jobDataModel.getStatus());
			jobEntity1.setLastUpdatedOn(now.format(dtf));
			jobEntity1.setReason(jobDataModel.getReason());
			iJobRepositiory.save(jobEntity1);
			return jobEntity1;
		}
	}

	/**
	 * service call to get all jobs no other service dependency
	 */
	@Override
	public Page<JobEntity> getAllJob()
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException {
		Page<JobEntity> page = iJobRepositiory
				.findAll(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "jobId")));
		return page;
	}

	// check if job exist based on ticket id and status
	public boolean checkStutusIdExist(String status, String ticketId) {
		JobEntity jobEntity = null;
		jobEntity = iJobRepositiory.findByStatusAndTicketId(status, ticketId);
		if (jobEntity == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * service call to get job by ticket id, no other service dependency
	 */
	public JobTicketEntity getByJobTicketId(String ticketId)
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException {
		JobTicketEntity jobEntity = iJobTicketRepository.findJobByTicketId(ticketId);
		return jobEntity;
	}

	/**
	 * service call to update job, no other service dependency
	 */
	@Override
	public JobEntity jobUpdate(JobEntity jobEntity)
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException {
		LOG.info(" LOG FOUND JOBS ================ ");
		List<JobEntity> jobList = iJobRepositiory.findByTicketId(jobEntity.getTicketId());

		DateTimeFormatter dateForMate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		JobEntity jobSave = new JobEntity();
		JobTicketEntity jobTicketEntity = null;
		JobEntity response = null;
		JobEntity jobUpdate;
		String updateStatus = "";
		long id = 0;

		// validation
		String je = JSONConverter.convertPOJOToString(jobEntity);
		JobDataModel jv = (JobDataModel) JSONConverter.convertStringToPOJO(je, JobDataModel.class);
		JobDataValidation jobDataValidation = new JobDataValidation();

		jobDataValidation.updatevalidate(jobEntity);

		for (JobEntity j : jobList) {
			if (j.getStatus().equalsIgnoreCase(jobEntity.getStatus())) {
				LOG.info(" LOG FOUND ================ " + jobEntity.getStatus());
				updateStatus = j.getStatus();
				id = j.getId();
			}
		}

		// update and save data
		if (updateStatus.equalsIgnoreCase(jobEntity.getStatus())) {
			LOG.info(" JOB UPDATE " + jobEntity.getStatus());
			jobUpdate = iJobRepositiory.findBySeriesId(id);
			jobUpdate.setActualEndDate(dateForMate.format(now));
			// jobUpdate.setActualStartDate(dateForMate.format(now));
			jobUpdate.setNotes(jobEntity.getNotes());
			jobUpdate.setCustomerId(jobEntity.getCustomerId());
			jobUpdate.setCustomerName(jobEntity.getCustomerName());
			jobUpdate.setStartDate(dateForMate.format(now));
			jobUpdate.setJobId(jobEntity.getJobId());
			jobUpdate.setLastUpdatedOn(dateForMate.format(now));
			jobUpdate.setReason(jobEntity.getReason());
			jobUpdate.setTicketId(jobEntity.getTicketId());
			jobUpdate.setUserId(jobEntity.getUserId());
			response = iJobRepositiory.save(jobUpdate);

		} else {
			LOG.info(" JOB CREATE ");
			jobTicketEntity = new JobTicketEntity();
			jobSave.setActualEndDate(dateForMate.format(now));
			jobSave.setActualStartDate(dateForMate.format(now));
			jobSave.setStartDate(dateForMate.format(now));
			jobSave.setCustomerId(jobEntity.getCustomerId());
			jobSave.setJobId(jobEntity.getJobId());
			jobSave.setCustomerName(jobEntity.getCustomerName());
			jobSave.setLastUpdatedOn(dateForMate.format(now));
			jobSave.setNotes(jobEntity.getNotes());
			jobSave.setReason(jobEntity.getReason());
			jobSave.setTicketId(jobEntity.getTicketId());
			jobSave.setUserId(jobEntity.getUserId());
			jobSave.setStatus(jobEntity.getStatus());

			jobTicketEntity.setJobId(jobEntity.getJobId());
			jobTicketEntity.setTicketId(jobEntity.getTicketId());
			jobTicketEntity.setUserId(jobEntity.getUserId());

			iJobTicketRepository.save(jobTicketEntity);
			response = iJobRepositiory.save(jobSave);

		}
		return response;
	}

	/**
	 * getByTIcketId
	 */
	public List<JobDataModel> getByTicketIdDemo(String ticketId) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		List<JobEntity> lstEntities = iJobRepositiory.findByTicketId(ticketId);

		JobDataModel jobDataModel = null;
		List<JobDataModel> lstJobDataModels = new ArrayList<JobDataModel>();
		for (JobEntity jobEntity : lstEntities) {
			jobDataModel = new JobDataModel();
			BeanUtils.copyProperties(jobEntity, jobDataModel);
			lstJobDataModels.add(jobDataModel);
		}
		return lstJobDataModels;
	}

	/**
	 * 
	 */
	@Override
	public Page<JobDataModel> searchDetails(JobSpecification userSpecification)
			throws ResourceNotFoundException, Exception {

		// pageable
		Pageable paging = PageRequest.of(0, Integer.MAX_VALUE);
		List<JobEntity> jobEntities = iJobRepositiory.findAll(userSpecification);

		List<JobDataModel> lstJobrDataModel = new ArrayList<JobDataModel>();

		JobDataModel jobDataModel = null;

		JobEntity jobEntity = null;
		int size = jobEntities.size();

		for (int index = 0; index < size; index++) {

			jobEntity = jobEntities.get(index);
			jobDataModel = new JobDataModel();
			jobDataModel.setActualEndDate(jobEntity.getActualEndDate());
			jobDataModel.setActualStartDate(jobEntity.getActualStartDate());
			jobDataModel.setCustomerId(jobEntity.getCustomerId());
			jobDataModel.setCustomerName(jobEntity.getCustomerName());
			// jobDataModel.setId(jobEntity.getId());
			jobDataModel.setLastUpdatedOn(jobEntity.getLastUpdatedOn());
			jobDataModel.setNotes(jobEntity.getNotes());
			jobDataModel.setReason(jobEntity.getReason());
			jobDataModel.setStartDate(jobEntity.getStartDate());
			jobDataModel.setStatus(jobEntity.getStatus());
			jobDataModel.setTicketId(jobEntity.getTicketId());
			jobDataModel.setUserId(jobEntity.getUserId());
			jobDataModel.setJobId(jobEntity.getJobId());

			lstJobrDataModel.add(jobDataModel);

		}
		Page<JobDataModel> result = new PageImpl<JobDataModel>(lstJobrDataModel);
		return result;
	}
}
