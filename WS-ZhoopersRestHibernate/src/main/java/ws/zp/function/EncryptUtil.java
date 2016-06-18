package ws.zp.function;

import javax.transaction.Transactional;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EncryptUtil
{
	private StandardPBEStringEncryptor encryptor;
	private final String password = "zhoopers";
	
	public EncryptUtil() 
	{
		SimplePBEConfig simplePBEConfig = new SimplePBEConfig();
		simplePBEConfig.setAlgorithm("PBEWithMD5AndDES");
		//simplePBEConfig.setKeyObtentionIterations(10000);
		simplePBEConfig.setPassword(password);
		
		//SaltGenerator saltGenerator = new RandomSaltGenerator();
		//saltGenerator.generateSalt(16);
		//simplePBEConfig.setSaltGenerator(saltGenerator);

		encryptor = new StandardPBEStringEncryptor();
		encryptor.setConfig(simplePBEConfig);
		encryptor.initialize();
	}
	
	public String encryptString(String value) 
	{
		return encryptor.encrypt(value);
	}
	
	public String decryptString(String value) 
	{
		return encryptor.decrypt(value);
	}
	
	/*public static void main(String[] args)
	{
		EncryptUtil ec = new EncryptUtil();
		String result = ec.decryptString("yMaDY+y1pBjUQWLVdFomFw==");
		System.out.println(result);
	}*/
}