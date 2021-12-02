package com.techleads.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.techleads.app.model.EncryptDecryptData;

public class ExtractEncryptData implements ResultSetExtractor<List<EncryptDecryptData>> {

	List<EncryptDecryptData> encryptDataList=new ArrayList<>();
	@Override
	public List<EncryptDecryptData> extractData(ResultSet rs) throws SQLException, DataAccessException {
		while (rs.next()) {
			EncryptDecryptData encryptData = new EncryptDecryptData();
			encryptData.setId(rs.getInt("ENCRP_ID"));
			encryptData.setEncryptedMessage(rs.getString("ENCRYPTED_MSG"));
			encryptDataList.add(encryptData);
		}
		return encryptDataList;
	}

}


