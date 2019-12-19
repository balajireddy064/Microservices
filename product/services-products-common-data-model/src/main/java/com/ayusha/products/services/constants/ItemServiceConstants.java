package com.ayusha.products.services.constants;

/**
 * 
 * @author Finch
 * Date: 01-Aug-2019
 * Product Service constant
 *
 */
public class ItemServiceConstants {

	/**Get Item By ItemId Service Name **/
	public final static String GET_ITEM_BY_ITEMID_SERVICE_NAME="products/item/find?id=$1";
	/**Get Item By Product Service Name **/
	public final static String GET_ITEM_BY_PRODUCT_SERVICE_NAME="products/item/find/product?productId=$1";
	/**Get Item By Product and Type Service Name **/
	public final static String GET_ITEM_BY_PRODUCTANDTYPE_SERVICE_NAME="products/item/find/producttype?productId=$1&type=$2";
	/** Update Item Service Name **/
	public final static String UPDATE_ITEM_SERVICE_NAME="products/item/update/";
	/**Add Item Service Name **/
	public final static String ADD_ITEM_SERVICE_NAME="products/item/add/";
}
