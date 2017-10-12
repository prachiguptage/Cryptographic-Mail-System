package com.columbusstate.pgp;

import org.apache.commons.lang3.StringUtils;

public class Tester {

	private static final String PASSPHRASE = "test";

	private static final String DE_INPUT = "Hello_Encrypted.pgp";
	private static final String DE_OUTPUT = "Hello_Decrypted.txt";
	

	private static final String E_INPUT = "Hello.txt";
	private static final String E_OUTPUT = "Hello_Encrypted.pgp";
	

	public static void testDecrypt(String keyFileName) throws Exception {
		PGPFileProcessor p = new PGPFileProcessor();
		p.setInputFileName(DE_INPUT);
		p.setOutputFileName(DE_OUTPUT);
		p.setPassphrase(PASSPHRASE);
		p.setSecretKeyFileName(keyFileName + ".skr");
		System.out.println(p.decrypt());
	}

	public static void testEncrypt(String keyFileName) throws Exception {
		PGPFileProcessor p = new PGPFileProcessor();
		p.setInputFileName(E_INPUT);
		p.setOutputFileName(E_OUTPUT);
		p.setPassphrase(PASSPHRASE);
		p.setPublicKeyFileName(keyFileName + ".pkr");
		System.out.println(p.encrypt());
	}
	
	public static void main(String[] args) {
	
		String email = "siddharth.gupta26@gmail.com";
		try {
			testDecrypt(StringUtils.split(email,"@")[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}