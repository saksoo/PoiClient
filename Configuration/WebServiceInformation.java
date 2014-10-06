package Configuration;

import java.util.Properties;

public class WebServiceInformation {
	public final String url;
	public final String http;
	public final String name;
	
	public WebServiceInformation(Properties p) {
		url = p.getProperty("ws_url");
		http = p.getProperty("ws_http");
		name = p.getProperty("ws_name");
	
		if (url == null || http==null || name == null) {
			throw new IllegalArgumentException("Invalid properties file or settings missing");
		}	
	}

	@Override
	public String toString() {
		return "WebServiceInformation [url=" + url + ", http=" + http
				+ ", name=" + name + "]";
	}
}
