package br.senac.backend.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.senac.backend.util.ETYPE_ORDER;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

	private String guid;
	private String userGuid;
	private String userName;
	private Date orderDate;
	private String number;
	private ETYPE_ORDER status;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ETYPE_ORDER getStatus() {
		return status;
	}

	public void setStatus(ETYPE_ORDER status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
