/**
 * 
 */
package com.ayusha.payments.service;

import com.ayusha.payments.data.models.CashReceiptDiscountsDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;

/**
 * @author Swathi
 *
 */
public interface ICashReceiptDiscountsService {

	/** save cash receipt discounts **/
	public CashReceiptDiscountsDataModel save(CashReceiptDiscountsDataModel cashReceiptDiscountsDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** find discounted amount **/
	public CashReceiptDiscountsDataModel findDiscountAmount(String couponCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

}
