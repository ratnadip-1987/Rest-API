package BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Testbase {
	
	public int RESPONSE_STATUS_CODE_200=200;
	public int RESPONSE_STATUS_CODE_201=201;
	public static Properties prop;
	
	public Testbase()
	{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\Ratnadip Ghosh\\Documents\\TJXAutomation\\restAPI\\src\\main\\java\\Config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
