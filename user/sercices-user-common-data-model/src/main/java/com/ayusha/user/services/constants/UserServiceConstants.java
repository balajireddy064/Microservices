package com.ayusha.user.services.constants;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * User Service Constants
 */
public class UserServiceConstants {


	/** get user details by userid service name**/
	public final static String GET_USER_DETAILS_BY_USERID_SERVICE_NAME="users/user/userId?userid=$1";
	
	/** get user details by usermail service name **/
	public final static String GET_USER_DETAILS_BY_USEREMAIL_SERVICE_NAME="users/user/emailid?emailid=$1";
	
	/** get user details by userphone service name **/
	public final static String GET_USER_DETAILS_BY_USEREPHONE_SERVICE_NAME="users/user/phonenumber?phonenumber=$1";
	
	/** get user details by username service name **/
	public final static String GET_USER_DETAILS_BY_USERNAME_SERVICE_NAME="users/user/firstname?firstname=$1";
	
	/** get locations user details by username service name **/
	public final static String GET_LOCATIONS_USER_DETAILS_BY_USERNAME_SERVICE_NAME="users/user/locations?locationid=$1";
	
	/** get users service name **/
	public final static String GET_USERS_SERVICE_NAME="users/all";
	
	/** update user details service name**/
	public final static String UPDATE_USER_DETAILS_SERVICE_NAME="users/user/update";
	
	/** add user details service name **/
	public final static String ADD_USER_DETAILS_SERVICE_NAME="users/user/add";
}
