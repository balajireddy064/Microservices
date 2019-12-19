package com.ayusha.job.services.constants;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Job Service Constants
 */
public class JobServiceConstants {
	
	/**Get Job Service Name **/
	public final static String GET_JOB_SERVICE_NAME="jobs/job/find?jobId=$1";
	
	/**Get Jobs for userid Service Name **/
	public final static String GET_JOBS_FOR_USERID_SERVICE_NAME="jobs/job/findJobsForUserId?userid=$1";
	
	/**Get Jobs for status Service Name **/
	public final static String GET_JOBS_FOR_STATUS_SERVICE_NAME="jobs/job/findJobsForStatusId?userid=$1&statusid=$2";
	
	/**Get Jobs for ticketid Service Name **/
	public final static String GET_JOBS_FOR_TICKETID_SERVICE_NAME="jobs/job/findJobForTicketId?ticketid=$1";
	
	/**Get Jobs for jobid Service Name **/
	public final static String GET_JOBS_FOR_JOBID_SERVICE_NAME="jobs/job/findJobForJobId?jobid=$1";
	
	/**Get users for locationid Service Name **/
	public final static String GET_USERS_FOR_LOCATIONID_SERVICE_NAME="users/user/locations?locationid=$1";
	
	
	
	/**Update Job Service Name **/
	public final static String UPDATE_JOB_SERVICE_NAME="jobs/job/update/";
	
	/** Add Job Service Name **/
	public final static String ADD_JOB_SERVICE_NAME="jobs/job/add";

}
