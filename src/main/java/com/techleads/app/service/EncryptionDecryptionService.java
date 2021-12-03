package com.techleads.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techleads.app.aes.EncryptDecryptUtility;
import com.techleads.app.model.EncryptDecryptData;
import com.techleads.app.repository.EncryptionDecryptionRepository;

@Service
public class EncryptionDecryptionService {
	@Autowired
	private EncryptionDecryptionRepository encryptionDecryptionRepository;

	public List<EncryptDecryptData> findALLEncryptData() {

		List<EncryptDecryptData> encryptDataList = encryptionDecryptionRepository.findAll();
		return encryptDataList;

	}

	public EncryptDecryptData saveEncryptedMessage(EncryptDecryptData encryptData) {

		//Encrypting message and saving in Encrypt table
		String encryptedMsg = EncryptDecryptUtility.encryptPlainTextMsg(encryptData.getMessage());
		encryptData.setMessage(encryptedMsg);

		int count = encryptionDecryptionRepository.saveEncryptedMessage(encryptData);
		if (count > 0) {
			EncryptDecryptData encryptedMessage = encryptionDecryptionRepository.findMaxId();
			//Decrypting message and saving it in Decrypt table
			String plainTextMsg = EncryptDecryptUtility.decryptPlainTextMsg(encryptedMsg);
			encryptedMessage.setDecryptedMessage(plainTextMsg);
			encryptionDecryptionRepository.saveDecryptedMessage(encryptedMessage);
			
			encryptedMessage.setDecryptedMessage(null);
			return encryptedMessage;
		}
		return new EncryptDecryptData();
	}

	public EncryptDecryptData findEncryptDataById(Integer id) {
		EncryptDecryptData findById = encryptionDecryptionRepository.findById(id);
		return findById;

	}

	public String deleteById(Integer id) {
		int count = encryptionDecryptionRepository.deleteById(id);

		if (count > 0) {
			return "Deleted the message with id " + id;
		}
		return "Not Deleted the message with id " + id;
	}

}
