package br.senac.backend.response;

import br.senac.backend.util.ETYPE_PRODUCT;

public class ProductResponse {

	private String guid;
	private String name;
	private String code;
	private ETYPE_PRODUCT type;
	private String description;
	private Double price;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ETYPE_PRODUCT getType() {
		return type;
	}

	public void setType(ETYPE_PRODUCT type) {
		this.type = type;
	}

}
