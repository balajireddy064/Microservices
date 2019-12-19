package com.ayusha.communication.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ayusha.communication.services.entity.CommunicationEntity;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the methods for
 *         the ticketmanagement persistance CRUD operations
 *
 */
public interface INotificationRepository extends JpaRepository<CommunicationEntity, Integer> {

	@Query("SELECT t FROM CommunicationEntity t WHERE t.otp = ?1")
	CommunicationEntity findNotificationByNotificationId(String notificationId);

	@Query("SELECT t FROM CommunicationEntity t WHERE t.jobCode = ?1 and  t.otp = ?2")
	CommunicationEntity findValidation(String jobcode, String otp);

	@Query("SELECT t FROM CommunicationEntity t WHERE t.otp = ?1")
	CommunicationEntity findOne(String otp);

	@Query("DELETE  FROM CommunicationEntity t WHERE t.phone=?1")
	void delete(String phone);

	CommunicationEntity findByPhone(String phone);

	@Query("SELECT t FROM CommunicationEntity t WHERE t.otp = ?1")
	CommunicationEntity findByOtp(String otp);

	@Query("SELECT t FROM CommunicationEntity t WHERE t.id = ?1")
	CommunicationEntity findByOtpId(long id);

}
