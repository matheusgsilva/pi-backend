package br.senac.backend.request;

public class StockRequest {

	private String productGuid;
	private Integer quantityAvailable;
	private Double weightAvailable;

	public String getProductGuid() {
		return productGuid;
	}

	public void setProductGuid(String productGuid) {
		this.productGuid = productGuid;
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

}
