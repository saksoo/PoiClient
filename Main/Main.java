package Main;

import java.io.IOException;
import java.util.Properties;

import PoiServicesClient.Application;

public class Main {
	public static void main(String[] args) {
		try{
			//Load property file from project folder
			Properties p = new Properties();			
			p.load(Application.class.getClassLoader().getResourceAsStream("config.properties"));
			
			Application a = new Application(p);
			a.start();
		} catch (IOException ex) {
			System.err.println("IO Exception occured while loading property file:" + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error in application: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
