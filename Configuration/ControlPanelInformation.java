package Configuration;

import java.util.Properties;

public class ControlPanelInformation {
	public final int T;
	
	public ControlPanelInformation(Properties p) {
		try {
			T = 1000* Integer.parseInt(p.getProperty("T"));			
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid properties file or settings missing: " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "ControlPanelInformation [T=" + T + "]";
	}
}