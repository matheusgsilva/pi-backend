package br.senac.backend.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.senac.backend.util.ETYPE_PRODUCT;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockResponse {

	private String guid;
	private String productGuid;
	private String productName;
	private String productCode;
	private ETYPE_PRODUCT stockType;
	private Integer quantityAvailable;
	private Double weightAvailable;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getProductGuid() {
		return productGuid;
	}

	public void setProductGuid(String productGuid) {
		this.productGuid = productGuid;
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

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Double getWeightAvailable() {
		return weightAvailable;
	}

	public void setWeightAvailable(Double weightAvailable) {
		this.weightAvailable = weightAvailable;
	}

	public ETYPE_PRODUCT getStockType() {
		return stockType;
	}

	public void setStockType(ETYPE_PRODUCT stockType) {
		this.stockType = stockType;
	}

}
