package com.ayusha.job.inventory.data.models;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductSerialize {
	/** id **/
	private String id;
	/** productId **/
	private String productId;
	/** lstItemDetails **/
	ArrayList<LstItemDetails> lstItemDetails;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the lstItemDetails
	 */
	public ArrayList<LstItemDetails> getLstItemDetails() {
		return lstItemDetails;
	}

	/**
	 * @param lstItemDetails the lstItemDetails to set
	 */
	public void setLstItemDetails(ArrayList<LstItemDetails> lstItemDetails) {
		this.lstItemDetails = lstItemDetails;
	}

	/**
	 * 
	 * @Class Inner class
	 *
	 */
	public static class LstItemDetails {
		/** id **/
		private String id;
		/** productId **/
		private String productId;
		/** quantity **/
		private String quantity;
		/** price **/
		private String price;
		/** itemId **/
		private String itemId;
		/** type **/
		private String type;
		/** tax **/
		private String tax;
		/** media **/
		private String media;
		/** name **/
		private String name;
		/** remarks **/
		private String remarks;

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the productId
		 */
		public String getProductId() {
			return productId;
		}

		/**
		 * @param productId the productId to set
		 */
		public void setProductId(String productId) {
			this.productId = productId;
		}

		/**
		 * @return the quantity
		 */
		public String getQuantity() {
			return quantity;
		}

		/**
		 * @param quantity the quantity to set
		 */
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		/**
		 * @return the price
		 */
		public String getPrice() {
			return price;
		}

		/**
		 * @param price the price to set
		 */
		public void setPrice(String price) {
			this.price = price;
		}

		/**
		 * @return the itemId
		 */
		public String getItemId() {
			return itemId;
		}

		/**
		 * @param itemId the itemId to set
		 */
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the tax
		 */
		public String getTax() {
			return tax;
		}

		/**
		 * @param tax the tax to set
		 */
		public void setTax(String tax) {
			this.tax = tax;
		}

		/**
		 * @return the media
		 */
		public String getMedia() {
			return media;
		}

		/**
		 * @param media the media to set
		 */
		public void setMedia(String media) {
			this.media = media;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the remarks
		 */
		public String getRemarks() {
			return remarks;
		}

		/**
		 * @param remarks the remarks to set
		 */
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public LstItemDetails(String id, String productId, String quantity, String price, String itemId, String type,
				String tax, String media, String name, String remarks) {
			super();
			this.id = id;
			this.productId = productId;
			this.quantity = quantity;
			this.price = price;
			this.itemId = itemId;
			this.type = type;
			this.tax = tax;
			this.media = media;
			this.name = name;
			this.remarks = remarks;
		}

	}

}
