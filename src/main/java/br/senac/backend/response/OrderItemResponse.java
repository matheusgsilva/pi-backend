package br.senac.backend.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.senac.backend.util.ETYPE_PRODUCT;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemResponse {

	private String guid;
	private String orderGuid;
	private String productGuid;
	private String productName;
	private String productCode;
	private ETYPE_PRODUCT productType;
	private Integer quantity;
	private Double weight;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getOrderGuid() {
		return orderGuid;
	}

	public void setOrderGuid(String orderGuid) {
		this.orderGuid = orderGuid;
	}

	public String getProductGuid() {
		return productGuid;
	}

	public void setProductGuid(String productGuid) {
		this.productGuid = productGuid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public ETYPE_PRODUCT getProductType() {
		return productType;
	}

	public void setProductType(ETYPE_PRODUCT productType) {
		this.productType = productType;
	}

}
