package com.exuberant.ims.util;

import com.exuberant.ims.exception.FatalException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyService {
	
	private Properties properties;
	
	private PropertyService(){
		properties = new Properties();
		String fileName = "default.properties";
		try {
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
			properties.load(inStream);
		} catch (IOException e) {
			throw new FatalException(String.format("Couldnt read properties file %s", fileName));
		}		
	}

	public int getPropertyAsInt(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}

	private static class SingletonPropertyService{
        private static final PropertyService INSTANCE = new PropertyService();
    }
	
	public static PropertyService getInstance(){
		return SingletonPropertyService.INSTANCE;
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}

}
