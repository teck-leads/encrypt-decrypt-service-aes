package com.techleads.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.techleads.app.model.EncryptDecryptData;

public class ExtractEncryptDataById implements ResultSetExtractor<EncryptDecryptData> {
	private boolean decryptMsg;

	public ExtractEncryptDataById(boolean decryptMsg) {
		this.decryptMsg = decryptMsg;
	}

	EncryptDecryptData encryptData = new EncryptDecryptData();

	@Override
	public EncryptDecryptData extractData(ResultSet rs) throws SQLException, DataAccessException {
		while (rs.next()) {
			encryptData.setId(rs.getInt("ENCRP_ID"));
			encryptData.setEncryptedMessage(rs.getString("ENCRYPTED_MSG"));
			if (decryptMsg) {
				encryptData.setDecryptedMessage(rs.getString("DECRYPTED_MSG"));
			}

		}
		return encryptData;
	}

}
