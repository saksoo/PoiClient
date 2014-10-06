package PoiServicesClient;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.xml.rpc.ServiceException;

import Configuration.ControlPanelInformation;
import Configuration.RegistrationInformation;
import Configuration.WebServiceInformation;
import PoiServices.PoiBeatServiceImplementationServiceLocator;
import PoiServices.PoiBeatServiceInterface;

public class Application {
	public final WebServiceInformation webserviceInformation;
	public final ControlPanelInformation cpInformation;
	public final RegistrationInformation regInformation = new RegistrationInformation();
	
	public PoiBeatServiceImplementationServiceLocator service = null;
	public PoiBeatServiceInterface server = null;
	
	public RegistrationDialog rp;
	public ControlPanel cp;
	
	public Application(Properties p) {
		webserviceInformation = new WebServiceInformation(p);
		cpInformation = new ControlPanelInformation(p);
		
		System.out.println(webserviceInformation);
		System.out.println(cpInformation);
	}
	
	public void start() {
		try {
			System.out.println(webserviceInformation.http);
			service = new PoiBeatServiceImplementationServiceLocator(webserviceInformation.url,
					new javax.xml.namespace.QName( "http://PoiServices/", "PoiBeatServiceImplementationService"));
			server = service.getPoiBeatServiceImplementationPort();
			
			System.out.println("Application initialized successfully.");
			
			rp = new RegistrationDialog(this);
			rp.setVisible(true);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Application  failed to initialize. Cannot connect to web service. Please try again later.");
		}			
	}
	
	public void stop() {
		cp.setVisible(false);
		cp.dispose();
	}
	
	public boolean registerUser(String username, String password1, String password2) {
		try {
			String result = server.registerUser(username+"#"+password1+"#"+password2);
			if (result.equals("OK")) {
				regInformation.username = username;
				regInformation.password = password1;
				
				if (regInformation.username != null) {
					rp.setVisible(false);
					rp.dispose();
					cp = new ControlPanel(this);
					cp.setVisible(true);
					return true;
				} 	
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return false;
	}
	
	public boolean loginUser(String username, String password) {
		boolean result;
		try {
			result = server.loginUser(username+"#"+password);
			if (result) {
				regInformation.username = username;
				regInformation.password = password;
				
				if (regInformation.username != null) {
					rp.setVisible(false);
					rp.dispose();
					cp = new ControlPanel(this);
					cp.setVisible(true);
					return true;
				} 		
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return false;
	}

	public String setMonitorData(String username, String password, int x, int y, String type, String name) {
		String result;
		try {
			result = server.setMonitorData(username+"#"+password, x +"," + y +"#" + type +"#" + name); 
			return result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getMapData(String username, String password, int x, int y, int R) {
		String result;
		try {
			result = server.getMapData(username+"#"+password, x +"," + y +"," + R); 
			return result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
