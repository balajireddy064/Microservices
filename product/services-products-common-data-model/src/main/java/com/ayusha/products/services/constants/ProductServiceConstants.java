package com.ayusha.products.services.constants;
/**
 * 
 * @author Finch
 * Date: 01-Aug-2019
 * Product Service constant
 *
 */
public class ProductServiceConstants {

	/**Get Product Service Name **/
	public final static String GET_PRODUCT_SERVICE_NAME="products/product/find/productid?id=$1";
	/**Get Product for Service Name **/
	public final static String GET_PRODUCT_FOR_SERVICE_NAME="products/product/find/productfor?categoryid=$1&subcategoryid=$2&makeid=$3";
	/**Get Product for Service Name **/
	public final static String GET_PRODUCT_FOR1_SERVICE_NAME="products/product/find/productfor1?categoryid=$1&subcategoryid=$2&makeid=$3&modelid=$4";
	/**Update Product Service Name **/
	public final static String UPDATE_PRODUCT_SERVICE_NAME="products/product/update";
	/** Add Product Service Name **/
	public final static String ADD_PRODUCT_SERVICE_NAME="products/product/add";
}
