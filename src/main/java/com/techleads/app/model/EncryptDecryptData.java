package com.techleads.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class EncryptDecryptData {

	private Integer id;
	@JsonInclude(Include.NON_NULL)
	private String message;
	@JsonInclude(Include.NON_NULL)
	private String encryptedMessage;
	@JsonInclude(Include.NON_NULL)

	private String decryptedMessage;

	public EncryptDecryptData() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

	public String getDecryptedMessage() {
		return decryptedMessage;
	}

	public void setDecryptedMessage(String decryptedMessage) {
		this.decryptedMessage = decryptedMessage;
	}

	@Override
	public String toString() {
		return "EncryptData [id=" + id + ", message=" + message + ", encryptedMessage=" + encryptedMessage
				+ ", decryptedMessage=" + decryptedMessage + "]";
	}

}
