package com.techleads.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techleads.app.model.EncryptDecryptData;
import com.techleads.app.service.EncryptionDecryptionService;

@RestController
public class EncryptionDecryptionController {
	@Autowired
	private EncryptionDecryptionService encryptionDecryptionService;

	@PostMapping(value = { "/encryptmsgs" })
	public EncryptDecryptData saveExpense(@RequestBody EncryptDecryptData encryptData) {
		EncryptDecryptData encryptedMessage = encryptionDecryptionService.saveEncryptedMessage(encryptData);
		return encryptedMessage;
	}

	@GetMapping(value = {"/encryptmsgs"})
	public List<EncryptDecryptData> findALLEncryptData() {
		List<EncryptDecryptData> findALLEncryptData = encryptionDecryptionService.findALLEncryptData();
		return findALLEncryptData;
	}
	
	@GetMapping(value = {"/encryptmsgs/{id}"})
	public EncryptDecryptData findEncryptDataById(@PathVariable("id") Integer id) {
		EncryptDecryptData findById = encryptionDecryptionService.findEncryptDataById(id);
		return findById;

	}
	@PostMapping(value = {"/decryptmsgs"})
	public EncryptDecryptData findDecryptedMessageByIdAndEncryptMsg(@RequestBody EncryptDecryptData encryptData) {
		EncryptDecryptData decryptedMessage = encryptionDecryptionService.findDecryptedMessageByIdAndEncryptMsg(encryptData);
		return decryptedMessage;
	}


}
