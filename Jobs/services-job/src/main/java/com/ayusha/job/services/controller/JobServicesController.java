package com.ayusha.job.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayusha.job.data.models.JobDataModel;
import com.ayusha.job.data.models.JobsDataModel;
import com.ayusha.job.service.IJobService;
import com.ayusha.job.services.entity.JobEntity;
import com.ayusha.job.services.entity.JobTicketEntity;
import com.ayusha.job.services.repository.IJobRepository;
import com.ayusha.job.specification.JobSpecification;
import com.ayusha.job.user.data.handler.UserLocationsDataHandler;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.user.data.models.LocationsUsersDataModel;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * This is the service api interface. This controls the access to the services
 *
 * @author Finch
 * @version 1.0
 * @since 01-Aug-2019
 */
@RestController
@RequestMapping("/jobs/job")
@CrossOrigin
public class JobServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(JobServicesController.class);

	/** iJobService **/
	@Autowired
	private IJobService iJobService;

	/** iJobRepository **/
	@Autowired
	private IJobRepository iJobRepository;

	/**
	 * create jobBy
	 */
	@PostMapping("/add")
	public String addJob(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job creation - start");
		JobDataModel jobDataModel = (JobDataModel) JSONConverter.convertStringToPOJO(requestBody, JobDataModel.class);
		iJobService.save(jobDataModel);
		return JSONConverter.convertPOJOToString(jobDataModel);
	}

	/**
	 * get job by ticket id
	 */
	@GetMapping("/getByTicketId")
	public JobTicketEntity getByJobTicketId(@RequestParam("ticketId") String ticketId)
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException {
		return iJobService.getByJobTicketId(ticketId);
	}

	/**
	 * find jobs for user id
	 */
	@GetMapping("/findJobsForUserId")
	public String findJobsForUserId(@RequestParam("userid") String userId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered find job for user id - start");
		JobsDataModel jobsDataModel = iJobService.getJobsFor(userId);
		return JSONConverter.convertPOJOToString(jobsDataModel);
	}

	/**
	 * assign job
	 */
	@PostMapping("/assign")
	public String createJob(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job assign - start " + requestBody);
		UserLocationsDataHandler userLocationsDataHandler = null;
		LocationsUsersDataModel locationsUsersDataModel = null;
		List<String> lstUserIds = null;
		TicketDataModel ticketDataModel = null;
		try {
			ticketDataModel = (TicketDataModel) JSONConverter.convertStringToPOJO(requestBody, TicketDataModel.class);
			LOG.info("Job data is====>" + requestBody + "::::" + ticketDataModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		iJobService.assignJob(ticketDataModel);
		return JSONConverter.convertPOJOToString(ticketDataModel);
	}

	/**
	 * assign job
	 */
	@PostMapping("/assignwithWeb")
	public String jobassignwithWeb(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServicesDataException, DataPersistenceOperationException {
		TicketDataModel ticketDataModel = null;

		try {
			ticketDataModel = (TicketDataModel) JSONConverter.convertStringToPOJO(requestBody, TicketDataModel.class);
			LOG.info("Job data is ====>" + requestBody + "::::" + ticketDataModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		iJobService.jobassignwithWeb(ticketDataModel);
		return JSONConverter.convertPOJOToString(ticketDataModel);
	}

	/**
	 * find jobs by status and customer id
	 */
	@GetMapping("/findJobsForStatusId")
	public String findJobsForUserIdAndStatus(@RequestParam("customerId") String customerId,
			@RequestParam("statusId") String statusId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("find job for status operation- start");
		JobDataModel jobDataModel = iJobService.getJobsFor(customerId, statusId);
		return JSONConverter.convertPOJOToString(jobDataModel);
	}

	/**
	 * find jobs by ticket id
	 */
	@GetMapping("/findJobForTicketId")
	public JobDataModel findJobForTicketId(@RequestParam("ticketid") String ticketid,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("find job for ticket id - start");
		return iJobService.getJob(ticketid);
	}

	/**
	 * find job by job id
	 */

	@GetMapping("/findJobForJobId")
	public String findJob(@RequestParam("jobid") String jobId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("find job for job id - start");

		return JSONConverter.convertPOJOToString(iJobService.getJob(jobId));
	}

	/**
	 * update job by status
	 */
	@PostMapping("/updateByStatus")
	public JobEntity updateByStatus(@RequestParam("statusId") String statusId,
			@RequestParam("ticketId") String ticketId, @RequestBody JobDataModel jobDataModel) {
		return iJobService.updateBasedOnStatus(statusId, ticketId, jobDataModel);
	}

	/**
	 * get all jobs
	 */
	@GetMapping("/getAllJob")
	public Page<JobEntity> getAllJob()
			throws ServicesDataException, DataPersistenceOperationException, InvalidServiceRequestException {
		return iJobService.getAllJob();
	}

	/**
	 * update job
	 */
	@PostMapping(value = "/jobUpdate")
	public String updateJob(@RequestBody String body, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("trigger update operation - start");
		JobEntity jobEntity = (JobEntity) JSONConverter.convertStringToPOJO(body, JobEntity.class);
		JobEntity j = iJobService.jobUpdate(jobEntity);
		return JSONConverter.convertPOJOToString(j);
	}

	/**
	 * getByTicketId
	 */
	@GetMapping("/getByTicketIdDemo")
	public List<JobDataModel> getByTicketIdDemo(@RequestParam("ticketId") String ticketId,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {

		return iJobService.getByTicketIdDemo(ticketId);
	}

	/**
	 * search Requested Base on values
	 */
//	@RequestMapping(value = "/Search", method = RequestMethod.GET)
	@GetMapping("/jobSearch")
	public Page<JobDataModel> searchBy(@RequestParam("Search") String userSearch)
			throws ResourceNotFoundException, Exception {
		// call to user specification class
		JobSpecification userSpecification = new JobSpecification(userSearch);
		return iJobService.searchDetails(userSpecification);

	}
}
