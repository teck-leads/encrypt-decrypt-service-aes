package com.techleads.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techleads.app.aes.EncryptDecryptUtility;
import com.techleads.app.common.DBQueries;
import com.techleads.app.model.EncryptDecryptData;
import com.techleads.app.repository.EncryptionDecryptionRepository;
import com.techleads.app.repository.ExtractEncryptDataById;

@Service
public class EncryptionDecryptionService {
	@Autowired
	private EncryptionDecryptionRepository encryptionDecryptionRepository;

	public List<EncryptDecryptData> findALLEncryptData() {

		List<EncryptDecryptData> encryptDataList = encryptionDecryptionRepository.findAll();
		return encryptDataList;

	}

	public EncryptDecryptData saveEncryptedMessage(EncryptDecryptData encryptData) {

		// Encrypting message and saving in Encrypt table
		String encryptedMsg = EncryptDecryptUtility.encryptPlainTextMsg(encryptData.getMessage());
		encryptData.setMessage(encryptedMsg);

		int count = encryptionDecryptionRepository.saveEncryptedMessage(encryptData);
		if (count > 0) {
			EncryptDecryptData encryptedMessage = encryptionDecryptionRepository.findMaxId();
			// Decrypting message and saving it in Decrypt table
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

	public EncryptDecryptData findDecryptedMessageByIdAndEncryptMsg(EncryptDecryptData encryptData) {
		EncryptDecryptData decryptedMessage = encryptionDecryptionRepository
				.findDecryptedMessageByIdAndEncryptMsg(encryptData);
		return decryptedMessage;
	}

	public String deleteEncryptDataById(Integer id) {

		int childCount = encryptionDecryptionRepository.deleteChildTableById(id);
		if (childCount > 0) {
			int masterCount = encryptionDecryptionRepository.deleteMasterTableById(id);
			if (masterCount > 0) {
				return "Deleted Encrypted message with id: " + id;
			}
		}

		return "Error: Not Deleted Encrypted message with id: " + id;
	}

}
