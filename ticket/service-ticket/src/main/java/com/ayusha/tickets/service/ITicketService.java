package com.ayusha.tickets.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.ayusha.ticket.data.models.TicketAndCustomerDataModel;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.ticket.data.models.TicketDataModelAndCustomerDataModels;
import com.ayusha.ticket.data.models.TicketDataModelUpdate;
import com.ayusha.tickets.entity.CustomerEntity;
import com.ayusha.tickets.entity.TicketEntity;
import com.google.protobuf.TextFormat.ParseException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface ITicketService {

	/** create ticket **/
	public TicketDataModel add(TicketDataModel ticketDataModel) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException;

	/** update **/
	public ResponseEntity<?> updateTicketBasedOnTicketId(String ticketId, TicketDataModel ticketDataModel)
			throws ParseException, ServicesDataException, ResourceNotFoundException;

	/** update ticket status **/
	public TicketEntity updatePartlyBy(String ticketId, TicketDataModelUpdate ticketUpdateDataModel)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException;

	/** get All tickets **/
	public Page<TicketDataModel> getTickets(Pageable pg) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** get tickets by city **/
	public Page<TicketAndCustomerDataModel> getCities(String city_filter, Pageable pageable)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception;

	/** get ticket by TikcetId **/
	public TicketDataModelAndCustomerDataModels getByTicketIdAndCustomer(String ticketId)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception;

	/** getByUserId **/
	public List<TicketDataModel> getByUserId(String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** getByDate **/
	public Page<TicketDataModel> getByDate(Date visit_date, Pageable pageable) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** getBy ticketId **/
	public TicketEntity getTicketDetails(String ticketId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** getALl City Based On character **/
	public Page<CustomerEntity> getAllCityBasedOnCharacter(String city)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;

	/** update Based on warranty **/
	public TicketDataModel updateWaranty(String ticketId, TicketDataModel ticketDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;

}