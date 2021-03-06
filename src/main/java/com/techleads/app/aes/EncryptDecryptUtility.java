package com.techleads.app.aes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptDecryptUtility {
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static SecretKey key = AESUtil.generateKey(128);
	private static IvParameterSpec ivParameterSpec = AESUtil.generateIv();

	public static String encryptPlainTextMsg(String plainTextMsg) {
		String cipherText = null;
		try {
			cipherText = AESUtil.encrypt(ALGORITHM, plainTextMsg, key, ivParameterSpec);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}

		return cipherText;

	}

	public static String decryptPlainTextMsg(String enctryptedTextMsg) {
		String plainText = null;
		try {
			plainText = AESUtil.decrypt(ALGORITHM, enctryptedTextMsg, key, ivParameterSpec);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}

		return plainText;

	}

}
