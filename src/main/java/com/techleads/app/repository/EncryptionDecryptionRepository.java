package com.techleads.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techleads.app.common.DBQueries;
import com.techleads.app.model.EncryptDecryptData;
@Repository
public class EncryptionDecryptionRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	public int saveEncryptedMessage(EncryptDecryptData encryptData) {
		Object[] params = {encryptData.getMessage()};
		int count = jdbcTemplate.update(DBQueries.INSERT_INTO_ENCRYPTED_TAB, params);
		return count;
	}
	
	public int saveDecryptedMessage(EncryptDecryptData encryptData) {
		Object[] params = {encryptData.getDecryptedMessage(), encryptData.getId()};
		int count = jdbcTemplate.update(DBQueries.INSERT_INTO_DECRYPTED_TAB, params);
		return count;
	}
	
	
	
	public List<EncryptDecryptData> findAll() {
		Object[] params = {  };
		List<EncryptDecryptData> encryptedMessages = jdbcTemplate.query(DBQueries.SELECT_ALL_MSGS, new ExtractEncryptData(), params);
		
		return encryptedMessages;

	}
	
	public EncryptDecryptData findById(Integer id) {
		Object[] params = { id };
		EncryptDecryptData encryptData = jdbcTemplate.query(DBQueries.SELECT_MSGS_BY_ID, new ExtractEncryptDataById(false), params);
		
		return encryptData;

	}
	
	public EncryptDecryptData findMaxId() {
		Object[] params = {  };
		EncryptDecryptData encryptData = jdbcTemplate.query(DBQueries.SELECT_MSGS_BY_MAX_ID, new ExtractEncryptDataById(false), params);
		
		return encryptData;

	}
	
	public int deleteMasterTableById(Integer id) {
		Object[] params = {id};
		int count = jdbcTemplate.update(DBQueries.DELETE_ENCRYPTED_TAB_BY_ID, params);
		return count;
	}
	
	public int deleteChildTableById(Integer id) {
		Object[] params = {id};
		int count = jdbcTemplate.update(DBQueries.DELETE_DECRYPTED_TAB_BY_ID, params);
		return count;
	}
	
	public EncryptDecryptData findDecryptedMessageByIdAndEncryptMsg(EncryptDecryptData encryptData) {
		Object[] params = { encryptData.getId(), encryptData.getEncryptedMessage() };
		EncryptDecryptData decryptedMessages = jdbcTemplate.query(DBQueries.SELECT_DECRYPT_MSG_BY_ENCRYPT_AND_ID, new ExtractEncryptDataById(true), params);
		
		return decryptedMessages;

	}

	
	

}
