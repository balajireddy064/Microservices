package com.ayusha.repair.services.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.repair.service.IRepairNotesService;
import com.ayusha.repair.services.data.models.JobNotesDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
* This is the service api interface.
* This controls the access to the services
*
* @author  author1r
* @version 1.0
* @since   2019-05-03 
*/
@RestController
@RequestMapping("/repairs/notes")
public class RepairNotesServicesController extends ServiceRequestProcessor{
	
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(RepairNotesServicesController.class);
	
	/** repository **/
	@Autowired
	private IRepairNotesService iRepairServices;
	/**
	 * 
	 * 
	 */
	@PostMapping("/add")
	public String addJobNotes(@RequestBody String requestBody,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered job notes creation - start");
		String strRequestBody = preProcess(requestBody,httpServletRequest,httpServletResponse);
		
		strRequestBody = postProcess(requestBody,httpServletRequest,httpServletResponse);
		JobNotesDataModel jobNotesDataModel = (JobNotesDataModel)JSONConverter.convertStringToPOJO(requestBody, JobNotesDataModel.class);
		iRepairServices.save(jobNotesDataModel);
		LOG.info("successfully completed add job notes operation ");
		return JSONConverter.convertPOJOToString(jobNotesDataModel);
	}
	/**
	 * 
	 * 
	 */
	@PostMapping("/update")
	public String updateJobNotes(@RequestBody String requestBody,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		
		LOG.info("entered job notes creation - start");
		String strRequestBody = preProcess(requestBody,httpServletRequest,httpServletResponse);
		
		strRequestBody = postProcess(requestBody,httpServletRequest,httpServletResponse);
		JobNotesDataModel jobNotesDataModel = (JobNotesDataModel)JSONConverter.convertStringToPOJO(requestBody, JobNotesDataModel.class);
		iRepairServices.save(jobNotesDataModel);
		LOG.info("successfully completed add job notes operation ");
		return JSONConverter.convertPOJOToString(jobNotesDataModel);
	}
	
	/**
	 * 
	 * 
	 */
	@GetMapping("/find")
	public String getJobNotes(@RequestParam("jobid") String jobid,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered job notes creation - start");
		return JSONConverter.convertPOJOToString(iRepairServices.getJobNotes(jobid));
	}
}
